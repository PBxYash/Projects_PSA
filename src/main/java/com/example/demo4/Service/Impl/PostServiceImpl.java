package com.example.demo4.Service.Impl;

import com.example.demo4.Entity.Post;
import com.example.demo4.Exception.ResourceNotFoundException;
import com.example.demo4.Repository.PostRepository;
import com.example.demo4.Service.PostService;
import com.example.demo4.payload.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post save = postRepo.save(post);
        PostDto dto = new PostDto();
        dto.setId(save.getId());
        dto.setTitle(save.getTitle());
        dto.setDescription(save.getDescription());
        dto.setContent(save.getContent());
        return dto;

    }

    @Override
    public void deletePost(long id) {
//        Optional<Post> byId = postRepo.findById(id);
//        if(byId.isPresent()){
//            postRepo.deleteById(id);
//        }else {
//            throw new ResourceNotFoundException("post not found By id: "+id);
//        }
        postRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("post is not found by id:"+id)
        );{
            postRepo.deleteById(id);
        }
    }

    @Override
    public List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
        Page<Post> postRepoAll = postRepo.findAll(pageable);
        List<Post> Post = postRepoAll.getContent();
        List<PostDto> dtos = Post.stream().map(c -> MaptoDto(c)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public PostDto updatePost(long postId, PostDto postDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post is not Found " + postId)
        );
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post saved = postRepo.save(post);
        PostDto dtos = MaptoDto(saved);
        return dtos;
    }

    public PostDto MaptoDto(Post post){
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }
}

package com.example.demo4.Service.Impl;

import com.example.demo4.Entity.Comment;
import com.example.demo4.Entity.Post;
import com.example.demo4.Exception.ResourceNotFoundException;
import com.example.demo4.Repository.CommentRepository;
import com.example.demo4.Repository.PostRepository;
import com.example.demo4.Service.CommentService;
import com.example.demo4.payload.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found by id :" + postId)
        );
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        comment.setPost(post);

        Comment saved = commentRepository.save(comment);
        CommentDto dto = new CommentDto();
        dto.setId(saved.getId());
        dto.setName(saved.getName());
        dto.setEmail(saved.getEmail());
        dto.setBody(saved.getBody());

         return dto;
    }

    @Override
    public void deleteComments(long commentId) {
        commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("Comments is Not found by id:"+commentId)
        );
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        List<CommentDto> dtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> dtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public CommentDto updateComments(long id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("comments not found By id:" + id)
        );
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment save = commentRepository.save(comment);
        CommentDto dto = mapToDto(save);
        return dto;
    }

    CommentDto mapToDto(Comment comment){
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());
        return dto;
    }
}

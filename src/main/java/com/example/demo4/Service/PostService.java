package com.example.demo4.Service;

import com.example.demo4.payload.PostDto;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto postDto);

    void deletePost(long id);

    List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String Dir);

    PostDto updatePost(long postId, PostDto postDto);
}

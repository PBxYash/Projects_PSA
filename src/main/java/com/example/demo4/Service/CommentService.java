package com.example.demo4.Service;

import com.example.demo4.payload.CommentDto;

import java.util.List;

public interface CommentService {
    public CommentDto createComment(long postId, CommentDto commentDto);

    void deleteComments(long commentId);

    List<CommentDto> getCommentByPostId(long postId);

    List<CommentDto> getAllComments();

    CommentDto updateComments(long id, CommentDto commentDto);
}

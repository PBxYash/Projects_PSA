package com.example.demo4.Controller;

import com.example.demo4.Entity.Comment;
import com.example.demo4.Service.CommentService;
import com.example.demo4.payload.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
//    http://localhost:8081/api/comments?postId=2
    @PostMapping
    public ResponseEntity<CommentDto>createComment(@RequestParam("postId") long postId ,@RequestBody CommentDto commentDto){
        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComments(@PathVariable long commentId){
        commentService.deleteComments(commentId);
        return new ResponseEntity<>("Comment is deleted !!",HttpStatus.CREATED);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>>getCommentsByPostId(@PathVariable long postId){
        List<CommentDto> commentByPostId = commentService.getCommentByPostId(postId);
        return new ResponseEntity<>(commentByPostId,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CommentDto>>getAllComments(){
        List<CommentDto> dtos = commentService.getAllComments();
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<CommentDto>updateComments(
            @RequestParam("id") long id ,
            @RequestBody CommentDto commentDto
    ){
        CommentDto dto = commentService.updateComments(id, commentDto);
        return  new ResponseEntity<>(dto,HttpStatus.OK);
    }

}

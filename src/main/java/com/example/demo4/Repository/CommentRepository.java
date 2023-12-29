package com.example.demo4.Repository;

import com.example.demo4.Entity.Comment;
import com.example.demo4.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPostId(long postId);
}

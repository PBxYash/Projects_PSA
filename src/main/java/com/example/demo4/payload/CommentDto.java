package com.example.demo4.payload;

import com.example.demo4.Entity.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private long id ;

    private String name;

    private String email ;

    private String body;

}

package com.example.demo4.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

//import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private long id ;
    @NotNull
    @Size(min=2,message = "Title size minimum 4")
    private String title;
    @NotNull
    @Size(min=4,message ="description size minimum is 4 ")
    private String description;
    @NotNull
    @Size(min=4,message = "content size minimum is 4")
    private String content;


}

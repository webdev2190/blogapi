package com.blogapi.payload;

import lombok.Data;
@Data
//Todo: Comment object should be matched with CommentDto object
public class CommentDto {
    private long id;
    private String name;
    private String email;
    private String body;
}


package com.blogapi.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data // Encapsulation achieve in one annotation
public class PostDto {

    private long id;

    //ToDo: this Annotations comes from Sprint Validation
    @NotEmpty//22/06/2023 update codes
    @Size(min=2, message = "Title Should be At least 2 characters")
    private String title;

    @NotEmpty(message = "You can not leave empty Description")
    @Size(min=4, message = "Description Should be At least 4 characters")
    private String description;

    @NotEmpty
    private String content;
}

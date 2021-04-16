package com.ronsong.rngesus.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class CreatePostDTO {
    @NotEmpty(message = "title is required")
    @Length(min = 3, max = 60, message = "must be 3 - 60 characters in length")
    private String title;

    @NotEmpty(message = "content is required")
    @Length(min = 5, max = 500, message = "must be 5 - 500 characters in length")
    private String content;

    private List<String> tags;
}
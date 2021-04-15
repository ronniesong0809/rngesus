package com.ronsong.rngesus.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class CreatePostDTO {
    @NotEmpty(message = "Please enter the username")
    private String title;

    private String content;

    private List<String> tags;
}
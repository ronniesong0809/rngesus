package com.ronsong.rngesus.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class EditPostDTO {
    private String id;
    private String userId;

    @NotEmpty(message = "title is required")
    @Length(min = 3, max = 100, message = "must be 3 - 100 characters in length")
    private String title;

    @NotEmpty(message = "content is required")
    @Length(min = 5, max = 20000, message = "must be 5 - 20000 characters in length")
    private String content;

    private List<String> tags;
}
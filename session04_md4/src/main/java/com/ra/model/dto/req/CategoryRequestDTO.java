package com.ra.model.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryRequestDTO {
    @NotBlank(message = "ten dm ko dc de trong")
    private String categoryName;
    private String description;
    private Boolean status;
}

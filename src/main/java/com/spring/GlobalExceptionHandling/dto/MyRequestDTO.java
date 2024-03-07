package com.spring.GlobalExceptionHandling.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MyRequestDTO {
    @NotNull
    private String max;

    @NotNull
    @Size(min = 3, max = 10)
    private String word;
}

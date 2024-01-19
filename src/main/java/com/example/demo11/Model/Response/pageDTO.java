package com.example.demo11.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class pageDTO {
    private Object data;
    private Long totalElement;
    private Integer pageNumber;
    private Integer totalPages;
}

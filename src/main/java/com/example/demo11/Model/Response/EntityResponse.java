package com.example.demo11.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EntityResponse {
    private Object response;
    private Integer status;
}

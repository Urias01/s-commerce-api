package com.s.commerce.application.commons.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePagination {

    private Integer page;

    private Integer size;

    private String field;

    private String direction;

    public BasePagination(Integer page, Integer size, String field, String direction) {
        this.page = page;
        this.size = size;
        this.field = field;
        this.direction = direction;
    }
}

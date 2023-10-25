package com.yard.sale.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private int id;
    private String name;
    private boolean active;
}

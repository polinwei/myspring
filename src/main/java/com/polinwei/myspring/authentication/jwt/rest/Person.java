package com.polinwei.myspring.authentication.jwt.rest;

import lombok.Data;

@Data
public class Person {
    private String name;
    private String email;

    public Person() {
    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

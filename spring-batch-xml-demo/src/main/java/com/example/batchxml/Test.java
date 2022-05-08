package com.example.batchxml;

import com.example.batchxml.model.User;

import java.lang.reflect.Field;

public class Test {

    public static void main(String[] args) throws NoSuchFieldException {
        User user= new User();
        user.setId(1);

        Field field = new User().getClass().getDeclaredField("value");
        System.out.println("Type -->"+field.getType().getName());
    }
}

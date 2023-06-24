package com.lise.models.todos;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TodoPutResponse {
    public int userId;
    public int id;
    public String title;
    public  boolean completed;
}

package com.lise.models.todos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TodoPostBody {
public int userId;
public String title;
public boolean completed;
}

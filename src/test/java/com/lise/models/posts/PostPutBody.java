package com.lise.models.posts;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostPutBody {
    public   int userId;
    public  String title;
    public  String body;
}
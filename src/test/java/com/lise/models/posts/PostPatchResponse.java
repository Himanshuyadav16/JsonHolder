package com.lise.models.posts;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostPatchResponse {
    public   int userId;
    public  int id;
    public  String title;
    public  String body;

}

package com.lise.models.comments;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentPostResponse {
    public int postId;
    public int id;
    public String name;
    public String email;
    public String body;
}

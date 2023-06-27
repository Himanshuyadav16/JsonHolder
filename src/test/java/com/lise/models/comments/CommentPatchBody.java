package com.lise.models.comments;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentPatchBody {
    public int postId;
    public String name;
    public String email;
    public String body;
}

package com.lise.models.users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserPutResponse {
    public String name;
    public String email;
    public int id;

}

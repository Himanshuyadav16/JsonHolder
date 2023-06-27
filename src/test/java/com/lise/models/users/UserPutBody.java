package com.lise.models.users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserPutBody {
    public String name;
    public String email;
}

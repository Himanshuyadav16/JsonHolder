package com.lise.models.users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserPatchBody {
    public String name;
    public String email;
    public String username;
    public Address address;
    public String phone;
    public String website;
    public Company company;
}

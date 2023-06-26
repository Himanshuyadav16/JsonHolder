package com.lise.models.albums;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AlbumPutResponse {
    public int id;
    public int userId;
    public String title;
}


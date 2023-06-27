package com.lise.models.albums;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AlbumPutBody {
    public int userId;
    public String title;
}
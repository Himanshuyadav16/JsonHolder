package com.lise.models.photos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PhotoPostResponse {
    public int id;
    public int albumId;
    public String title;
    public String url;
    public String thumbnailUrl;

}

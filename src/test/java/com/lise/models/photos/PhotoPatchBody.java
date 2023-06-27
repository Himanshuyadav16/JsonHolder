package com.lise.models.photos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PhotoPatchBody {
    public int albumId;
    public String title;
    public String url;
    public String thumbnailUrl;
}

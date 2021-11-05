package com.example.demo.data.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class Item {
    @SerializedName("Name") private String name;
    @SerializedName("Type") private String type;
    @SerializedName("wTeaser") private String teaser;
    @SerializedName("wUrl") private String wikipediaUrl;
    @SerializedName("yId") private String youtubeId;
}

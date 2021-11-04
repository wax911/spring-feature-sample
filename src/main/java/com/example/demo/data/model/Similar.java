package com.example.demo.data.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public final class Similar {
    @SerializedName("info") private List<Item> info;
    @SerializedName("results") private List<Item> results;
}

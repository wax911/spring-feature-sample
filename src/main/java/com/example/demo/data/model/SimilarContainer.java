package com.example.demo.data.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public final class SimilarContainer {
    @SerializedName("similar") private Similar similar;
}

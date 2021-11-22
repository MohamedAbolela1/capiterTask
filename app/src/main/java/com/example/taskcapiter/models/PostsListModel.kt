package com.example.taskcapiter.models

import com.google.gson.annotations.SerializedName

data class PostsListModel(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("data")
    val data: PostsItem
)
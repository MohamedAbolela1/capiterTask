package com.example.taskcapiter.models

import com.google.gson.annotations.SerializedName


data class PostsItem(
    @SerializedName("after")
    val after: String? = null,
    @SerializedName("geo_filter")
    val geo_filter: String? = null,
    @SerializedName("children")
    val children: MutableList<Children>,
    @SerializedName("modhash")
    val modhash: String? = null,
    @SerializedName("dist")
    val dist: Int? = 0,

    )

{
    data class Children(
        @SerializedName("kind")
        val kind: String? = null,
        @SerializedName("data")
        val data: ChildrenItem? = null,
    )
    {
        data class ChildrenItem(
            @SerializedName("title")
            val title: String? = null,
            @SerializedName("thumbnail")
            val thumbnail: String? = null,
            @SerializedName("is_video")
            val is_video : Boolean ? = false,
            @SerializedName("url")
            val url : String ? = null,
            @SerializedName("secure_media")
            val secure_media : Media ? = null
        )
        {
            data class Media(
                    @SerializedName("reddit_video")
                    val reddit_video : Fallback ? = null
                )

                {
                    data class Fallback (
                        @SerializedName("fallback_url")
                        val fallback_url : String ? = null
                    )

                }

        }
    }
}

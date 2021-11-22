package com.example.taskcapiter.main.data.apiInterface

import com.example.taskcapiter.models.PostsListModel
import com.example.taskcapiter.utils.Constants.RemoteUrls.Kind
import com.example.taskcapiter.utils.Constants.RemoteUrls.Limit
import com.example.taskcapiter.utils.Constants.RemoteUrls.Posts_URL
import com.example.taskcapiter.utils.Constants.RemoteUrls.After
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApisInterface {
    @GET(Posts_URL)
    suspend fun getPostsList(
        @Query(Kind) kind : String,
        @Query(Limit) limit : Int,
    ): PostsListModel

    @GET(Posts_URL)
    suspend fun getOthersPostsList(
        @Query(Kind) kind : String,
        @Query(Limit) limit : Int,
        @Query(After) after : String
    ): PostsListModel
}
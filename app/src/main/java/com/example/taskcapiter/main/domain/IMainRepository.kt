package com.example.taskcapiter.main.domain

import com.example.taskcapiter.models.PostsListModel

interface IMainRepository {
    suspend fun getPostsList(kind: String, limit:Int): PostsListModel
    suspend fun getOtherPostsList(kind: String, limit:Int ,after: String): PostsListModel
}
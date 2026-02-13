package com.example.retrofit2.service

import com.example.retrofit2.model.Post
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Post

    @POST("posts")
    suspend fun createPost(@Body newPost: Post): Post

}

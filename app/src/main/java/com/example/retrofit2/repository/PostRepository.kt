package com.example.retrofit2.repository

import com.example.retrofit2.model.Post
import com.example.retrofit2.service.RetrofitClient

class PostRepository {

    private val apiService = RetrofitClient.instance

    suspend fun getPosts(): List<Post>? {
        return try {
            apiService.getPosts()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getPostById(postId: Int): Post? {
        return try {
            apiService.getPostById(postId)
        } catch (e: Exception) {
            null
        }
    }
    suspend fun createPost(newPost: Post): Post? {
        return try {
            apiService.createPost(newPost)
        } catch (e: Exception) {
            null
        }
    }


}

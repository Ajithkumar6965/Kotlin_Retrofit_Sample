package me.ajith.learning.retrofitsample.data.remote

import retrofit2.http.GET

interface UserApiService {

    @GET("posts")
    suspend fun getPosts():List<UserInfo>

}
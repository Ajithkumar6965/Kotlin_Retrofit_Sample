package me.ajith.learning.retrofitsample.data.remote

class ApiHelperImpl(private val apiService: UserApiService) : ApiHelper {
    override suspend fun getPosts(): List<UserInfo> = apiService.getPosts()
}
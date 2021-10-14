package com.example.githubrestapi

import retrofit2.Call
import retrofit2.http.GET
import org.springframework.web.bind.annotation.RequestParam;
import retrofit2.http.Query

interface Api {
    @GET("repositories")
    fun getRepositories(): Call<ArrayList<RepositoryResponse>>

    @GET("search/repositories")
    fun searchRepositories(
        @Query("q")q: String?,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("sort") sort: String?,
        @Query("order") order: String?
    ): Call<RepositoryResponse>
}
package com.example.retroapi

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("gimme")
    fun getData() : Call<responseDataClass>
}
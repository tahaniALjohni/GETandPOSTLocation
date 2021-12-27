package com.example.getandpostlocation

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIinterface{

    @GET("/test/")
    fun getUser(): Call<PersonDetails?>?


    @POST("/test/")
    fun addUser(@Body info:PersonDetails.PersonDetailsItem): Call<PersonDetails.PersonDetailsItem>
}

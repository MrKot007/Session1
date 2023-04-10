package com.example.session1try2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface API {
    @POST("signIn")
    fun signIn(@Body body: ModelAuth) : Call<ModelIdentity>
    @POST("signUp")
    fun signUp(@Body body: ModelReg) : Call<ModelIdentity>
}
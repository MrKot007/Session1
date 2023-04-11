package com.example.requests

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface API {
    @POST("signIn")
    fun signIn(@Body body: ModelAuth) : Call<ModelIdentity>
    @POST("signUp")
    fun signUp(@Body body: ModelReg) : Call<ModelIdentity>
    @GET("catalog/tags")
    fun getTags() : Call<List<ModelTag>>
    @GET("catalog/courses")
    fun getCourses() : Call<List<ModelCourse>>
    @GET("catalog/course")
    fun getCourse(@Query("idCourse") id: Int) : Call<ModelDataCourse>
    @GET("catalog/materials")
    fun getMaterials() : Call<List<ModelMaterial>>
    @POST("catalog/orderCreate")
    fun order(@Body body: List<Int>) : Call<Boolean>
    @GET("workSpace/plan")
    fun getPlan(@Query("date") date: String, @Query("courseId") courseId: Int, @Query("direction") direction: String) : Call<List<ModelObjective>>
    @POST("workSpace/confirmLesson")
    fun confirm(@Query("idLesson") idLesson: Int, @Query("commentFile") file: String) : Call<Boolean>
    @GET("workSpace/delayLessons")
    fun getExpiredLessons() : Call<List<ModelObjective>>
    @GET("profile")
    fun getProfile(@Query("idUser") idUser: String) : Call<Profile>
    @PATCH("profile")
    fun editProfile(@Body body: EditProfile) : Call<Profile>
    @GET("profile/courses")
    fun getProfileCourses() : Call<List<ModelCourse>>
    @GET("profile/course")
    fun getProfileCourse(@Query("idUser") idUser: String) : Call<ModelDataCourse>




}
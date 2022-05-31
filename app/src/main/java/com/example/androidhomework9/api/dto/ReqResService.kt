package com.example.androidhomework9.api.dto

import com.example.androidhomework9.api.dto.request.UserRequest
import com.example.androidhomework9.api.dto.response.CreateUserResponse
import com.example.androidhomework9.api.dto.response.ListUsers
import com.example.androidhomework9.api.dto.response.UpdateUserResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ReqResService {

    @GET("users")
    fun getUsers(@Query("page") page: Int): Call<ListUsers>

    @POST("users")
    fun createUser(@Body user: UserRequest): Call<CreateUserResponse>

    @PUT("users/{id}")
    fun updateUser(@Body user: UserRequest, @Path("id") id: Long): Call<UpdateUserResponse>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: Long): Call<ResponseBody>
}

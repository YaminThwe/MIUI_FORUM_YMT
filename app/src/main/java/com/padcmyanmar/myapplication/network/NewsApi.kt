package com.padcmyanmar.myapplication.network

import com.padcmyanmar.myapplication.network.responses.GetNewsResponse
import com.padcmyanmar.myapplication.utils.HealthCareConstants
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NewsApi
{
    @FormUrlEncoded
    @POST(HealthCareConstants.GET_NEWS)
    fun loadNews(
            @Field("access_token") accessToken: String) : Call<GetNewsResponse>
}
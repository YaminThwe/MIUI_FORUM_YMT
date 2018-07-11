package com.padcmyanmar.myapplication.network

import com.google.gson.Gson
import com.padcmyanmar.myapplication.events.ErrorEvent
import com.padcmyanmar.myapplication.events.SuccessEvent
import com.padcmyanmar.myapplication.network.responses.GetNewsResponse
import com.padcmyanmar.myapplication.utils.HealthCareConstants
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NewsDataAgent {

//skeleton structure
    companion object {
        private var INSTANCE: NewsDataAgent? = null
        fun getInstance(): NewsDataAgent {
            if (INSTANCE == null) {
                INSTANCE = NewsDataAgent()
            }
            val i = INSTANCE
            return i!!
        }
    }

    private val healthCareApi: NewsApi

    private constructor() {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(HealthCareConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(okHttpClient)
                .build()

        healthCareApi = retrofit.create(NewsApi::class.java)
    }
    fun loadHealthCare(accessToken: String){
        val newsResponseCall = healthCareApi.loadNews(accessToken)
        newsResponseCall.enqueue(object : Callback<GetNewsResponse> {
            override fun onFailure(call: Call<GetNewsResponse>?, t: Throwable?) {
                EventBus.getDefault().post(ErrorEvent.ApiErrorEvent(t))
            }

            override fun onResponse(call: Call<GetNewsResponse>, response: Response<GetNewsResponse>) {
                val newsResponse: GetNewsResponse? = response.body()
                if (newsResponse != null
                        && newsResponse.getCode() == 200
                        && newsResponse.getList().isNotEmpty()) {
                    val newsLoadedEvent = SuccessEvent.NewsLoadedEvent( newsResponse.getList())
                    EventBus.getDefault().post(newsLoadedEvent)
                } else {
                    if(newsResponse != null)
                        EventBus.getDefault().post(SuccessEvent.EmptyDataLoadedEvent(newsResponse.getMessage()))
                    else
                        EventBus.getDefault().post(SuccessEvent.EmptyDataLoadedEvent())
                }
            }
        })
    }


}
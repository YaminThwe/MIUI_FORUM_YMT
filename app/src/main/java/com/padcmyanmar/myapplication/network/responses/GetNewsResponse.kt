package com.padcmyanmar.myapplication.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.myapplication.data.vos.HealthCareInfoVO

class GetNewsResponse
{
    @SerializedName("code")
    private val code: Int = 0

    @SerializedName("message")
    private val message: String? =null

    @SerializedName("healthcare-info")
    private var healthCareList: List<HealthCareInfoVO>? = null

    fun getCode() : Int {
        return code
    }

    fun getMessage() : String? {
        return message
    }

    fun getList(): List<HealthCareInfoVO>
    {
        if(healthCareList == null)
        {
            healthCareList = ArrayList<HealthCareInfoVO>()
        }
        val newsListVal = healthCareList
        return newsListVal!!
    }
}
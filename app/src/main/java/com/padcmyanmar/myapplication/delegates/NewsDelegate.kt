package com.padcmyanmar.myapplication.delegates

import com.padcmyanmar.myapplication.data.vos.HealthCareInfoVO

interface NewsDelegate
{
    fun onTapNews(news: HealthCareInfoVO?)
}
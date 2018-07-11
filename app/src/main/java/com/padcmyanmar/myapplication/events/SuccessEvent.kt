package com.padcmyanmar.myapplication.events

import com.padcmyanmar.myapplication.data.vos.HealthCareInfoVO

class SuccessEvent
{
    class NewsLoadedEvent(val loadedNews: List<HealthCareInfoVO>)

    class EmptyDataLoadedEvent(val errorMsg: String? ="Empty Body Response")
}
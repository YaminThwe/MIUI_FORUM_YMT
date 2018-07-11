package com.padcmyanmar.myapplication.data.models

import com.padcmyanmar.myapplication.data.vos.HealthCareInfoVO
import com.padcmyanmar.myapplication.events.SuccessEvent
import com.padcmyanmar.myapplication.network.NewsDataAgent
import com.padcmyanmar.myapplication.utils.HealthCareConstants
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class HealthCareModel
{
    companion object {
            //sigleton
            private var INSTANCE: HealthCareModel? = null
            fun getInstance(): HealthCareModel {
                if (INSTANCE == null) {
                    INSTANCE = HealthCareModel()
                }

                val i = INSTANCE
                return i!!
            }
    }

    private constructor() {
        EventBus.getDefault().register(this)
    }

    private var healthCarePage: Int = 1
    private var healthCareDate: HashMap<Int,HealthCareInfoVO> = HashMap()

    fun loadNews(){
        NewsDataAgent.getInstance().loadHealthCare(HealthCareConstants.ACCESS_TOKEN)
    }

    fun forceLoadNews() {
        healthCarePage = 1
        healthCareDate = HashMap()
        NewsDataAgent.getInstance().loadHealthCare(HealthCareConstants.ACCESS_TOKEN)
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onNewsLoadedEvent(newsLoadedEvent: SuccessEvent.NewsLoadedEvent) {
        for (news: HealthCareInfoVO in newsLoadedEvent.loadedNews) {
            healthCareDate[news.id] = news
        }

    }

}
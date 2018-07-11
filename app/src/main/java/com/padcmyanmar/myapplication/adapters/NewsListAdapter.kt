package com.padcmyanmar.myapplication.adapters

import android.content.Context
import android.view.ViewGroup
import com.padcmyanmar.myapplication.R
import com.padcmyanmar.myapplication.data.vos.HealthCareInfoVO
import com.padcmyanmar.myapplication.delegates.NewsDelegate
import com.padcmyanmar.myapplication.viewholders.BaseNewsViewHolder
import com.padcmyanmar.myapplication.viewholders.NewsListViewHolder

class NewsListAdapter(context: Context, private val healthCareDelegate: NewsDelegate) : BaseRecyclerAdapter <NewsListViewHolder, HealthCareInfoVO>(context){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val healthCareItemView = healthLayoutInflater.inflate(R.layout.view_holder_news_list,parent,false)
        return NewsListViewHolder(healthCareItemView,healthCareDelegate)

    }

}

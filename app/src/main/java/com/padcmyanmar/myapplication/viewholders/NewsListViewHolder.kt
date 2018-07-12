package com.padcmyanmar.myapplication.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.padcmyanmar.myapplication.activities.NewsListActivity
import com.padcmyanmar.myapplication.data.vos.HealthCareInfoVO
import com.padcmyanmar.myapplication.delegates.NewsDelegate
import kotlinx.android.synthetic.main.activity_news_detail.view.*
import kotlinx.android.synthetic.main.view_holder_news_list.view.*

class NewsListViewHolder(itemView: View , private val healthDelegate : NewsDelegate):BaseNewsViewHolder<HealthCareInfoVO> (itemView)
{
    override fun setData(data: HealthCareInfoVO) {
        healthData = data

        itemView.tv_new_title_list!!.text = data.title

        itemView.tv_name!!.text=data.author!!.authorName

        if(data.image!=null){
            Glide.with(itemView.context)
                    .load(data.image!!)
                    .into(itemView.iv_news)
        }

    }

    override fun onClick(p0: View?) {
        healthDelegate.onTapNews(healthData)
    }

}


package com.padcmyanmar.myapplication.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseNewsViewHolder<W>(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    protected  var healthData: W? = null

    init{
        itemView.setOnClickListener(this)
    }
    abstract fun setData(data: W)
}
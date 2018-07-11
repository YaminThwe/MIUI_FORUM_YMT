package com.padcmyanmar.myapplication.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.padcmyanmar.myapplication.R
import com.padcmyanmar.myapplication.R.id.rvHealthCare
import com.padcmyanmar.myapplication.R.id.vp_empty
import com.padcmyanmar.myapplication.adapters.NewsListAdapter
import com.padcmyanmar.myapplication.data.models.HealthCareModel
import com.padcmyanmar.myapplication.data.vos.HealthCareInfoVO
import com.padcmyanmar.myapplication.delegates.NewsDelegate
import com.padcmyanmar.myapplication.events.ErrorEvent
import com.padcmyanmar.myapplication.events.SuccessEvent
import kotlinx.android.synthetic.main.activity_news_list.*
import kotlinx.android.synthetic.main.view_pod_empty.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class NewsListActivity : BaseActivity(), NewsDelegate {

    private var healthCareAdapter: NewsListAdapter? = null

    override fun onTapNews(news: HealthCareInfoVO?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        rvHealthCare.layoutManager=LinearLayoutManager(applicationContext)
        healthCareAdapter = NewsListAdapter(applicationContext,this)
        rvHealthCare.adapter=healthCareAdapter

        HealthCareModel.getInstance().loadNews()
        swipeRefreshLayout.isRefreshing = true

        swipeRefreshLayout.setOnRefreshListener {
            HealthCareModel.getInstance().loadNews()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_news_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSuccessGetHealthCare(newsLoadedEvent: SuccessEvent.NewsLoadedEvent){
        healthCareAdapter!!.appendNewData(newsLoadedEvent.loadedNews as MutableList<HealthCareInfoVO>)
        swipeRefreshLayout.isRefreshing = false
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onErrorNewsLoadedEvent(apiErrorEvent: ErrorEvent.ApiErrorEvent ) {
        swipeRefreshLayout.isRefreshing = false
    //    Snackbar.make(rvHealthCare, "ERROR : " + apiErrorEvent.getMsg(), Snackbar.LENGTH_LONG)
    //            .setAction("Action", null).show()
       var empty:View = vp_empty
        empty.visibility = View.VISIBLE






    }
    
}

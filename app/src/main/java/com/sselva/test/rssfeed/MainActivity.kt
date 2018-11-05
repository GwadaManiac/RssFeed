package com.sselva.test.rssfeed

import android.net.Uri
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import butterknife.ButterKnife
import com.squareup.picasso.Picasso
import com.sselva.test.rssfeed.manager.FeedManager
import com.sselva.test.rssfeed.manager.model.RssData
import com.sselva.test.rssfeed.ui.adapter.RssResultsAdapter
import com.sselva.test.rssfeed.ui.fragment.RssResultsFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private var mPosition: Int = 0
    private var mResultsAdapter: RssResultsAdapter? = null
    private val mDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        supportActionBar!!.hide()
        mSwipeRefreshLayout.setOnRefreshListener(this)

        mResultsAdapter = RssResultsAdapter(supportFragmentManager)
        mViewPager.setAdapter(mResultsAdapter)
        mViewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                updateRssData(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        mViewPager.setOffscreenPageLimit(mResultsAdapter!!.count)
        // This part fixes interaction between SwipeRefreshLayout and ViewPager
        mViewPager.setOnTouchListener(View.OnTouchListener { _, event ->
            mSwipeRefreshLayout.isEnabled = false
            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    mSwipeRefreshLayout.isEnabled = true
                }
            }
            return@OnTouchListener false
        })
        mTabLayout.setupWithViewPager(mViewPager)
        updateRssData(0)
    }

    override fun onStop() {
        super.onStop()
        if (!mDisposable.isDisposed) {
            mDisposable.dispose()
        }
    }

    private fun stopRefresh() {
        mSwipeRefreshLayout.isRefreshing = false
        mLoadingLayout.visibility = View.GONE
    }

    override fun onRefresh() {
        mLoadingLayout.visibility = View.VISIBLE
        updateRssData(mViewPager.currentItem)
    }

    private fun updateRssData(position: Int) {
        mPosition = position
        val observable: Observable<RssData>
        when (position) {
            0 -> observable = FeedManager.getInstance()!!.topNewsFeed
            1 -> observable = FeedManager.getInstance()!!.technoFeed
            2 -> observable = FeedManager.getInstance()!!.sportFeed
            3 -> observable = FeedManager.getInstance()!!.immoFeed
            4 -> observable = FeedManager.getInstance()!!.guidesAchatFeed
            else -> observable = FeedManager.getInstance()!!.topNewsFeed
        }
        val disposable = observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe({ rssData ->
            stopRefresh()
            if (!rssData.isEmpty()) {
                updateUI(rssData)
            } else {
                onWebServiceError()
                Log.w(TAG, "response body is null !")
            }
        }, { throwable ->
            stopRefresh()
            onWebServiceError()
            Log.w(TAG, throwable.message)
        })
        mDisposable.add(disposable)
    }

    private fun onWebServiceError() {
        Toast.makeText(this, "Network error !", Toast.LENGTH_LONG).show()
    }

    private fun updateUI(rssData: RssData) {
        val channel = rssData.channel
        if (!TextUtils.isEmpty(channel!!.title)) {
            mToolbarTitle.setText(channel.title)
        }
        if (!TextUtils.isEmpty(channel.description)) {
            mChannelDescription.setText(channel.description)
        }
        if (!TextUtils.isEmpty(channel.image!!.url)) {
            Picasso.get().load(Uri.parse(channel.image.url)).into(mChannelImage)
        }
        (mResultsAdapter!!.getItem(mPosition) as RssResultsFragment).setChannel(channel)
    }

    companion object {

        private val TAG = MainActivity::class.java.simpleName
    }
}

package com.sselva.test.rssfeed.manager


import com.sselva.test.rssfeed.manager.model.RssData

import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister

import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

import com.sselva.test.rssfeed.Constants.BASE_URL
import io.reactivex.schedulers.Schedulers

class FeedManager {

    val topNewsFeed: Observable<RssData>
        get() = client.topNewsFeed

    val technoFeed: Observable<RssData>
        get() = client.technoFeed

    val sportFeed: Observable<RssData>
        get() = client.sportFeed

    val guidesAchatFeed: Observable<RssData>
        get() = client.guidesAchatFeed

    val immoFeed: Observable<RssData>
        get() = client.immoFeed

    companion object {

        private val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(
                        Persister(AnnotationStrategy())))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()

        internal var client = retrofit.create(FeedApi::class.java)

        private var mInstance: FeedManager? = null

        fun getInstance(): FeedManager? {
            if (mInstance == null) {
                mInstance = FeedManager()
            }
            return mInstance
        }
    }
}

package com.sselva.test.rssfeed.manager

import com.sselva.test.rssfeed.manager.model.RssData

import io.reactivex.Observable
import retrofit2.http.GET

import com.sselva.test.rssfeed.Constants.GUIDES_ACHAT
import com.sselva.test.rssfeed.Constants.IMMO_FEED
import com.sselva.test.rssfeed.Constants.SPORT_FEED
import com.sselva.test.rssfeed.Constants.TECHNO_FEED
import com.sselva.test.rssfeed.Constants.UNE_FEED


interface FeedApi {

    @get:GET(UNE_FEED)
    val topNewsFeed: Observable<RssData>

    @get:GET(TECHNO_FEED)
    val technoFeed: Observable<RssData>

    @get:GET(SPORT_FEED)
    val sportFeed: Observable<RssData>

    @get:GET(GUIDES_ACHAT)
    val guidesAchatFeed: Observable<RssData>

    @get:GET(IMMO_FEED)
    val immoFeed: Observable<RssData>
}

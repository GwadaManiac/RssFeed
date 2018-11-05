package com.sselva.test.rssfeed.manager.model

import android.os.Parcel
import android.os.Parcelable

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

import java.io.Serializable


@Root(name = "item")
class ChannelItem : Serializable {

    @Element(required = false)
    val link: String? = null

    @Element
    val title: String? = null

    @Element(required = false)
    val description: String? = null

    @Element(required = false)
    val pubDate: String? = null

    @Element(required = false)
    val enclosure: ChannelItemEnclosure? = null
}

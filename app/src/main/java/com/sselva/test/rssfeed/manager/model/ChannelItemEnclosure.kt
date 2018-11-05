package com.sselva.test.rssfeed.manager.model

import android.os.Parcel
import android.os.Parcelable

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

import java.io.Serializable

@Root(name = "enclosure")
class ChannelItemEnclosure : Serializable {

    @Attribute
    val url: String? = null
}

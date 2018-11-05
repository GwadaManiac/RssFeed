package com.sselva.test.rssfeed.manager.model


import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

import java.util.ArrayList


@Root(name = "channel")
class Channel {

    @Element
    val title: String? = null

    @Element
    val description: String? = null

    @Element
    val copyright: String? = null

    @Element
    val pubDate: String? = null

    @Element
    val image: ChannelImage? = null

    @ElementList(inline = true, name = "item")
    val items: ArrayList<ChannelItem> = ArrayList()
}

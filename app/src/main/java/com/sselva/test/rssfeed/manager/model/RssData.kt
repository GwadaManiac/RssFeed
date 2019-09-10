package com.sselva.test.rssfeed.manager.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(name = "rss")
class RssData {

    @set:Element
    @get:Element
    var channel: Channel? = null

    fun isEmpty() : Boolean {
        return channel == null
    }
}

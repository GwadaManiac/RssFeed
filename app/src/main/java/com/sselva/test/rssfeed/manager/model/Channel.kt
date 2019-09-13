package com.sselva.test.rssfeed.manager.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.io.Serializable
import java.util.ArrayList


@Root(name = "channel")
class Channel :Serializable  {

    @set:Element
    @get:Element
    var title: String? = null

    @set:Element
    @get:Element
    var description: String? = null

    @set:Element
    @get:Element
    var copyright: String? = null

    @set:Element
    @get:Element
    var pubDate: String? = null

    @set:ElementList(inline = true, name = "item")
    @get:ElementList(inline = true, name = "item")
    var items: ArrayList<ChannelItem> = ArrayList()
}

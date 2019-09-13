package com.sselva.test.rssfeed.manager.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

import java.io.Serializable


@Root(name = "item")
class ChannelItem : Serializable {

    @get:Element
    @set:Element
    var link: String? = null

    @get:Element
    @set:Element
    var title: String? = null

    @get:Element
    @set:Element
    var description: String? = null

    @get:Element
    @set:Element
    var pubDate: String? = null

    @get:Element(required = false)
    @set:Element(required = false)
    var enclosure: ChannelItemEnclosure? = null
}

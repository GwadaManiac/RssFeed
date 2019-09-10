package com.sselva.test.rssfeed.manager.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable


@Root(name = "image")
class ChannelImage : Serializable {

    @set:Element
    @get:Element(name = "url")
    var url: String? = null

    @set:Element
    @get:Element(name = "title")
    var title: String? = null
}

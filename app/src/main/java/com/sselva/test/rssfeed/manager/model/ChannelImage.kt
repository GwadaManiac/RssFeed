package com.sselva.test.rssfeed.manager.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(name = "image")
class ChannelImage {

    @Element(name = "url")
    val url: String? = null

    @Element(name = "title")
    val title: String? = null
}

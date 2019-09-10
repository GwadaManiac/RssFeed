package com.sselva.test.rssfeed.manager.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name = "enclosure")
class ChannelItemEnclosure : Serializable {

    @get:Attribute
    @set:Attribute
    var url: String? = null
}

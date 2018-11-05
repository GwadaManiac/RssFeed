package com.sselva.test.rssfeed.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.text.TextUtils
import android.view.View.GONE
import com.squareup.picasso.Picasso
import com.sselva.test.rssfeed.R
import com.sselva.test.rssfeed.manager.model.ChannelItem
import com.sselva.test.rssfeed.ui.WebViewActivity.Companion.EXTRA_WEB_URL
import kotlinx.android.synthetic.main.activity_channel_item_detail.*

class ChannelItemDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel_item_detail)

        var item: ChannelItem? = null
        if (intent != null) {
            item = intent.getSerializableExtra(KEY_CHANNEL_ITEM) as ChannelItem
        }
        bind(item!!)
    }

    private fun bind(channelItem: ChannelItem) {
        supportActionBar!!.hide()
        if (channelItem.enclosure != null && channelItem.enclosure.url != null) {
            Picasso.get().load(Uri.parse(channelItem.enclosure.url)).into(mChannelItemImage)
        } else {
            mChannelItemImage.visibility = GONE
        }
        mChannelItemTitle.text = channelItem.title
        if (!TextUtils.isEmpty(channelItem.pubDate))
            mChannelItemDate.text = channelItem.pubDate
        if (!TextUtils.isEmpty(channelItem.description))
            mChannelItemDescription.text = channelItem.description

        if (!TextUtils.isEmpty(channelItem.link)) {
            mChannelItemMoreLink.text = Html.fromHtml(getString(R.string.read_more))
            mChannelItemMoreLink.setOnClickListener {
                val intent = Intent(this@ChannelItemDetailsActivity, WebViewActivity.javaClass)
                intent.putExtra(EXTRA_WEB_URL, channelItem.link)
                startActivity(intent)
            }
        } else {
            mChannelItemMoreLink.visibility = GONE
        }
    }

    companion object {

        val KEY_CHANNEL_ITEM = "key_channel_item"
    }
}

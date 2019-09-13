package com.sselva.test.rssfeed.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
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
        if (!channelItem.enclosure?.url.isNullOrEmpty()) {
            Picasso.get().load(Uri.parse(channelItem.enclosure?.url)).into(mChannelItemImage)
        } else {
            mChannelItemImage.visibility = GONE
        }
        mChannelItemTitle.text = channelItem.title
        if (!channelItem.pubDate.isNullOrEmpty())
            mChannelItemDate.text = channelItem.pubDate
        if (!channelItem.description.isNullOrEmpty())
            mChannelItemDescription.text = channelItem.description

        if (!channelItem.link.isNullOrEmpty()) {
            mChannelItemMoreLink.text = HtmlCompat.fromHtml(getString(R.string.read_more), FROM_HTML_MODE_LEGACY)
            mChannelItemMoreLink.setOnClickListener {
                val intent = Intent(this@ChannelItemDetailsActivity, WebViewActivity::class.java)
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

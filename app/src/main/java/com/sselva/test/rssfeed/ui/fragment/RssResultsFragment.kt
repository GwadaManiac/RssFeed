package com.sselva.test.rssfeed.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sselva.test.rssfeed.R
import com.sselva.test.rssfeed.manager.model.Channel
import com.sselva.test.rssfeed.manager.model.ChannelItem
import com.sselva.test.rssfeed.ui.ChannelItemDetailsActivity
import com.sselva.test.rssfeed.ui.adapter.ChannelItemsAdapter
import kotlinx.android.synthetic.main.rss_results_fragment.view.*

class RssResultsFragment : Fragment() {

    private var mItemsAdapter: ChannelItemsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.rss_results_fragment, container, false)

        mItemsAdapter = ChannelItemsAdapter(object : ChannelItemsAdapter.OnChannelItemSelectedListener {
            override fun onChannelItemSelected(channelItem: ChannelItem) {
                val intent = Intent(activity, ChannelItemDetailsActivity::class.java)
                intent.putExtra(ChannelItemDetailsActivity.KEY_CHANNEL_ITEM, channelItem)
                startActivity(intent)
            }
        })
        view.mItemList!!.layoutManager = LinearLayoutManager(activity)
        view.mItemList!!.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        view.mItemList!!.adapter = mItemsAdapter

        return view
    }

    fun setChannel(channel: Channel?) {
        if (channel != null) {
            mItemsAdapter!!.setItems(channel.items)
        }
    }

    companion object {

        private val TAG = RssResultsFragment::class.java.simpleName
        val EXTRA_BUNDLE_POSITION = "EXTRA_BUNDLE_POSITION"
    }
}

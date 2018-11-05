package com.sselva.test.rssfeed.ui.adapter

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

import com.sselva.test.rssfeed.R
import com.sselva.test.rssfeed.manager.model.ChannelItem

import java.util.ArrayList


class ChannelItemsAdapter(private val mItemSelectedListener: OnChannelItemSelectedListener) : RecyclerView.Adapter<ChannelItemsAdapter.ChannelItemViewHolder>() {

    private val mChannelItemsList: ArrayList<ChannelItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.channel_item_view, parent, false)

        val viewHolder = ChannelItemViewHolder(itemView)
        viewHolder.itemView.setOnClickListener { mItemSelectedListener.onChannelItemSelected(viewHolder.mChannelItem) }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ChannelItemViewHolder, position: Int) {
        holder.bind(mChannelItemsList[position])
    }

    override fun getItemCount(): Int {
        return mChannelItemsList.size
    }

    fun setItems(items: ArrayList<ChannelItem>) {
        mChannelItemsList.clear()
        mChannelItemsList.addAll(items)
        notifyDataSetChanged()
    }

    interface OnChannelItemSelectedListener {
        fun onChannelItemSelected(channelItem: ChannelItem)
    }

    class ChannelItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var mChannelItem: ChannelItem

        fun bind(channelItem: ChannelItem) {
            mChannelItem = channelItem
            itemView.findViewById<TextView>(R.id.mItemTitle).text = channelItem.title
            if (channelItem.description != null)
                itemView.findViewById<TextView>(R.id.mItemDescription).text = channelItem.description
            if (channelItem.enclosure?.url != null) {
                Picasso.get().load(Uri.parse(channelItem.enclosure.url)).into(itemView.findViewById<ImageView>(R.id.mItemImage))
            } else {
                itemView.findViewById<ImageView>(R.id.mItemImage).visibility = View.INVISIBLE
            }
        }
    }
}

package com.sselva.test.rssfeed.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sselva.test.rssfeed.R
import com.sselva.test.rssfeed.manager.model.ChannelItem
import kotlinx.android.synthetic.main.channel_item_view.view.*
import java.util.*


class ChannelItemsAdapter(private val mItemSelectedListener: OnChannelItemSelectedListener) : RecyclerView.Adapter<ChannelItemsAdapter.ChannelItemViewHolder>() {

    private val mChannelItemsList: ArrayList<ChannelItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.channel_item_view, parent, false)

        return ChannelItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChannelItemViewHolder, position: Int) {
        holder.bind(mChannelItemsList[position])
        holder.itemView.setOnClickListener { mItemSelectedListener.onChannelItemSelected(holder.itemView.tag as ChannelItem) }
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

        fun bind(channelItem: ChannelItem) {
            itemView.mItemTitle.text = channelItem.title
            if (channelItem.description != null)
                itemView.mItemDescription.text = channelItem.description
            if (channelItem.enclosure?.url != null) {
                Picasso.get().load(Uri.parse(channelItem.enclosure!!.url)).into(itemView.mItemImage)
            } else {
                itemView.mItemImage.visibility = View.INVISIBLE
            }
            itemView.tag = channelItem
        }
    }
}

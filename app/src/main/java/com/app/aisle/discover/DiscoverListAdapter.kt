package com.app.aisle.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.app.aisle.R
import com.app.aisle.R.id.image_profile
import com.app.aisle.entity.home.LikesProfiles
import com.app.aisle.extension.imgsrc

class DiscoverListAdapter() :
    ListAdapter<LikesProfiles, DiscoverViewHolder>(DiscoverDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_likes_you, parent, false)
         return DiscoverViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        holder.itemView.findViewById<ImageView>(image_profile).imgsrc(getItem(position).avatar)
        holder.itemView.findViewById<TextView>(R.id.textview_profile).text =
            getItem(position).first_name
    }
}

class DiscoverDiffUtilCallBack : DiffUtil.ItemCallback<LikesProfiles>() {
    override fun areItemsTheSame(oldItem: LikesProfiles, newItem: LikesProfiles): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: LikesProfiles, newItem: LikesProfiles): Boolean {
        return oldItem.equals(newItem)
    }
}
package com.vad.pullup.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vad.pullup.R

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {

    class PagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val textView = itemView.findViewById(R.id.text) as TextView

        fun bind(text: String) {
            textView.text = text
        }
    }

    private val text = arrayOf("This is training screen", "This is statistic screen")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder =
        PagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_pager, parent, false))

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(text[position])
    }

    override fun getItemCount(): Int = text.size

}
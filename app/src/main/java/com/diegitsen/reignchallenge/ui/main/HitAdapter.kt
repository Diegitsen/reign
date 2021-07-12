package com.diegitsen.reignchallenge.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegitsen.reignchallenge.data.entity.Hit
import com.diegitsen.reignchallenge.databinding.AdapterHitBinding

class HitAdapter(private var items: List<Hit>, private val mListener: HitItemClickListener) :
    RecyclerView.Adapter<HitAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdapterHitBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], mListener)
    }

    fun replaceData(arrayList: List<Hit>) {
        items = arrayList
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: AdapterHitBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hit: Hit, listener: HitItemClickListener) {
            binding.hit = hit
            binding.hitItemClick = listener
            binding.executePendingBindings()
        }
    }

    interface HitItemClickListener {
        fun onHitItemClicked(storyUrl: String?)
    }
}
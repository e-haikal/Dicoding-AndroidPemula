package com.siaptekno.yogyacampus

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.siaptekno.yogyacampus.databinding.ActivityMainBinding
import com.siaptekno.yogyacampus.databinding.ItemRowCampusBinding

class ListCampusAdapter(private val listCampus: ArrayList<Campus>) : RecyclerView.Adapter<ListCampusAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemRowCampusBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listCampus[position]

        // If photo is a URL or path
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.binding.itemPhotoImageView)
        holder.binding.itemNameTextView.text = name
        holder.binding.itemDescriptionTextView.text = description

        // Set click listener
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("EXTRA_NAME", name)
                putExtra("EXTRA_DESCRIPTION", description)
                putExtra("EXTRA_PHOTO", photo)
            }
            context.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowCampusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listCampus.size
    }

}
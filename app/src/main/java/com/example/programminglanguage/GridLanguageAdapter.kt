package com.example.programminglanguage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GridLanguageAdapter (val listLanguage: ArrayList<Language>) :
    RecyclerView.Adapter<GridLanguageAdapter.GridViewHolder>() {

        private lateinit var onItemClickCallback: OnItemClickCallback

        interface OnItemClickCallback {
            fun onItemClicked(data: Language)
        }

        fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
            this.onItemClickCallback = onItemClickCallback
        }

        inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_language, parent, false)
            return GridViewHolder(view)
        }

        override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
            Glide.with(holder.itemView.context)
                .load(listLanguage[position].photo)
                .apply(RequestOptions().override(350, 550))
                .into(holder.imgPhoto)

            holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listLanguage[holder.adapterPosition]) }
        }

        override fun getItemCount(): Int {
            return listLanguage.size
        }
    }
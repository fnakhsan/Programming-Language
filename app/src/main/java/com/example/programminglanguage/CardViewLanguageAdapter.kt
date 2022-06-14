package com.example.programminglanguage

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CardViewLanguageAdapter(private val listLanguage: ArrayList<Language>) :
    RecyclerView.Adapter<CardViewLanguageAdapter.CardViewViewHolder>()  {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Language)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var btnFavorite: Button = itemView.findViewById(R.id.btn_set_favorite)
        var btnShare: Button = itemView.findViewById(R.id.btn_set_share)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_language, parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val language = listLanguage[position]

        Glide.with(holder.itemView.context)
            .load(language.photo)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)

        holder.tvName.text = language.name
        holder.tvDetail.text = language.detail

        holder.btnFavorite.setOnClickListener { Toast.makeText(holder.itemView.context, "Like " + listLanguage[position].name, Toast.LENGTH_SHORT).show()
            holder.btnFavorite.isSelected = !holder.btnFavorite.isSelected
        }

        holder.btnShare.setOnClickListener { Toast.makeText(holder.itemView.context, "Share " + listLanguage[position].name, Toast.LENGTH_SHORT).show()
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "${language.name} language, ${language.detail}")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            holder.btnShare.context.startActivity(shareIntent)
        }
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listLanguage[position]) }
    }

    override fun getItemCount(): Int = listLanguage.size
}
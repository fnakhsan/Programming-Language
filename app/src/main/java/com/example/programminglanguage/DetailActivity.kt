package com.example.programminglanguage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    private var title: String = "Programming Language"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val language = intent.getParcelableExtra<Language>(EXTRA_LANGUAGE)

        val photo = findViewById<ImageView>(R.id.img_item_photo)
        val name = findViewById<TextView>(R.id.tv_item_name)
        val dev = findViewById<TextView>(R.id.tv_item_developer)
        val paradigm = findViewById<TextView>(R.id.tv_item_paradigm)
        val detail = findViewById<TextView>(R.id.tv_item_detail)

        photo.setImageResource(language?.photo!!)
        name.text = language.name
        dev.text = language.developer
        paradigm.text = language.paradigm
        detail.text = language.detail

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setActionBar(title)
    }

    private fun setActionBar(title: String) {
        supportActionBar?.title = title
    }

    companion object {
        const val EXTRA_LANGUAGE = "extra_language"
    }
}
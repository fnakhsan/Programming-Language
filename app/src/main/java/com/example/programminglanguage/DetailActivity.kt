package com.example.programminglanguage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    private var title: String = "Programming Language"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val language = intent.getParcelableExtra<Language>(EXTRA_LANGUAGE)

        Log.d("test", language.toString())

        val foto = findViewById<ImageView>(R.id.img_item_photo)
        val nama = findViewById<TextView>(R.id.tv_item_name)
        val dev = findViewById<TextView>(R.id.tv_item_developer)
        val paradigma = findViewById<TextView>(R.id.tv_item_paradigm)
        val ket = findViewById<TextView>(R.id.tv_item_detail)

        foto.setImageResource(language?.photo!!)
        nama.text = language.name
        dev.text = language.developer
        paradigma.text = language.paradigm
        ket.text = language.detail

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
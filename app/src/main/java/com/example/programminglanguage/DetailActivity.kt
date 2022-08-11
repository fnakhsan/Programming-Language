package com.example.programminglanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class DetailActivity : AppCompatActivity() {

    private lateinit var title: String
    private lateinit var language: Language

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        language = intent.getParcelableExtra(EXTRA_LANGUAGE)!!

        val photo = findViewById<ImageView>(R.id.img_item_photo)
        val name = findViewById<TextView>(R.id.tv_item_name)
        val dev = findViewById<TextView>(R.id.tv_item_developer)
        val paradigm = findViewById<TextView>(R.id.tv_item_paradigm)
        val detail = findViewById<TextView>(R.id.tv_item_detail)

        photo.setImageResource(language.photo!!)
        title = language.name!!
        name.text = title
        dev.text = language.developer
        paradigm.text = language.paradigm
        detail.text = language.detail

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setActionBar(title)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_share -> {
                Toast.makeText(
                    this,
                    "Share $title",
                    Toast.LENGTH_SHORT
                ).show()
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "$title language, ${language.detail}")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
            R.id.action_favorite -> {
                Toast.makeText(
                    this,
                    "Favorite $title",
                    Toast.LENGTH_SHORT
                ).show()
                item.setIcon(R.drawable.ic_baseline_share_24)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBar(title: String) {
        supportActionBar?.title = title
    }

    companion object {
        const val EXTRA_LANGUAGE = "extra_language"
    }
}
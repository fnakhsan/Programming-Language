package com.example.programminglanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var title: String
    private lateinit var language: Language
    private lateinit var favorite: Favorite
    private lateinit var dao: FavoriteDao
    private var favStatus: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        language = intent.getParcelableExtra(EXTRA_LANGUAGE)!!

        dao = FavoriteDatabase.getInstance(this).favoriteDao

        val photo = findViewById<ImageView>(R.id.img_item_photo)
        val name = findViewById<TextView>(R.id.tv_item_name)
        val dev = findViewById<TextView>(R.id.tv_item_developer)
        val paradigm = findViewById<TextView>(R.id.tv_item_paradigm)
        val detail = findViewById<TextView>(R.id.tv_item_detail)

        photo.setImageResource(language.photo!!)
        title = language.name ?: "Programming Language"
        name.text = title
        dev.text = language.developer
        paradigm.text = language.paradigm
        detail.text = language.detail

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setActionBar(title)
        Log.d(TAG, "finish")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d(TAG, "start onCreateOptionsMenu")
        menuInflater.inflate(R.menu.menu_detail, menu)
        Log.d(TAG, "finish onCreateOptionsMenu")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                Toast.makeText(
                    this,
                    "Shared $title",
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
                favStatus = when (favStatus) {
                    false -> {
                        lifecycleScope.launch(Dispatchers.IO) {
                            favorite.apply {
                                name = language.name!!
                                paradigm = language.paradigm!!
                                developer = language.developer!!
                                detail = language.detail!!
                                photo = language.photo!!
                            }
                            dao.insertAll(favorite)
                        }
                        item.setIcon(R.drawable.ic_selected_favorite_24)
                        Toast.makeText(
                            this,
                            "Liked $title",
                            Toast.LENGTH_SHORT
                        ).show()
                        true
                    }
                    true -> {
                        lifecycleScope.launch(Dispatchers.IO) {
                            favorite.apply {
                                name = language.name!!
                                paradigm = language.paradigm!!
                                developer = language.developer!!
                                detail = language.detail!!
                                photo = language.photo!!
                            }
                            dao.delete(favorite.name)
                        }
                        Toast.makeText(
                            this,
                            "Disliked $title",
                            Toast.LENGTH_SHORT
                        ).show()
                        item.setIcon(R.drawable.ic_baseline_favorite_24)
                        false
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBar(title: String) {
        Log.d(TAG, "start setActionBar")
        supportActionBar?.title = title
        Log.d(TAG, "finish setActionBar")
    }

    companion object {
        const val EXTRA_LANGUAGE = "extra_language"
        const val TAG = "detail"
    }
}
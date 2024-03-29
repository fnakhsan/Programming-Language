package com.example.programminglanguage.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.programminglanguage.R
import com.example.programminglanguage.ViewModelFactory
import com.example.programminglanguage.databinding.ActivityDetailBinding
//import com.example.programminglanguage.model.Favorite
import com.example.programminglanguage.model.Language
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var title: String
    private lateinit var language: Language
    private var favStatus: Boolean = false

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "start activity")
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        detailViewModel = obtainViewModel(this@DetailActivity)
        Log.d(TAG, "finish obtain viewmodel")
        language = intent.getParcelableExtra(EXTRA_LANGUAGE)!!
        Log.d(TAG, "$language")

        binding.apply {
            imgItemPhoto.setImageResource(language.photo)
            title = language.name
            tvItemName.text = title
            tvItemDeveloper.text = language.developer
            tvItemParadigm.text = language.paradigm
            tvItemDetail.text = language.detail
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setActionBar(title)
        Log.d(TAG, "finish")
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[DetailViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        lifecycleScope.launch(Dispatchers.IO) {
            val fav = menu?.findItem(R.id.action_favorite)
            favStatus = detailViewModel.isFavorite(language.name)
            when (favStatus) {
                true -> {
                    fav?.setIcon(R.drawable.ic_selected_favorite_24)
                }
                false -> {
                    fav?.setIcon(R.drawable.ic_baseline_favorite_24)
                }
            }
        }
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
                when (favStatus) {
                    false -> {
                        lifecycleScope.launch(Dispatchers.IO) {
                            detailViewModel.insert(language)
                        }
                        item.setIcon(R.drawable.ic_selected_favorite_24)
                        favStatus = true
                        Toast.makeText(
                            this,
                            "Liked $title",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    true -> {
                        lifecycleScope.launch(Dispatchers.IO) {
                            detailViewModel.delete(language)
                        }
                        item.setIcon(R.drawable.ic_baseline_favorite_24)
                        favStatus = false
                        Toast.makeText(
                            this,
                            "Disliked $title",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBar(title: String) {
        supportActionBar?.title = title
    }

    companion object {
        const val EXTRA_LANGUAGE = "extra_language"
        const val TAG = "detail"
    }
}
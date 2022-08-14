package com.example.programminglanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.programminglanguage.adapter.CardViewLanguageAdapter
import com.example.programminglanguage.adapter.GridLanguageAdapter
import com.example.programminglanguage.adapter.ListLanguageAdapter
import com.example.programminglanguage.databinding.ActivityMainBinding
import com.example.programminglanguage.model.Language
import com.example.programminglanguage.model.LanguagesData
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    private lateinit var rvLanguages: RecyclerView
    private val list: ArrayList<Language> = arrayListOf()

    private var getFav: Boolean = false
    private var title: String = "Mode List"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_ProgrammingLanguage)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = obtainViewModel(this@MainActivity)

        rvLanguages = findViewById(R.id.rv_languages)
        rvLanguages.setHasFixedSize(true)

        list.addAll(LanguagesData.listData)
        setActionBarTitle(title)
        showRecyclerList(list)
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setMode(item: Int, list: ArrayList<Language>) {
        when (item) {
            R.id.action_list -> {
                title = "Mode List"
                showRecyclerList(list)
            }
            R.id.action_grid -> {
                title = "Mode Grid"
                showRecyclerGrid(list)
            }
            R.id.action_cardview -> {
                title = "Mode CardView"
                showRecyclerCardView(list)
            }
        }
    }

    private fun showFav(item: MenuItem, isFav: Boolean, title: String, list: ArrayList<Language>) {
        when (title) {
            "Mode List" -> {
                showRecyclerList(list)
            }
            "Mode Grid" -> {
                showRecyclerGrid(list)
            }
            "Mode CardView" -> {
                showRecyclerCardView(list)
            }
        }
        when(isFav) {
            false -> {
                item.setIcon(R.drawable.ic_selected_favorite_24)
                Toast.makeText(
                    this,
                    "Show Favorite in $title",
                    Toast.LENGTH_SHORT
                ).show()
                getFav = true
            }
            true -> {
                item.setIcon(R.drawable.ic_baseline_favorite_24)
                Toast.makeText(
                    this,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
                getFav = false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (getFav) {
            false -> {
                when (item.itemId) {
                    R.id.action_list -> {
                        setMode(R.id.action_list, list)
                    }
                    R.id.action_grid -> {
                        setMode(R.id.action_grid, list)
                    }
                    R.id.action_cardview -> {
                        setMode(R.id.action_cardview, list)
                    }
                    R.id.action_get_favorite -> {
                        mainViewModel.getAll().observe(this) {
                            if (it !== null) {
                                showFav(item, getFav, title, it as ArrayList<Language>)
                            }
                        }
                    }
                }
            }
            true -> {
                when (item.itemId) {
                    R.id.action_list -> {
                        mainViewModel.getAll().observe(this) {
                            if (it !== null) {
                                setMode(R.id.action_list, it as ArrayList<Language>)
                            }
                        }
                    }
                    R.id.action_grid -> {
                        mainViewModel.getAll().observe(this) {
                            if (it !== null) {
                                setMode(R.id.action_grid, it as ArrayList<Language>)
                            }
                        }
                    }
                    R.id.action_cardview -> {
                        mainViewModel.getAll().observe(this) {
                            if (it !== null) {
                                setMode(R.id.action_cardview, it as ArrayList<Language>)
                            }
                        }
                    }
                    R.id.action_get_favorite -> {
                        showFav(item, getFav, title, list)
                    }
                }
            }
        }
        if (item.itemId == R.id.action_profile) {
            val intent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(intent)
        }
        setActionBarTitle(title)
        return super.onOptionsItemSelected(item)
    }

    private fun showRecyclerList(list: ArrayList<Language>) {
        rvLanguages.layoutManager = LinearLayoutManager(this)
        val listLanguageAdapter = ListLanguageAdapter(list)
        rvLanguages.adapter = listLanguageAdapter

        listLanguageAdapter.setOnItemClickCallback(object :
            ListLanguageAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Language) {
                showSelectedLanguage(data)
            }
        })
    }

    private fun showRecyclerGrid(list: ArrayList<Language>) {
        rvLanguages.layoutManager = GridLayoutManager(this, 2)
        val gridLanguageAdapter = GridLanguageAdapter(list)
        rvLanguages.adapter = gridLanguageAdapter

        gridLanguageAdapter.setOnItemClickCallback(object :
            GridLanguageAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Language) {
                showSelectedLanguage(data)
            }
        })
    }

    private fun showRecyclerCardView(list: ArrayList<Language>) {
        rvLanguages.layoutManager = LinearLayoutManager(this)
        val cardViewLanguageAdapter = CardViewLanguageAdapter(list)
        rvLanguages.adapter = cardViewLanguageAdapter

        cardViewLanguageAdapter.setOnItemClickCallback(object :
            CardViewLanguageAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Language) {
                showSelectedLanguage(data)
            }
        })
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun showSelectedLanguage(language: Language) {
        val stringToast: String = getString(R.string.toast)
        Toast.makeText(this, "$stringToast ${language.name}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("extra_language", language)
        startActivity(intent)
    }

    companion object {
        const val TAG = "main"
    }
}
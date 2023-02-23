package com.example.programminglanguage.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.programminglanguage.AboutActivity
import com.example.programminglanguage.R
import com.example.programminglanguage.ViewModelFactory
import com.example.programminglanguage.adapter.CardViewLanguageAdapter
import com.example.programminglanguage.adapter.GridLanguageAdapter
import com.example.programminglanguage.adapter.ListLanguageAdapter
import com.example.programminglanguage.databinding.ActivityMainBinding
import com.example.programminglanguage.detail.DetailActivity
import com.example.programminglanguage.model.Language
import com.example.programminglanguage.model.LanguagesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    private lateinit var rvLanguages: RecyclerView
    private var list: ArrayList<Language> = LanguagesData.listData

    private var getFav: Boolean = false
    private var title: String = "Mode List"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = obtainViewModel(this@MainActivity)
        setTheme(R.style.Theme_ProgrammingLanguage)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvLanguages = findViewById(R.id.rv_languages)
        rvLanguages.setHasFixedSize(true)

        setActionBarTitle(title)
        showRecyclerList(list)
        mainViewModel.getAllChanges().observe(this) {
            when (getFav) {
                true -> {
                    when (title) {
                        "Mode List" -> {
                            showRecyclerList(it as ArrayList<Language>)
                        }
                        "Mode Grid" -> {
                            showRecyclerGrid(it as ArrayList<Language>)
                        }
                        "Mode CardView" -> {
                            showRecyclerCardView(it as ArrayList<Language>)
                        }
                    }
                }
                false -> {
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
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (getFav) {
            true -> {
                when (item.itemId) {
                    R.id.action_list -> {
                        lifecycleScope.launch(Dispatchers.IO) {
                            showRecyclerList(mainViewModel.getAll() as ArrayList<Language>)
                        }
                        title = "Mode List"
                    }
                    R.id.action_grid -> {
                        lifecycleScope.launch(Dispatchers.IO) {
                            showRecyclerGrid(mainViewModel.getAll() as ArrayList<Language>)
                        }
                        title = "Mode Grid"
                    }
                    R.id.action_cardview -> {
                        lifecycleScope.launch(Dispatchers.IO) {
                            showRecyclerCardView(mainViewModel.getAll() as ArrayList<Language>)
                        }
                        title = "Mode CardView"
                    }
                    R.id.action_get_favorite -> {
                        showFav(title, list)
                        item.setIcon(R.drawable.ic_favorite_default)
                        getFav = false
                        Toast.makeText(
                            this,
                            title,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            false -> {
                when (item.itemId) {
                    R.id.action_list -> {
                        title = "Mode List"
                        setMode(R.id.action_list, list)
                    }
                    R.id.action_grid -> {
                        title = "Mode Grid"
                        setMode(R.id.action_grid, list)
                    }
                    R.id.action_cardview -> {
                        title = "Mode CardView"
                        setMode(R.id.action_cardview, list)
                    }
                    R.id.action_get_favorite -> {
                        lifecycleScope.launch(Dispatchers.Main){
                            showFav(title, mainViewModel.getAll() as ArrayList<Language>)
                        }
                        item.setIcon(R.drawable.ic_favorite_selected)
                        getFav = true
                        Toast.makeText(
                            this,
                            "Show Favorite in $title",
                            Toast.LENGTH_SHORT
                        ).show()
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

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }

    private fun setMode(item: Int, list: ArrayList<Language>) {
        when (item) {
            R.id.action_list -> {
                showRecyclerList(list)
            }
            R.id.action_grid -> {
                showRecyclerGrid(list)
            }
            R.id.action_cardview -> {
                showRecyclerCardView(list)
            }
        }
    }

    private fun showFav(title: String, list: ArrayList<Language>) {
        lifecycleScope.launch(Dispatchers.Main){
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
        }
    }

    private fun showRecyclerList(list: ArrayList<Language>) {
        lifecycleScope.launch(Dispatchers.Main){
            rvLanguages.layoutManager = LinearLayoutManager(this@MainActivity)
            val listLanguageAdapter = ListLanguageAdapter(list)
            rvLanguages.adapter = listLanguageAdapter

            listLanguageAdapter.setOnItemClickCallback(object :
                ListLanguageAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Language) {
                    showSelectedLanguage(data)
                }
            })
        }
    }

    private fun showRecyclerGrid(list: ArrayList<Language>) {
        lifecycleScope.launch(Dispatchers.Main){
            rvLanguages.layoutManager = GridLayoutManager(this@MainActivity, 2)
            val gridLanguageAdapter = GridLanguageAdapter(list)
            rvLanguages.adapter = gridLanguageAdapter

            gridLanguageAdapter.setOnItemClickCallback(object :
                GridLanguageAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Language) {
                    showSelectedLanguage(data)
                }
            })
        }
    }

    private fun showRecyclerCardView(list: ArrayList<Language>) {
        lifecycleScope.launch(Dispatchers.Main){
            rvLanguages.layoutManager = LinearLayoutManager(this@MainActivity)
            val cardViewLanguageAdapter = CardViewLanguageAdapter(list)
            rvLanguages.adapter = cardViewLanguageAdapter

            cardViewLanguageAdapter.setOnItemClickCallback(object :
                CardViewLanguageAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Language) {
                    showSelectedLanguage(data)
                }
            })
        }
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun showSelectedLanguage(language: Language) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("extra_language", language)
        startActivity(intent)
    }
}
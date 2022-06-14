package com.example.programminglanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var rvLanguages: RecyclerView
    private var list: ArrayList<Language> = arrayListOf()
    private var title: String = "Mode List"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvLanguages = findViewById(R.id.rv_languages)
        rvLanguages.setHasFixedSize(true)

        list.addAll(LanguagesData.listData)
        setActionBarTitle(title)
        showRecyclerList()

    }

    private fun showRecyclerList() {
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

    private fun showRecyclerGrid() {
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

    private fun showRecyclerCardView() {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "Mode List"
                showRecyclerList()
            }
            R.id.action_grid -> {
                title = "Mode Grid"
                showRecyclerGrid()
            }
            R.id.action_cardview -> {
                title = "Mode CardView"
                showRecyclerCardView()
            }
            R.id.action_profile -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        setActionBarTitle(title)
    }

    private fun showSelectedLanguage(language: Language) {
        val stringToast: String = getString(R.string.toast)
        Toast.makeText(this, stringToast + language.name, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("extra_language", language)
        startActivity(intent)
    }
}
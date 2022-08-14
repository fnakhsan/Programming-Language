package com.example.programminglanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
//import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.programminglanguage.databinding.ActivityMainBinding
import com.example.programminglanguage.model.Language
import com.example.programminglanguage.model.LanguagesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    private lateinit var rvLanguages: RecyclerView
    private val list: ArrayList<Language> = arrayListOf()

    //    private var favoriteList: ArrayList<Favorite> = arrayListOf()
    private var getFav: Boolean = false
    private var title: String = "Mode List"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val dao: FavoriteDao = FavoriteDatabase.getInstance(this).favoriteDao
//        lifecycleScope.launch(Dispatchers.IO){
//            favoriteList = dao.getAll() as ArrayList<Favorite>
//        }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//            var list: ArrayList<Language> = arrayListOf()
        when (item.itemId) {
            R.id.action_get_favorite -> {
                getFav = when (getFav) {
                    false -> {
                        mainViewModel.getAll().observe(this@MainActivity) {
                            setMode(item.itemId, it as ArrayList<Language>)
                            Log.d(TAG, "get room: $list")
                        }
//                            list = mainViewModel.getAll() as ArrayList<Language>
                        item.setIcon(R.drawable.ic_selected_favorite_24)
                        true
                    }
                    true -> {
                        list.clear()
                        list.addAll(LanguagesData.listData)
                        setMode(item.itemId, list)
                        Log.d(TAG, "remove room: $list")
                        item.setIcon(R.drawable.ic_baseline_favorite_24)
                        false
                    }
                }
            }
            R.id.action_profile -> {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        Log.d(TAG, "check list: $list")
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int, list: ArrayList<Language>) {
        Log.d(TAG, "check list2: $list")
        when (selectedMode) {
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
        setActionBarTitle(title)
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
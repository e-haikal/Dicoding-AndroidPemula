package com.siaptekno.yogyacampus

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var campusRecyclerView: RecyclerView
    private val list = ArrayList<Campus>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        campusRecyclerView = findViewById(R.id.campusRecyclerView)
//        campusRecyclerView.adapter
        campusRecyclerView.setHasFixedSize(true)

        list.addAll(getListCampus())
        showRecyclerList()
    }

    private fun getListCampus(): ArrayList<Campus> {
        val dataName = resources.getStringArray(R.array.data_name_array)
        val dataDescription = resources.getStringArray(R.array.data_description_array)
        val dataPhoto = resources.getStringArray(R.array.data_photo_array)

        val listCampus = ArrayList<Campus>()
        for (i in dataName.indices) {
            val campus = Campus(dataName[i], dataDescription[i], dataPhoto[i])
            listCampus.add(campus)
        }
        return listCampus
    }

    private fun showRecyclerList() {
        campusRecyclerView.layoutManager = LinearLayoutManager(this)
        val listCampusAdapter = ListCampusAdapter(list)
        campusRecyclerView.adapter = listCampusAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                // Navigate to About activity
                val intent = Intent(this, About::class.java)
                startActivity(intent)
                true
            }
            android.R.id.home -> {
                // Navigate back to the previous activity
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
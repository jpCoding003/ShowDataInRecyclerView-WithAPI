package com.tops.showdatainrecyclerview_withapi

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tops.showdatainrecyclerview_withapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var adapter: MyAdapter
        var item = ArrayList<String>()
        var text = String()

        adapter = MyAdapter(item)
        binding.DataListView.layoutManager = LinearLayoutManager(this)
        binding.DataListView.adapter = adapter

        binding.btnAdd.setOnClickListener {
            text = binding.etEnterData.text.toString()
            adapter.addItem(text)
            binding.etEnterData.text?.clear()
        }
    }
}
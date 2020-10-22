package com.tejas.expandablerecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val crops = listOf<CropModel>(
            CropModel(
                category = "Vegetables",
                crops = listOf("Potato", "Onion", "Tomato")
            ),
            CropModel(
                category = "Fruits",
                crops = listOf("Apple", "Banana", "Orange")
            )

        )

        val activity = this

        findViewById<RecyclerView>(R.id.recyclerView)?.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = CropAdapter(crops)
        }
    }

}
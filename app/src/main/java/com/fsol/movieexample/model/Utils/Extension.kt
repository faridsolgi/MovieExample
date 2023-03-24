package com.fsol.movieexample.model.Utils

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fsol.movieexample.view.MovieListAdapter


fun RecyclerView.initRecycler(layoutManager: RecyclerView.LayoutManager,adapter: RecyclerView.Adapter<*>){
    this.layoutManager = layoutManager
    this.adapter = adapter
}

fun AppCompatActivity.navHost(view:View) :NavHostFragment  =
    this.supportFragmentManager.findFragmentById(view.id) as NavHostFragment


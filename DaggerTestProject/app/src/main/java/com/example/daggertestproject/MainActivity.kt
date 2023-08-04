package com.example.daggertestproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.daggertestproject.dependency.DaggerApplicationGraph
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
     lateinit var mainViewModal: MainViewModal



    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
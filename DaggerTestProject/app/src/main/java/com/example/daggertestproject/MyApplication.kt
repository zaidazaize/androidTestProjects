package com.example.daggertestproject

import android.app.Application
import com.example.daggertestproject.dependency.DaggerApplicationGraph

class MyApplication : Application() {
    val appComponent = DaggerApplicationGraph.create()
}
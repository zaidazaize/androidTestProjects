package com.example.daggertestproject.dependency

import com.example.daggertestproject.MainActivity
import com.example.daggertestproject.data.UserRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationGraph {

    fun repository(): UserRepository

    fun inject(mainActivity: MainActivity)
}
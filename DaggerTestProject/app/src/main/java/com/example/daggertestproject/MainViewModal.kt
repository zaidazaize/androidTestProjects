package com.example.daggertestproject

import com.example.daggertestproject.data.UserRepository
import javax.inject.Inject

class MainViewModal @Inject constructor( val userRepository: UserRepository) {
}
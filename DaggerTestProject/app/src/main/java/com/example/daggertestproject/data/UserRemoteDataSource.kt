package com.example.daggertestproject.data

import com.example.daggertestproject.data.network.LoginRetrofitService
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(val loginRetrofitService: LoginRetrofitService) {
}
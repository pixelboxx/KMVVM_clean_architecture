package com.cashless.self_order.di

import android.app.Application
import com.cashless.self_order.data.source.local.dao.AppDatabase
import com.cashless.self_order.data.source.local.sharedprf.SharedPrefsApi
import com.cashless.self_order.data.source.local.sharedprf.SharedPrefsImpl
import com.cashless.self_order.data.source.remote.service.AppApi
import com.cashless.self_order.data.source.repositories.*
import com.cashless.self_order.util.rxAndroid.BaseSchedulerProvider
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * --------------------
 * Created by ThuanPx on 6/17/2019.
 * Screen name:
 * --------------------
 */

val repositoryModule = module {
    single { provideAppDBRepository(get(), get()) }
    single { provideTokenRepository(androidApplication()) }
    single { provideUserRepository(get(), get()) }
    single { provideUserAuthRepository(get()) }
}

fun provideTokenRepository(app: Application): TokenRepository {
    return TokenRepositoryImpl(
            SharedPrefsImpl(app))
}

fun provideAppDBRepository(appDatabase: AppDatabase, gson: Gson): AppDBRepository {
    return AppDBRepositoryImpl(appDatabase, gson)
}

fun provideUserRepository(api: AppApi, sharedPrefsApi: SharedPrefsApi): UserRepository {
    return UserRepositoryImpl(api,
            sharedPrefsApi, api)
}

fun provideUserAuthRepository(baseSchedulerProvider: BaseSchedulerProvider): UserAuthManager {
    return UserAuthManager(baseSchedulerProvider)
}
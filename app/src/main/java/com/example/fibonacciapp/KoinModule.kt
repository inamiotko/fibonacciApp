package com.example.fibonacciapp

import android.content.Context
import com.example.fibonacciapp.helpers.DataStoreRepository
import com.example.fibonacciapp.helpers.DataStoreRepositoryImpl
import com.example.fibonacciapp.viewmodel.FibonacciViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun koinModule(context: Context): Module = module {
    viewModel { FibonacciViewModel() }
    single<DataStoreRepository> { DataStoreRepositoryImpl(context) }

}
package com.example.historyscreen.di

import com.example.historyscreen.view.history.HistoryInteractor
import com.example.historyscreen.view.history.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectDependencies() = loadModules

private val loadModules by lazy {
    loadKoinModules(listOf(historyScreen))
}

val historyScreen = module {
    factory { HistoryInteractor(get()) }
    viewModel { HistoryViewModel(get()) }
}

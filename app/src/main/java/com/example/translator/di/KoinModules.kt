package com.example.translator.di

import androidx.room.Room
import com.example.repository.*
import com.example.translator.model.data.DataModel
import com.example.translator.room.HistoryDatabase
import com.example.translator.view.main.MainInteractor
import com.example.translator.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module


fun injectDependencies() = loadModules

private val loadModules by lazy {
    loadKoinModules(listOf(application, mainScreen))
}

val application = module {
    single { Room.databaseBuilder(get(), HistoryDatabase::class.java, "HistoryDB").build() }
    single { get<HistoryDatabase>().historyDao() }
    single<Repository<DataModel>> { RepositoryImpl(RetrofitImpl()) }
    single<RepositoryLocal<DataModel>> { RepositoryLocalImpl(RoomDataBaseImpl(get())) }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    viewModel { MainViewModel(get()) }
}



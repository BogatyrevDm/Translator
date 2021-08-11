package com.example.translator.di

import androidx.room.Room
import com.example.repository.*
import com.example.translator.model.data.DataModel
import com.example.translator.room.HistoryDatabase

import com.example.translator.view.history.HistoryInteractor
import com.example.translator.view.history.HistoryViewModel
import com.example.translator.view.history.search.HistorySearchInteractor
import com.example.translator.view.history.search.HistorySearchViewModel
import com.example.translator.view.main.MainInteractor
import com.example.translator.view.main.MainViewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDatabase::class.java, "HistoryDB").build() }
    single { get<HistoryDatabase>().historyDao() }
    single<Repository<DataModel>> { RepositoryImpl(RetrofitImpl()) }
    single<RepositoryLocal<DataModel>> { RepositoryLocalImpl(RoomDataBaseImpl(get())) }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

val historyScreen = module {
    factory { HistoryInteractor(get()) }
    factory { HistoryViewModel(get()) }
}
val historySearchScreen = module {
    factory { HistorySearchInteractor(get()) }
    factory { HistorySearchViewModel(get()) }
}
package com.example.translator.di

import androidx.room.Room
import com.example.translator.model.data.DataModel
import com.example.translator.model.datasource.RetrofitImpl
import com.example.translator.model.datasource.RoomDataBaseImpl
import com.example.translator.model.repository.Repository
import com.example.translator.model.repository.RepositoryImpl
import com.example.translator.model.repository.RepositoryLocal
import com.example.translator.model.repository.RepositoryLocalImpl
import com.example.translator.room.HistoryDatabase
import com.example.translator.view.history.HistoryInteractor
import com.example.translator.view.history.HistoryViewModel
import com.example.translator.view.main.MainInteractor
import com.example.translator.view.main.MainViewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDatabase::class.java, "HistoryDB").build() }
    single { get<HistoryDatabase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImpl(RetrofitImpl()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryLocalImpl(RoomDataBaseImpl(get())) }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

val historyScreen = module {
    factory { HistoryInteractor(get()) }
    factory { HistoryViewModel(get()) }
}
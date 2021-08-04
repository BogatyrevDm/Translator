package com.example.translator.di

import com.example.translator.model.data.DataModel
import com.example.translator.model.datasource.RetrofitImpl
import com.example.translator.model.datasource.RoomDataBaseImpl
import com.example.translator.model.repository.Repository
import com.example.translator.model.repository.RepositoryImpl
import com.example.translator.view.main.MainInteractor
import com.example.translator.view.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) { RepositoryImpl(RetrofitImpl()) }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) { RepositoryImpl(RoomDataBaseImpl()) }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}
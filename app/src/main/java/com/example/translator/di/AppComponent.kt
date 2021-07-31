package com.example.translator.di

import com.example.translator.view.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}
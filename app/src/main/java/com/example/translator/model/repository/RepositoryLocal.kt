package com.example.translator.model.repository

import com.example.translator.model.data.AppState

interface RepositoryLocal<T>:Repository<T> {
    suspend fun saveToDb(appState: AppState)
    suspend fun getDataByWord(word: String):T
}
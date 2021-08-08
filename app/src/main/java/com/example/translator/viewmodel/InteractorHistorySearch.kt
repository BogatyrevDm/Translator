package com.example.translator.viewmodel

interface InteractorHistorySearch<T> {
    suspend fun getDataByWord(word: String, fromRemoteSource: Boolean): T
}
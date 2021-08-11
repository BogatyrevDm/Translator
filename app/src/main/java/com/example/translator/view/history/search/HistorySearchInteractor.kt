package com.example.translator.view.history.search

import com.example.repository.RepositoryLocal
import com.example.translator.model.data.AppState
import com.example.translator.model.data.DataModel
import com.example.translator.viewmodel.InteractorHistorySearch

class HistorySearchInteractor(
    private val localRepository: RepositoryLocal<DataModel>
) : InteractorHistorySearch<AppState> {
    override suspend fun getDataByWord(word: String, fromRemoteSource: Boolean): AppState {
       val searchResult = localRepository.getDataByWord(word)
       return AppState.Success(listOf(searchResult))
    }
}
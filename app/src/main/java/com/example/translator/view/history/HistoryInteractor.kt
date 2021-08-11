package com.example.translator.view.history

import com.example.repository.RepositoryLocal
import com.example.translator.model.data.AppState
import com.example.translator.model.data.DataModel
import com.example.translator.viewmodel.Interactor

class HistoryInteractor(
    private val localRepository: RepositoryLocal<DataModel>
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
            return AppState.Success(localRepository.getData(word))
    }
}
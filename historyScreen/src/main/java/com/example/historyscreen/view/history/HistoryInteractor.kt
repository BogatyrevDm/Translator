package com.example.historyscreen.view.history

import com.example.core.viewmodel.Interactor
import com.example.repository.RepositoryLocal
import com.example.translator.model.data.AppState
import com.example.translator.model.data.DataModel

class HistoryInteractor(
    private val localRepository: RepositoryLocal<DataModel>
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
            return AppState.Success(localRepository.getData(word))
    }
}
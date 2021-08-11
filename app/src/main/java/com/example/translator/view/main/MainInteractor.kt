package com.example.translator.view.main

import com.example.repository.Repository
import com.example.repository.RepositoryLocal
import com.example.translator.model.data.AppState
import com.example.translator.model.data.DataModel
import com.example.translator.viewmodel.Interactor

class MainInteractor(
    private val remoteRepository: Repository<DataModel>,
    private val localRepository: RepositoryLocal<DataModel>
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(remoteRepository.getData(word))
            localRepository.saveToDb(appState)
        } else {
            appState = AppState.Success(localRepository.getData(word))
        }
        return appState
    }
}
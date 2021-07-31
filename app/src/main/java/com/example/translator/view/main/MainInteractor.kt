package com.example.translator.view.main

import com.example.translator.di.NAME_LOCAL
import com.example.translator.di.NAME_REMOTE
import com.example.translator.model.data.AppState
import com.example.translator.model.data.DataModel
import com.example.translator.model.repository.Repository
import com.example.translator.viewmodel.Interactor
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) private val remoteRepository: Repository<List<DataModel>>,
    @Named(NAME_LOCAL)  private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
       return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map{AppState.Success(it)}
        }
    }
}
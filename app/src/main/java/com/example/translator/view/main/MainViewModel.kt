package com.example.translator.view.main

import com.example.translator.model.data.AppState
import com.example.translator.viewmodel.BaseViewModel

class MainViewModel (
    private val interactor: MainInteractor
) : BaseViewModel<AppState>() {
    override fun getData(word: String, isOnline: Boolean) {
        liveDataToObserve.value = AppState.Loading(null)
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    {
                        liveDataToObserve.value = it
                    }, {
                        liveDataToObserve.value = AppState.Error(it)
                    }
                )
        )
    }
}
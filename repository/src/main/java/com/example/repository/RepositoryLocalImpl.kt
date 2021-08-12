package com.example.repository

import com.example.translator.model.data.AppState
import com.example.translator.model.data.DataModel

class RepositoryLocalImpl(private val dataSource: DataSourceLocal<DataModel>) :
    RepositoryLocal<DataModel> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun getDataByWord(word: String):DataModel {
        return dataSource.getDataByWord(word)
    }

    override suspend fun saveToDb(appState: AppState) {
        dataSource.saveToDB(appState)
    }


}
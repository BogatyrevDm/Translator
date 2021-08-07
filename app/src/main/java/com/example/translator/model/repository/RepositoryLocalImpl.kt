package com.example.translator.model.repository

import com.example.translator.model.data.AppState
import com.example.translator.model.data.DataModel
import com.example.translator.model.datasource.DataSourceLocal

class RepositoryLocalImpl(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDb(appState: AppState) {
        dataSource.saveToDB(appState)
    }

}
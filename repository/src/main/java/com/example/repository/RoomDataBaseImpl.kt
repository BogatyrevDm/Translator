package com.example.repository

import com.example.translator.model.data.AppState
import com.example.translator.model.data.DataModel
import com.example.translator.room.HistoryDao


class RoomDataBaseImpl(private val historyDao: HistoryDao) : DataSourceLocal<DataModel> {
    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }

    override suspend fun getDataByWord(word: String):DataModel {
        return mapHistoryEntityToSearchResultOneWord(historyDao.getDataByWord(word))
    }
}
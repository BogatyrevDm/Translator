package com.example.translator.model.datasource

import com.example.translator.model.data.DataModel


class DataSourceLocal (private val localProvider:RoomDataBaseImpl):DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> = localProvider.getData(word)
}
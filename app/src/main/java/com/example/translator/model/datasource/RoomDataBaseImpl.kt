package com.example.translator.model.datasource

import com.example.translator.model.data.DataModel


class RoomDataBaseImpl :DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        TODO("Not yet implemented")
    }
}
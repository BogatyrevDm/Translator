package com.example.repository

import com.example.translator.model.data.DataModel

class RepositoryImpl (private val dataSource: DataSource<DataModel>) :
    Repository<DataModel> {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}
package com.example.translator.model.repository

import com.example.translator.model.data.DataModel
import com.example.translator.model.datasource.DataSource

class RepositoryImpl (private val dataSource: DataSource<DataModel>) :
    Repository<DataModel> {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}
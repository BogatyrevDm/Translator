package com.example.translator.model.datasource

import com.example.translator.model.data.DataModel
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomDataBaseImpl @Inject constructor():DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("Not yet implemented")
    }
}
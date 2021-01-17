package br.com.luanadev.mysubscribers.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.luanadev.mysubscribers.data.db.entity.SubscriberEntiy

@Dao
interface SubscriberDao {
    @Insert
    suspend fun insert(subscriber: SubscriberEntiy): Long

    @Update
    suspend fun update(subscriber: SubscriberEntiy)

    @Query("DELETE FROM subscriber WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM subscriber")
    suspend fun deleteAll()

    @Query("SELECT * FROM subscriber")
    fun getAll():LiveData<List<SubscriberEntiy>>
}
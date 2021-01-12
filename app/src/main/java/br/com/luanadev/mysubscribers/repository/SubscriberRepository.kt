package br.com.luanadev.mysubscribers.repository

import androidx.lifecycle.LiveData
import br.com.luanadev.mysubscribers.data.db.entity.SubscriberEntiy

interface SubscriberRepository {

    suspend fun insertSubscriber(name: String, email: String): Long

    suspend fun updateSubscriber(id: Long, name: String, email: String)

    suspend fun deletSubscriber(id: Long)

    suspend fun deleteAllSubscribers()

    suspend fun getAllSubscribers(): LiveData<List<SubscriberEntiy>>
}
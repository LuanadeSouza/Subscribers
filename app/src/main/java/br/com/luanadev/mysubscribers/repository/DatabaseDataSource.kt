package br.com.luanadev.mysubscribers.repository

import androidx.lifecycle.LiveData
import br.com.luanadev.mysubscribers.data.db.dao.SubscriberDao
import br.com.luanadev.mysubscribers.data.db.entity.SubscriberEntiy

class DatabaseDataSource(
    private val subscriberDao: SubscriberDao
) : SubscriberRepository {
    override suspend fun insertSubscriber(name: String, email: String): Long {
        val subscriber = SubscriberEntiy(name = name, email = email)
        return subscriberDao.insert(subscriber)
    }

    override suspend fun updateSubscriber(id: Long, name: String, email: String) {
        val subscriber = SubscriberEntiy(id = id, name = name, email = email)
        subscriberDao.update(subscriber)
    }

    override suspend fun deletSubscriber(id: Long) {
        subscriberDao.delete(id = id)
    }

    override suspend fun deleteAllSubscribers() {
        subscriberDao.deleteAll()
    }

    override suspend fun getAllSubscribers(): LiveData<List<SubscriberEntiy>> {
        return subscriberDao.getAll()
    }
}
package br.com.luanadev.mysubscribers.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.luanadev.mysubscribers.data.db.dao.SubscriberDao
import br.com.luanadev.mysubscribers.data.db.entity.SubscriberEntiy

@Database(entities = [SubscriberEntiy::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val subscriberDao: SubscriberDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance: AppDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()
                }
                return instance
            }
        }
    }
}

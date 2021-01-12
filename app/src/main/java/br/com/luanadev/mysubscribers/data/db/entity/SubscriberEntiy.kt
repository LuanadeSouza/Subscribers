package br.com.luanadev.mysubscribers.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriber")
data class SubscriberEntiy (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val email: String

)
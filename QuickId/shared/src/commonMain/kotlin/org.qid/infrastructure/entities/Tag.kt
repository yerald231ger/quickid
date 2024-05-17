package org.qid.infrastructure.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "tags")
data class Tag(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @SerialName("file_id")
    val fileId: Int,

    @SerialName("name")
    val name: String
)
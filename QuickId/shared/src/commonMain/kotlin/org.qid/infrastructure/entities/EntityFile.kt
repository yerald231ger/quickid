package org.qid.infrastructure.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "entity_files")
data class EntityFile(

    @PrimaryKey(autoGenerate = false)
    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val name: String,

    @SerialName("description")
    val description: String,

    @SerialName("importance")
    val importance: Int,

    @SerialName("identity_file_type")
    val identityFileType: Int,

    @SerialName("path")
    val path: String
)
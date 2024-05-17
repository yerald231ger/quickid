package org.qid.infrastructure.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.qid.core.constants.IdentityFileType
import org.qid.core.models.IdentityFile

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

fun IdentityFile.toEntityFile(): EntityFile {
    return EntityFile(
        id = this.id,
        name = this.name,
        description = this.description,
        importance = this.importance,
        identityFileType = this.identityFileType.ordinal,
        path = this.path
    )
}

fun EntityFile.toIdentityFile(): IdentityFile {
    IdentityFile.create(this.id).also { identityFile ->
        identityFile.name = this.name
        identityFile.description = this.description
        identityFile.importance = this.importance
        identityFile.identityFileType = IdentityFileType.fromInt(this.identityFileType)
        identityFile.path = this.path
        return identityFile
    }
}
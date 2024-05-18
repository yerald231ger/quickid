package org.qid.core.models

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.qid.core.constants.IdentityFileType


class IdentityFile private constructor(
    val id: String,
    val createdAt: LocalDateTime,
    var name: String
) {

    var description: String = ""
    var importance: Int = 0
    var identityFileType: IdentityFileType = IdentityFileType.ID
    var size: Array<Byte> = emptyArray()
    var tags: List<String> = emptyList()
    var path: String = ""
    var fileExtension: String = ""

    override fun toString(): String {
        return "IdentityFile(id=$id, name='$name', description='$description', importance=$importance, identityFileType=$identityFileType, size=${size.contentToString()}, tags=${tags.size}, path='$path')"
    }

    companion object {
        fun create(id: String, name: String): IdentityFile {
            val currentMoment: Instant = Clock.System.now()
            val currentMomentLocal: LocalDateTime =
                currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
            return IdentityFile(id, currentMomentLocal, name)
        }

        fun createNameForImageFile(dateFormatted: String, fileExtension: String): String {
            return "qid.image.${dateFormatted}.${fileExtension}"
        }

        fun createNameForScannedFile(dateFormatted: String, fileExtension: String)
                : String {
            return "qid.scanned.${dateFormatted}.${fileExtension}"
        }

        fun createNameForDocumentFile(dateFormatted: String, fileExtension: String): String {
            return "qid.document.${dateFormatted}.${fileExtension}"
        }
    }
}
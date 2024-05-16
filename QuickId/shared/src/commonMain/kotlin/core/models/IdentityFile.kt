package core.models

import core.constants.IdentityFileType


class IdentityFile(val name: String) {
    var description: String = ""
    var importance: Int = 0
    var identityFileType: IdentityFileType = IdentityFileType.ID
    var size: Array<Byte> = emptyArray()
    var tags: List<String> = emptyList()
    var path: String = ""

    companion object {
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
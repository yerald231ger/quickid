package core.models

import core.constants.IdentityFileType

class IdentityFile(val name: String) {
    var description: String = ""
    var importance: Int = 0
    var identityFileType: IdentityFileType = IdentityFileType.ID
    var size: Array<Byte> = emptyArray()
    var tags: List<String> = emptyList()

    companion object {
        fun createNameForImageFile(dateFormatted: String): String {
            return "qid.image.${dateFormatted}"
        }
    }
}
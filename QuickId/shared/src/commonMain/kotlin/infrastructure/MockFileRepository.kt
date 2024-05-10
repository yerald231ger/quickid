package infrastructure

import core.constants.IdentityFileType
import core.infrastructure.FileRepository
import core.models.IdentityFile

class MockFileRepository : FileRepository {

    private var identityFiles = listOf(
        IdentityFile("Pasaporte").apply {
            importance = 0
            identityFileType = IdentityFileType.PASSPORT
            tags = listOf("tag1", "tag2")
        },
        IdentityFile("Ine").apply {
            importance = 0
            tags = listOf("tag3", "tag4")
        },
        IdentityFile("Licencia de conducir").apply {
            importance = 0
            identityFileType = IdentityFileType.DRIVER_LICENSE
            tags = listOf("tag5", "tag6")
        },
        IdentityFile("Acta de nacimiento").apply {
            importance = 0
            tags = listOf("tag7", "tag8")
        },
        IdentityFile("Comprobante de domicilio").apply {
            importance = 0
            tags = listOf("tag9", "tag10")
        },
        IdentityFile("Curp").apply {
            importance = 0
            tags = listOf("tag11", "tag12")
        })

    override fun getTopFiles(): List<IdentityFile> {
        return identityFiles.filter { it.importance == 0 }
    }

    override fun getRecentFiles(): List<IdentityFile> {
        return identityFiles.filter { it.importance == 0 }
    }

    override fun getFiles(id: Int?): List<IdentityFile> {
        if (id == null) return identityFiles

        return identityFiles.filter { it.importance == id }
    }

    override fun saveFile(identityFile: IdentityFile) {
        TODO("Not yet implemented")
    }

    override fun deleteFile(name: String) {
        TODO("Not yet implemented")
    }
}
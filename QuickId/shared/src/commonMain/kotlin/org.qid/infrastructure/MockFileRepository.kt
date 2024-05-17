package org.qid.infrastructure

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import org.qid.core.constants.IdentityFileType
import org.qid.core.infrastructure.FileRepository
import org.qid.core.models.IdentityFile

class MockFileRepository : FileRepository {

    private var identityFiles = listOf(
        listOf(
            IdentityFile.create(1).apply {
                name = "Pasaporte"
                importance = 0
                identityFileType = IdentityFileType.PASSPORT
                tags = listOf("tag1", "tag2")
            },
            IdentityFile.create(2).apply {
                name = "Ine"
                importance = 0
                tags = listOf("tag3", "tag4")
            },
            IdentityFile.create(3).apply {
                name = "Licencia de conducir"
                importance = 0
                identityFileType = IdentityFileType.DRIVER_LICENSE
                tags = listOf("tag5", "tag6")
            },
            IdentityFile.create(4).apply {
                name = "Acta de nacimiento"
                importance = 0
                tags = listOf("tag7", "tag8")
            },
            IdentityFile.create(5).apply {
                name = "Comprobante de domicilio"
                importance = 0
                tags = listOf("tag9", "tag10")
            },
            IdentityFile.create(6).apply {
                name = "Curp"
                importance = 0
                tags = listOf("tag11", "tag12")
            })
    )

    val flow = identityFiles.asFlow()

    override fun getTopFiles(): Flow<List<IdentityFile>> {
        return flow
    }

    override fun getRecentFiles(): Flow<List<IdentityFile>> {
        return flow
    }

    override fun getFiles(id: Long?): Flow<List<IdentityFile>> {
        if (id == null) return flow

        return flow.map { files ->
            files.filter {
                it.id == id
            }
        }
    }

    override fun saveFile(identityFile: IdentityFile) {
        identityFiles = listOf(identityFiles[0].plus(identityFile))
    }

    override fun deleteFile(identityFile: IdentityFile) {
        identityFiles = listOf(identityFiles[0].minus(identityFile))
    }


}
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
            IdentityFile.create("0f580c14-9b8f-4bb9-b7b0-e0d52c8c00d2", "passport.jpg").apply {
                importance = 0
                identityFileType = IdentityFileType.PASSPORT
                tags = listOf("tag1", "tag2")
            },
            IdentityFile.create("0c57ff3c-4f1f-49af-8060-7be98b48b3f3", "ine.jpg").apply {
                importance = 0
                tags = listOf("tag3", "tag4")
            },
            IdentityFile.create("f92fab4c-bca5-4c94-9081-a1add475eb6b", "driverLicense.jpg").apply {
                importance = 0
                identityFileType = IdentityFileType.DRIVER_LICENSE
                tags = listOf("tag5", "tag6")
            },
            IdentityFile.create("d57e3955-2609-49c7-b95c-3ed706e13300", "birthdayDocument.pdf").apply {
                importance = 0
                tags = listOf("tag7", "tag8")
            },
            IdentityFile.create("87d3436c-6cb2-482f-9012-493d7d777638", "address.pdf").apply {
                importance = 0
                tags = listOf("tag9", "tag10")
            },
            IdentityFile.create("a0e97dce-c182-47c6-8490-5e9764029f26", "curp.jpg").apply {
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

    override fun getFiles(id: String?): Flow<List<IdentityFile>> {
        if (id == null) return flow

        return flow.map { files ->
            files.filter {
                it.id == id
            }
        }
    }

    override suspend fun saveFile(identityFile: IdentityFile) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFile(identityFile: IdentityFile) {
        TODO("Not yet implemented")
    }

    override suspend fun countFiles(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun updateFile(identityFile: IdentityFile) {
        TODO("Not yet implemented")
    }


}
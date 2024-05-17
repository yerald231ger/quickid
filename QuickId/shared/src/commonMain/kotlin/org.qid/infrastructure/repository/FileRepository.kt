package org.qid.infrastructure.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.qid.core.infrastructure.FileRepository
import org.qid.core.models.IdentityFile
import org.qid.infrastructure.database.QuickIdDatabase
import org.qid.infrastructure.entities.toEntityFile
import org.qid.infrastructure.entities.toIdentityFile

class QuickIdFileRepository(
    private var database: QuickIdDatabase,
    private var scope: CoroutineScope
) : FileRepository {
    override fun getTopFiles(): Flow<List<IdentityFile>> {
        return database.fileDao().getAllAsFlow().map { list ->
            list.map {
                it.toIdentityFile()
            }
        }
    }

    override fun getRecentFiles(): Flow<List<IdentityFile>> {
        return database.fileDao().getAllAsFlow().map { list ->
            list.map {
                it.toIdentityFile()
            }
        }
    }

    override fun getFiles(id: Long?): Flow<List<IdentityFile>> {

        if (id == null) {
            return database.fileDao().getAllAsFlow().map { list ->
                list.map {
                    it.toIdentityFile()
                }
            }
        } else
            return database.fileDao().getAllAsFlow().map { list ->
                list.filter { it.id == id }.map {
                    it.toIdentityFile()
                }
            }
    }

    override suspend fun saveFile(identityFile: IdentityFile) {
        database.fileDao().insert(identityFile.toEntityFile())
    }

    override suspend fun deleteFile(identityFile: IdentityFile) {
        database.fileDao().delete(identityFile.toEntityFile())
    }

    override suspend fun countFiles(): Int {
        return database.fileDao().count()
    }

    override suspend fun updateFile(identityFile: IdentityFile) {
        TODO("Not yet implemented")
    }

}
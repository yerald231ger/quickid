package org.qid.core.infrastructure

import kotlinx.coroutines.flow.Flow
import org.qid.core.models.IdentityFile

interface FileRepository {
    fun getTopFiles(): Flow<List<IdentityFile>>
    fun getRecentFiles(): Flow<List<IdentityFile>>
    fun getFiles(id: Long?): Flow<List<IdentityFile>>
    fun saveFile(identityFile: IdentityFile)
    fun deleteFile(identityFile: IdentityFile)
}
package org.qid.core.infrastructure

import org.qid.core.models.IdentityFile

interface FileRepository {
    fun getTopFiles(): List<IdentityFile>
    fun getRecentFiles(): List<IdentityFile>
    fun getFiles(id: Int?): List<IdentityFile>
    fun saveFile(identityFile: IdentityFile)
    fun deleteFile(name: String)
}
package core.infrastructure

import core.models.IdentityFile

interface FileRepository {
    fun getTopFiles(): List<IdentityFile>
    fun getRecentFiles(): List<IdentityFile>
    fun getFiles(id: Int?): List<IdentityFile>
    fun saveFile(identityFile: IdentityFile)
    fun deleteFile(name: String)
}
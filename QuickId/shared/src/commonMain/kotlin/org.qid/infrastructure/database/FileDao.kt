package org.qid.infrastructure.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.qid.infrastructure.entities.EntityFile

@Dao
interface FileDao {
    @Query("SELECT * FROM entity_files")
    fun getAllAsFlow(): Flow<List<EntityFile>>

    @Query("SELECT COUNT(*) as count FROM entity_files")
    suspend fun count(): Int

    @Query("SELECT * FROM entity_files WHERE id in (:id)")
    suspend fun findById(id: Long): EntityFile?

    @Query("SELECT * FROM entity_files WHERE name = :name")
    suspend fun findByName(name: String): EntityFile?

    @Insert
    suspend fun insert(file: EntityFile)

    @Delete
    suspend fun delete(file: EntityFile)

    @Update
    suspend fun update(file: EntityFile)

}
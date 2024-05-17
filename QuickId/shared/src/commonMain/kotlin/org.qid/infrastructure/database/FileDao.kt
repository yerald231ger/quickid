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
    @Insert
    fun insert(file: EntityFile)
    @Query("SELECT * FROM entity_files")
    fun getAllAsFlow(): Flow<List<EntityFile>>

    @Query("SELECT COUNT(*) as count FROM entity_files")
    fun count(): Int
    @Query("SELECT * FROM entity_files WHERE id in (:id)")
    fun findById(id: Long): EntityFile?

    @Query("SELECT * FROM entity_files WHERE name = :name")
    fun findByName(name: String): EntityFile?

    @Delete
    fun delete(file: EntityFile)

    @Update
    fun update(file: EntityFile)

}
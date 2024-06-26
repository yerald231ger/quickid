package org.qid.infrastructure.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import org.qid.infrastructure.entities.EntityFile
import org.qid.infrastructure.entities.Tag

@Database(
    entities = [
        EntityFile::class,
        Tag::class
    ],
    version = 3,
    autoMigrations = [
        AutoMigration(
            from = 2,
            to = 3,
            spec = QuickIdDatabase.RenameIdentityFileType::class
        )
    ]
)
abstract class QuickIdDatabase : RoomDatabase() {
    abstract fun fileDao(): FileDao
    abstract fun tagDao(): TagDao

    @RenameColumn(
        tableName = "entity_files",
        fromColumnName = "identityFileType",
        toColumnName = "identity_file_type"
    )
    class RenameIdentityFileType : AutoMigrationSpec
}

internal const val dbFileName = "QuickId.db"
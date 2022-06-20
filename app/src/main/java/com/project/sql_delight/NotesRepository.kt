package com.project.sql_delight

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import demo.notesdb.NotesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NotesRepository(database: NotesDatabase):NoteDataSource {
    private val queries = database.notesEntityQueries
    override suspend fun insertNote(title: String, description: String, id: Long?) {
        return withContext(Dispatchers.IO){
            queries.insertnotes(id, title, description)
        }
    }

    override fun getAllNotes(): Flow<List<NotesEntity>> {
        return queries.getAllNotes().asFlow().mapToList()
    }

    override suspend fun getNoteById(id: Long): NotesEntity? {
        return withContext(Dispatchers.IO){
            queries.getNoteByid(id).executeAsOneOrNull()
        }
    }

    override suspend fun deleteNoteById(id: Long) {
       return withContext(Dispatchers.IO){
           queries.deletepersonByid(id)
       }
    }
}
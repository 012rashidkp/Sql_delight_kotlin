package com.project.sql_delight

import demo.notesdb.NotesEntity
import kotlinx.coroutines.flow.Flow

interface NoteDataSource {
    suspend fun insertNote(title: String, description: String,id: Long? = null)

    fun getAllNotes(): Flow<List<NotesEntity>>

    suspend fun getNoteById(id: Long): NotesEntity?

    suspend fun deleteNoteById(id: Long)
}
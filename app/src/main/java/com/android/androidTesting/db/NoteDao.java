package com.android.androidTesting.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note ORDER BY date DESC")
    List<Note> getAllNotes();

    @Query("SELECT * FROM note WHERE nid == :noteid LIMIT 1")
    Note getNoteById(int noteid);

    @Query("SELECT * FROM note WHERE date >= :date1 AND date <= :date2")
    List<Note> getNotesBetweenDates(long date1, long date2);

    @Query("SELECT * FROM note WHERE date >= :date1")
    List<Note> getNotesBetweenDates(long date1);

    @Query("SELECT * FROM note WHERE description LIKE :searchTerm")
    List<Note> getNotesWithDescription(String searchTerm);

//    @Query("SELECT * FROM note n WHERE n.nid IN (SELECT lt.nid FROM linktable lt WHERE lt.tid IN :filterValues)")
//    List<Note> getNotesWithTag(List<String> filterValues);

    @RawQuery
    List<Note> filterNotes(SupportSQLiteQuery query);

    @Insert
    long insertNote(Note note);

    @Delete
    void delete(Note note);

    @Update
    void update(Note note);
}

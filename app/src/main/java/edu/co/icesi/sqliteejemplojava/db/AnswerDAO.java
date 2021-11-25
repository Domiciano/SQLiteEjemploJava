package edu.co.icesi.sqliteejemplojava.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.co.icesi.sqliteejemplojava.model.Answer;

@Dao
public interface AnswerDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Answer answer);

    @Query("SELECT * FROM answers WHERE userid = :userid AND isUploaded = 0")
    List<Answer> getAllNotUploaded(String userid);



    @Query("UPDATE answers SET isUploaded = 1 WHERE userid = :id")
    void updateAllAnswers(String id);

}

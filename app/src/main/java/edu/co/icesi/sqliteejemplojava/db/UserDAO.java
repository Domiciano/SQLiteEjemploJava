package edu.co.icesi.sqliteejemplojava.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import edu.co.icesi.sqliteejemplojava.model.User;
import edu.co.icesi.sqliteejemplojava.model.UserWithAnswers;

@Dao
public interface UserDAO {

    //Las consultas
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users WHERE id = :id")
    User getById(String id);

    @Transaction
    @Query("SELECT users.* FROM users INNER JOIN answers WHERE users.id = :id AND answers.isUploaded = 0")
    UserWithAnswers getUserWithAnswersNotSynced(String id);


}

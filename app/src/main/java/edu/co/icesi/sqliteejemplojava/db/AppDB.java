package edu.co.icesi.sqliteejemplojava.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import edu.co.icesi.sqliteejemplojava.model.Answer;
import edu.co.icesi.sqliteejemplojava.model.User;

@Database(entities = {
        User.class,
        Answer.class

}, version = 1)
public abstract class AppDB extends RoomDatabase {

    private static AppDB instance = null;

    public static AppDB getInstance(Context context){
        if(instance == null){
            //Crear la instancia
            instance = Room.databaseBuilder(context, AppDB.class, "appdbjava").allowMainThreadQueries().build();
        }
        return instance;
    }


    //Declarar los DAO
    public abstract UserDAO userDAO();

    public abstract AnswerDAO answerDAO();


}

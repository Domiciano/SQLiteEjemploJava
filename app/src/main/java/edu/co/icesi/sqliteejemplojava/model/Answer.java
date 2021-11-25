package edu.co.icesi.sqliteejemplojava.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "answers")
public class Answer {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="id")
    private String natID;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="age")
    private int age;

    @ColumnInfo(name="strata")
    private int strata;

    @ColumnInfo(name="mostUsedSocialNet")
    private String mostUsedSocialNet;

    @ColumnInfo(name="mostConsumedDrink")
    private String mostConsumedDrink;

    @ColumnInfo(name="exerciseLevel")
    private int exerciseLevel;

    @ColumnInfo(name="isUploaded")
    private boolean isUploaded;

    @ColumnInfo(name = "userid")
    private String userID;

    public Answer(){}

    @Ignore
    public Answer(@NonNull String natID, String name, int age, int strata, String mostUsedSocialNet, String mostConsumedDrink, int exerciseLevel, boolean isUploaded, String userID) {
        this.natID = natID;
        this.name = name;
        this.age = age;
        this.strata = strata;
        this.mostUsedSocialNet = mostUsedSocialNet;
        this.mostConsumedDrink = mostConsumedDrink;
        this.exerciseLevel = exerciseLevel;
        this.isUploaded = isUploaded;
        this.userID = userID;
    }

    @NonNull
    public String getNatID() {
        return natID;
    }

    public void setNatID(@NonNull String natID) {
        this.natID = natID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStrata() {
        return strata;
    }

    public void setStrata(int strata) {
        this.strata = strata;
    }

    public String getMostUsedSocialNet() {
        return mostUsedSocialNet;
    }

    public void setMostUsedSocialNet(String mostUsedSocialNet) {
        this.mostUsedSocialNet = mostUsedSocialNet;
    }

    public String getMostConsumedDrink() {
        return mostConsumedDrink;
    }

    public void setMostConsumedDrink(String mostConsumedDrink) {
        this.mostConsumedDrink = mostConsumedDrink;
    }

    public int getExerciseLevel() {
        return exerciseLevel;
    }

    public void setExerciseLevel(int exerciseLevel) {
        this.exerciseLevel = exerciseLevel;
    }

    public boolean isUploaded() {
        return isUploaded;
    }

    public void setUploaded(boolean uploaded) {
        isUploaded = uploaded;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}

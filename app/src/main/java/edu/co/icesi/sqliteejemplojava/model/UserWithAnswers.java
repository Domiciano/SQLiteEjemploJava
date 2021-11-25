package edu.co.icesi.sqliteejemplojava.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithAnswers {

    @Embedded
    private User user;
    @Relation(
            parentColumn = "id",
            entityColumn = "userid"
    )
    private List<Answer> answers;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}

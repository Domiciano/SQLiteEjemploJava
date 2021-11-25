package edu.co.icesi.sqliteejemplojava;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.List;

import edu.co.icesi.sqliteejemplojava.databinding.ActivitySurveyBinding;
import edu.co.icesi.sqliteejemplojava.db.AppDB;
import edu.co.icesi.sqliteejemplojava.model.Answer;
import edu.co.icesi.sqliteejemplojava.model.User;
import edu.co.icesi.sqliteejemplojava.model.UserWithAnswers;

public class SurveyActivity extends AppCompatActivity {

    private ActivitySurveyBinding binding;
    private String id;
    private AppDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySurveyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        id = getIntent().getExtras().getString("id");
        db = AppDB.getInstance(getApplicationContext());

        binding.submitBtn.setOnClickListener(
                v->{
                    submit();
                }
        );

        binding.syncBtn.setOnClickListener(
                v->{
                    sync();
                }
        );

        updateUI();
    }

    private void sync() {
        UserWithAnswers answers = db.userDAO().getUserWithAnswersNotSynced(id);
        User user = answers.getUser();
        List<Answer> ans = answers.getAnswers();
        Log.e(">>>","");

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        WriteBatch batch = firestore.batch();

        DocumentReference userref = firestore.collection("pollster").document(user.getId());
        batch.set(userref, user);

        for(Answer a : ans){
            DocumentReference dataRef = firestore.collection("data").document(a.getNatID());
            batch.set(dataRef, a);
        }

        batch.commit().addOnSuccessListener(
                task->{
                    //Marcar los datos como subidos
                    Toast.makeText(this, "Datos sincronizados con exito", Toast.LENGTH_SHORT).show();
                    db.answerDAO().updateAllAnswers(id);
                    updateUI();
                }
        );

    }

    public void updateUI(){
        List<Answer> answers = db.answerDAO().getAllNotUploaded(id);
        binding.syncBtn.setText("SYNC ("+answers.size()+")");
    }

    public void submit(){

        RadioButton socialRB = findViewById(binding.socialNetRG.getCheckedRadioButtonId());
        RadioButton drinkRB = findViewById(binding.drinkRG.getCheckedRadioButtonId());

        Answer answer = new Answer(
                binding.natIDET.getText().toString(),
                binding.nameET.getText().toString(),
                Integer.parseInt(binding.ageET.getText().toString()),
                Integer.parseInt(binding.strataET.getText().toString()),
                socialRB.getText().toString(),
                drinkRB.getText().toString(),
                Integer.parseInt(binding.exerciseET.getText().toString()),
                false,
                id
        );

        db.answerDAO().insert(answer);
        updateUI();

    }


}
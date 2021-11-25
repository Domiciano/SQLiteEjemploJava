package edu.co.icesi.sqliteejemplojava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import edu.co.icesi.sqliteejemplojava.databinding.ActivityMainBinding;
import edu.co.icesi.sqliteejemplojava.db.AppDB;
import edu.co.icesi.sqliteejemplojava.model.User;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDB.getInstance(getApplicationContext());

        binding.loginBtn.setOnClickListener(
                v->{
                    String cedula = binding.loginIDET.getText().toString();
                    String nombre = binding.loginNameET.getText().toString();
                    User user = new User(cedula, nombre);

                    db.userDAO().insert(user);

                    List<User> users = db.userDAO().getAll();
                    for(User u : users){
                        Log.e(">>>", u.getName());
                    }

                    Intent intent = new Intent(this, SurveyActivity.class);
                    intent.putExtra("id", cedula);
                    startActivity(intent);
                }
        );

        binding.loginNameET.setOnFocusChangeListener(
                (view,focus)->{
                    if(focus){
                        String id = binding.loginIDET.getText().toString();
                        User user = db.userDAO().getById(id);
                        if(user != null) binding.loginNameET.setText(user.getName());
                    }
                }
        );

    }
}
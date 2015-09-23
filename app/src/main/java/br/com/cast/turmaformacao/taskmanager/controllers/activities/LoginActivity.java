package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.User;
import br.com.cast.turmaformacao.taskmanager.model.persistence.UserRepository;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonNewUser;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindEditTextLogin();
        bindEditTextPassword();
        bindButtonLogin();
        bindButtonNewUser();
    }

    private void bindButtonLogin() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkLogin()) {
                    Intent redirectToTaskList = new Intent(LoginActivity.this, TaskListActivity.class);
                    startActivity(redirectToTaskList);
                    finish();
                }
                else
                    Toast.makeText(LoginActivity.this, R.string.msg_user_pass_incorrect, Toast.LENGTH_LONG).show();

            }
        });
    }

    public boolean checkLogin(){

        User checkUser = new User();

        checkUser.setUser(editTextLogin.getText().toString());
        checkUser.setPassword(editTextPassword.getText().toString());

        user = UserRepository.checkLogin(checkUser);

        if(user != null)
            return true;
        else
            return false;
    }

    private void bindButtonNewUser() {
        buttonNewUser = (Button) findViewById(R.id.buttonNewUser);

        buttonNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent redirectToNewUser = new Intent(LoginActivity.this, NewUserActivity.class);
                startActivity(redirectToNewUser);
            }
        });
    }

    private void bindEditTextPassword() {
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    private void bindEditTextLogin() {
        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
    }

}

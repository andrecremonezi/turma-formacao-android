package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.User;
import br.com.cast.turmaformacao.taskmanager.model.services.UserBussinessServices;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

public class NewUserActivity extends AppCompatActivity {
    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonCriar;
    private User user;
    public static final String PARAM_TASK = "PARAM_TASK";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        initUser();
        bindEditTextLogin();
        bindEditTextPassword();
        bindButtonCriar();
    }

    private void initUser() {
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            this.user = (User) extras.getParcelable(PARAM_TASK);
        }

        this.user = this.user == null ? new User() : this.user;
    }

    private void bindButtonCriar() {
        buttonCriar = (Button) findViewById(R.id.buttonCriar);
        buttonCriar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String requiredMessage = getResources().getString(R.string.msg_required);

                if (!FormHelper.validateRequired(requiredMessage, editTextLogin, editTextPassword)) {
                    bindUser();
                    UserBussinessServices.save(user);
                    Toast.makeText(NewUserActivity.this, R.string.msg_user_create_sucess, Toast.LENGTH_LONG).show();
                    NewUserActivity.this.finish();
                }
            }
        });
    }

    public void bindUser(){
        user.setUser(editTextLogin.getText().toString());
        user.setPassword(editTextPassword.getText().toString());
    }

    private void bindEditTextLogin() {
        editTextLogin = (EditText) findViewById(R.id.editTextUser);
        editTextLogin.setText(user.getUser() == null ? "" : user.getUser());
    }

    private void bindEditTextPassword() {
        editTextPassword = (EditText) findViewById(R.id.editTextPass);
        editTextPassword.setText(user.getPassword() == null ? "" : user.getPassword());
    }

}

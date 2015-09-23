package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Address;
import br.com.cast.turmaformacao.taskmanager.model.entities.User;

import br.com.cast.turmaformacao.taskmanager.model.http.AddressService;
import br.com.cast.turmaformacao.taskmanager.model.services.UserBussinessServices;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

public class NewUserActivity extends AppCompatActivity {
    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonCriar;
    private Button buttonSearch;
    private EditText editTextZipCode;
    private EditText editTextType;
    private EditText editTextStreet;
    private EditText editTextNeighborhood;
    private EditText editTextCity;
    private EditText editTextState;
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
        bindButtonSearch();
        bindAddress();
    }

    private class GetAddressTask extends AsyncTask<String, Void, Address> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Address doInBackground(String... params) {
            return AddressService.getAddressByZipCode(params[0]);
        }

        @Override
        protected void onPostExecute(Address address) {
            super.onPostExecute(address);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    private void bindButtonSearch() {
        buttonSearch = (Button) findViewById(R.id.buttonBuscarCep);
        buttonSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new GetAddressTask().execute(editTextZipCode.getText().toString());
            }
        });
    }

    private void bindAddress() {
        editTextZipCode = (EditText) findViewById(R.id.editCep);
        editTextType = (EditText) findViewById(R.id.editType);
        editTextStreet = (EditText) findViewById(R.id.editStreet);
        editTextNeighborhood = (EditText) findViewById(R.id.editNeighborhood);
        editTextCity = (EditText) findViewById(R.id.editCity);
        editTextState = (EditText) findViewById(R.id.editState);
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

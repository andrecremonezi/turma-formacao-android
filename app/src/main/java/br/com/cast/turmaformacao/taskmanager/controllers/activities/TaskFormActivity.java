package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.controllers.adpters.LabelListAdapter;
import br.com.cast.turmaformacao.taskmanager.model.entities.Address;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.http.AddressService;
import br.com.cast.turmaformacao.taskmanager.model.http.TaskService;
import br.com.cast.turmaformacao.taskmanager.model.services.LabelBusinessServices;
import br.com.cast.turmaformacao.taskmanager.model.services.TaskBusinessServices;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

public class TaskFormActivity extends AppCompatActivity {
    private Spinner spinner;
    private EditText editTextName;
    private EditText editTextDescription;
    private Button buttonSave;
    private Button buttonNewLabel;
    private Task task;
    private ProgressDialog progressDialog;
    public static final String PARAM_TASK = "PARAM_TASK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        initTask();
        bindSpinner();
        bindEditTextName();
        bindEditTextDescription();
        bindButtonNewLabel();
        bindButtonSave();
    }


    private class GetTaskWeb extends AsyncTask<String, Void, Task> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(TaskFormActivity.this);
            progressDialog.setMessage("Carregando");
            progressDialog.show();

        }

        @Override
        protected Task doInBackground(String... params) {

            return TaskService.getTaskByWebId(Long.parseLong(params[0]));
        }

        @Override
        protected void onPostExecute(Task task) {
            super.onPostExecute(task);
            progressDialog.dismiss();
            editTextName.setText(task.getName().toString());
            editTextDescription.setText(task.getDescription().toString());
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        onUpdateSpinnerList();
    }

    private void bindSpinner() {
        spinner = (Spinner) findViewById(R.id.spinnerLabel);
        onUpdateSpinnerList();
    }

    private void onUpdateSpinnerList() {
        List<Label> labels = LabelBusinessServices.findAll();
        spinner.setAdapter(new LabelListAdapter(TaskFormActivity.this, labels));
    }

    private void initTask() {
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            this.task = (Task) extras.getParcelable(PARAM_TASK);
        }

        this.task = this.task == null ? new Task() : this.task;
    }

    private void bindButtonSave() {
        buttonSave = (Button) findViewById(R.id.button_saveTask);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String requiredMessage = TaskFormActivity.this.getString(R.string.msg_required);

                if (!FormHelper.validateRequired(requiredMessage, editTextName)) {
                    bindTask();
                    TaskBusinessServices.save(task);
                    Toast.makeText(TaskFormActivity.this, getString(R.string.msg_save_sucessfull), Toast.LENGTH_LONG).show();
                    TaskFormActivity.this.finish();
                }
            }
        });
    }


    private void bindButtonNewLabel(){
        buttonNewLabel = (Button) findViewById(R.id.button_newLabel);

        buttonNewLabel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent redirectToLabelForm = new Intent(TaskFormActivity.this, LabelFormActivity.class);
                startActivity(redirectToLabelForm);
            }
        });
    }


    private void bindTask() {
        task.setName(editTextName.getText().toString());
        task.setDescription(editTextDescription.getText().toString());
        Label label = (Label) spinner.getSelectedItem();
        task.setLabel(label);
        }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextTaskName);
        editTextName.setText(task.getName() == null ? "" : task.getName());

    }

    private void bindEditTextDescription() {
        editTextDescription = (EditText) findViewById(R.id.editTextTaskDescription);
        editTextDescription.setText(task.getDescription() == null ? "" : task.getDescription());
    }


}

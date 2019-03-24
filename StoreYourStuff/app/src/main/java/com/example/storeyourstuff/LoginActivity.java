package com.example.storeyourstuff;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;


import Helpers.Validation;
import SQL.SQLiteHelperLogIn;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;
    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private AppCompatButton appCompatButtonLogin;
    private AppCompatTextView textViewLinkRegister;
    private Validation validation;
    private SQLiteHelperLogIn sqlitehelperlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        init();

    }


    private void init() {

        nestedScrollView =  findViewById(R.id.nestedScrollView);
        textInputLayoutEmail =  findViewById(R.id.InputLayoutEmail);
        textInputLayoutPassword =  findViewById(R.id.InputEditTextPassword);
        textInputEditTextEmail =  findViewById(R.id.InputEditTextEmail);
        textInputEditTextPassword = findViewById(R.id.InputEditTextPassword);
        appCompatButtonLogin =  findViewById(R.id.ButtonLogin);
        textViewLinkRegister =  findViewById(R.id.ViewLinkRegister);
        sqlitehelperlogin = new SQLiteHelperLogIn(activity);
        validation = new Validation(activity);
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.ViewLinkRegister:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    private void verifyFromSQLite() {
        if (!validation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!validation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!validation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }

        if (sqlitehelperlogin.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {


            Intent accountsIntent = new Intent(activity, UsersListActivity.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);


        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
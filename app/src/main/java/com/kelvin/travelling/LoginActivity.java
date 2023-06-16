package com.kelvin.travelling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends Activity {

    private TextInputLayout mainInputEmail;
    private TextInputLayout mainInputPass;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mainInputEmail = findViewById(R.id.main_input_email);
        mainInputPass = findViewById(R.id.main_input_password);
        btn_login = findViewById(R.id.btn_Login);

        TextView snackRegisterNow = findViewById(R.id.tv_forgetPassword);
        snackRegisterNow.setOnClickListener(v -> Snackbar.make(v, "Próximamente...", Snackbar.LENGTH_LONG).show());

    }

    public void btnLogin(View view) {
        Intent buttonLogin = new Intent(this, HomeActivity.class);
        startActivity(buttonLogin);
    }

    public void btnRegister(View view) {
        Intent buttonRegister = new Intent(this, RegisterActivity.class);
        startActivity(buttonRegister);
    }
}

package com.kelvin.travelling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kelvin.travelling.database.DataBaseHelper;

public class LoginActivity extends Activity {

    private TextInputLayout mainInputEmail;
    private TextInputLayout mainInputPass;
    private Button btn_login;
    String username;
    TextInputEditText email;
    TextInputEditText password;
    DataBaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mainInputEmail = findViewById(R.id.main_input_email);
        mainInputPass = findViewById(R.id.main_input_password);
        btn_login = findViewById(R.id.btn_Login);
        email = findViewById(R.id.second_input_email);
        password = findViewById(R.id.second_input_password);
        DB = new DataBaseHelper(this);

        TextView snackRegisterNow = findViewById(R.id.tv_forgetPassword);
        snackRegisterNow.setOnClickListener(v -> Snackbar.make(v, "Próximamente...", Snackbar.LENGTH_LONG).show());

        btn_login.setOnClickListener(v -> loginUser());

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("username")) {
            username = extras.getString("username");
            Log.d("LoginActivity", "Received username: " + username);
        }

    }

    public void loginUser() {
        String username = getIntent().getStringExtra("username");
        String userEmail = email.getText().toString();
        String pass = password.getText().toString();

        if (isInputEmpty(mainInputEmail)) {
            mainInputEmail.setError("Fill in the field");
            btn_login.setEnabled(false);
        } else if (isInputEmpty(mainInputPass)) {
            mainInputPass.setError("Fill in the field");
            btn_login.setEnabled(false);
        } else {
            boolean result = DB.checkUsernamePassword(userEmail, pass);

            if (result) {
                Toast.makeText(this, "Successful login.", Toast.LENGTH_SHORT).show();

                Intent goToHome = new Intent(getApplicationContext(), HomeActivity.class);
                goToHome.putExtra("username", username);
                goToHome.putExtra("email", userEmail);
                goToHome.putExtra("password", pass);
                startActivity(goToHome);
                finish();
            } else {
                Toast.makeText(this, "Incorrect credentials.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isInputEmpty(TextInputLayout textInputLayout) {
        String textInputs = textInputLayout.getEditText().getText().toString().trim();
        return TextUtils.isEmpty(textInputs);
    }

    public void btnRegister(View view) {
        Intent buttonRegister = new Intent(this, RegisterActivity.class);
        startActivity(buttonRegister);
    }
}



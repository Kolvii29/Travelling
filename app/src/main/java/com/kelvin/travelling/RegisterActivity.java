package com.kelvin.travelling;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kelvin.travelling.database.DataBaseHelper;

public class RegisterActivity extends Activity {

    private ImageView photo_user;
    private TextInputLayout mainInputName;
    private TextInputLayout mainInputEmail;
    private TextInputLayout mainInputPass;
    private TextInputLayout mainInputAges;
    private Button btn_letsStart;
    private AutoCompleteTextView completeInputAges;
    private String termsPrivacy;
    TextInputEditText username, email, password;
    DataBaseHelper DB;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        ConstraintLayout clickToCamera = findViewById(R.id.constr_clickCamera);
        TextView btn_seeConditions = findViewById(R.id.tv_verCondiciones);
        photo_user = findViewById(R.id.picProfile);
        mainInputName = findViewById(R.id.main_input_name);
        mainInputEmail = findViewById(R.id.main_input_email);
        mainInputPass = findViewById(R.id.main_input_password);
        mainInputAges = findViewById(R.id.main_input_age);
        btn_letsStart = findViewById(R.id.btn_letsStart);
        completeInputAges = findViewById(R.id.second_input_age);
        btn_letsStart = findViewById(R.id.btn_letsStart);
        username = findViewById(R.id.second_input_name);
        email = findViewById(R.id.second_input_email);
        password = findViewById(R.id.second_input_password);
        DB = new DataBaseHelper(this);

        mainInputName.getEditText().addTextChangedListener(textWatcherInputs);
        mainInputEmail.getEditText().addTextChangedListener(textWatcherInputs);
        mainInputPass.getEditText().addTextChangedListener(textWatcherInputs);
        mainInputAges.getEditText().addTextChangedListener(textWatcherInputs);

        btn_InputAges();

        termsPrivacy = "https://developers.google.com/ml-kit/terms";
        btn_seeConditions.setOnClickListener(v -> {
            Uri termsAndPrivacy = Uri.parse(termsPrivacy);
            Intent clickTvSeeTerms = new Intent(Intent.ACTION_VIEW, termsAndPrivacy);
            startActivity(clickTvSeeTerms);
        });
        clickToCamera.setOnClickListener(v -> {
            Intent cameraPic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraPic, 1);
        });
        btn_letsStart.setOnClickListener(v -> registerUser());

    }

    public void registerUser() {
        String userUsername = username.getText().toString();
        String userEmail = email.getText().toString();
        String pass = password.getText().toString();

        if (!isInputEmpty(mainInputName)) {
            mainInputName.setError("Fill in the field.");
            btn_letsStart.setEnabled(false);
        } else if (!isInputEmpty(mainInputEmail)) {
            mainInputEmail.setError("Fill in the field.");
            btn_letsStart.setEnabled(false);
        } else if (!isInputEmpty(mainInputPass)) {
            mainInputPass.setError("Fill in the field.");
            btn_letsStart.setEnabled(false);
        } else {
            mainInputName.setError(null);
            mainInputEmail.setError(null);
            mainInputPass.setError(null);

            boolean userNameExists = DB.checkUsername(userUsername);

            if (userNameExists) {
                btn_letsStart.setEnabled(false);
                Toast.makeText(this, "The user name is already in use.", Toast.LENGTH_SHORT).show();
            } else {
                boolean isInserted = DB.insertData(userUsername, userEmail, pass);

                if (isInserted) {
                    btn_letsStart.setEnabled(true);
                    Toast.makeText(this, "Successful Registration.", Toast.LENGTH_SHORT).show();

                    Intent goToLogin = new Intent(getApplicationContext(), LoginActivity.class);
                    goToLogin.putExtra("username", userUsername);
                    startActivity(goToLogin);
                    finish();

                } else {
                    Toast.makeText(this, "Error registering the user", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            photo_user.setImageBitmap(photo);
        }

    }

    private final TextWatcher textWatcherInputs = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            boolean isInputEmpty = isInputEmpty(mainInputName)
                    && isInputEmpty(mainInputEmail)
                    && isInputEmpty(mainInputPass)
                    && isInputEmpty(mainInputAges);
            boolean isInputNameValid = isInputEmpty(mainInputName);
            boolean containInvalidChars = InvalidCharacters(mainInputName);

            if (!isInputNameValid) {
                mainInputName.setError("Fill in the field");
                btn_letsStart.setEnabled(false);
            } else if (containInvalidChars) {
                mainInputName.setError("Oops, I don't think that's right, check it.");
                btn_letsStart.setEnabled(false);
            } else {
                mainInputName.setError(null);
                btn_letsStart.setEnabled(isInputEmpty);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private boolean isInputEmpty(TextInputLayout textInputLayout) {
        String textInputs = textInputLayout.getEditText().getText().toString().trim();
        return !TextUtils.isEmpty(textInputs);
    }

    private boolean InvalidCharacters(TextInputLayout textInputLayout) {
        String textInputs = textInputLayout.getEditText().getText().toString().trim();
        return textInputs.contains("@") || textInputs.contains("!");
    }

    private void btn_InputAges() {
        String[] optionAges = {"0-5", "6-11", "12-17", "18-99"};

        completeInputAges.setAdapter(new ArrayAdapter<>(this, R.layout.dropdown_menu, optionAges));
        completeInputAges.setOnItemClickListener((parent, view, position, id) -> {
            String selectedAge = optionAges[position];

            if (selectedAge.equals("0-5") || selectedAge.equals("6-11") || selectedAge.equals("12-17")) {
                mainInputAges.setError("Esta aplicación no es para tí.");
                btn_letsStart.setEnabled(false);
            } else if (selectedAge.equals("18-99")) {
                mainInputAges.setError(null);
            } else {
                mainInputName.setError("Fill in the field");
                btn_letsStart.setEnabled(false);
            }
        });
    }

}


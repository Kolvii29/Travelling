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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends Activity {

    private ImageView photo_user;
    private TextInputLayout mainInputName;
    private TextInputLayout mainInputEmail;
    private TextInputLayout mainInputPass;

    private TextInputLayout mainInputAges;
    private Button btn_letsStart;
    private AutoCompleteTextView completeInputAges;
    private String termsPrivacy;
    private TextView btn_seeConditions;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        ConstraintLayout clickToCamera = findViewById(R.id.constr_clickCamera);
        photo_user = findViewById(R.id.picProfile);
        mainInputName = findViewById(R.id.main_input_name);
        mainInputEmail = findViewById(R.id.main_input_email);
        mainInputPass = findViewById(R.id.main_input_password);
        mainInputAges = findViewById(R.id.main_input_age);
        btn_letsStart = findViewById(R.id.btn_letsStart);
        completeInputAges = findViewById(R.id.second_input_age);
        btn_seeConditions = findViewById(R.id.tv_verCondiciones);
        btn_letsStart = findViewById(R.id.btn_letsStart);



        mainInputName.getEditText().addTextChangedListener(textWatcherInputs);
        mainInputEmail.getEditText().addTextChangedListener(textWatcherInputs);
        mainInputPass.getEditText().addTextChangedListener(textWatcherInputs);
        mainInputAges.getEditText().addTextChangedListener(textWatcherInputs);

        btn_InputAges();

        termsPrivacy = "https://developers.google.com/ml-kit/terms";
        btn_seeConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri termsAndPrivacy = Uri.parse(termsPrivacy);
                Intent clickTvSeeTerms = new Intent(Intent.ACTION_VIEW, termsAndPrivacy);
                startActivity(clickTvSeeTerms);
            }
        });
        clickToCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraPic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraPic, 1);
            }
        });


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

            if (!isInputNameValid){
                mainInputName.setError("Rellena el campo");
                btn_letsStart.setEnabled(false);
            } else if (containInvalidChars) {
                mainInputName.setError("Ups, no creo que sea correcto, revísalo");
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
                mainInputName.setError("Rellena el campo");
                btn_letsStart.setEnabled(false);
            }
        });
    }

    public void btnLogin(View view) {
        Intent buttonLogin = new Intent(this, HomeActivity.class);
        startActivity(buttonLogin);
        finish();
    }

}


package com.example.toplearners;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.toplearners.dialogFragments.AreYouSureFragment;

public class SubmissionActivity extends AppCompatActivity {

    private static final String DIALOG = "success";
    private EditText firstNameEdTx;
    private EditText lastNameEdTx;
    private EditText emailEdTx;
    private EditText linkEdTx;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        firstNameEdTx = findViewById(R.id.firstNameEdTx);
        lastNameEdTx = findViewById(R.id.lastNameEdTx);
        emailEdTx = findViewById(R.id.emailEdTx);
        linkEdTx = findViewById(R.id.projectEdTx);
        backBtn = findViewById(R.id.backImageBtn);
        Button submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateEditTexts();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubmissionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void validateEditTexts() {
        if (firstNameEdTx.getText().toString().trim().equalsIgnoreCase("")) {
            firstNameEdTx.setError("This field can not be blank");
        } else if (lastNameEdTx.getText().toString().trim().equalsIgnoreCase("")) {
            lastNameEdTx.setError("This field can not be blank");
        } else if (emailEdTx.getText().toString().trim().equalsIgnoreCase("")) {
            emailEdTx.setError("This field can not be blank");
        } else if (linkEdTx.getText().toString().trim().equalsIgnoreCase("")) {
            linkEdTx.setError("This field can not be blank");
        } else {
            //sendProject(fname, lname, email, projectlink);*/
            Bundle bundle = new Bundle();
            bundle.putString("firstName", firstNameEdTx.getText().toString());
            bundle.putString("lastName", lastNameEdTx.getText().toString());
            bundle.putString("email", emailEdTx.getText().toString());
            bundle.putString("projectLink", linkEdTx.getText().toString());
            FragmentManager manager = getSupportFragmentManager();
            AreYouSureFragment dialog = new AreYouSureFragment();
            dialog.setArguments(bundle);
            dialog.show(manager, DIALOG);
        }
    }
}
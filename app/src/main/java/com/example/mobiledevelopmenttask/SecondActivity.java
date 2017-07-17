package com.example.mobiledevelopmenttask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private Button submitFormToEmail,registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);

        submitFormToEmail = (Button) findViewById(R.id.btn_sibmit_to_mail);
        registerButton = (Button) findViewById(R.id.btn_register_data);

        submitFormToEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SecondActivity.this,SubmitFormActivity.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SecondActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}

package com.example.mobiledevelopmenttask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText_name,editText_ph,editText_pass;

    private MyDataBase mydatabase;

    private Button Started_button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        mydatabase = new MyDataBase(this, "mydb");


        editText_name=(EditText)findViewById(R.id.editText_name);
        editText_name.setText(null);

        editText_ph=(EditText)findViewById(R.id.editText_ph);
        editText_ph.setText(null);

        editText_pass=(EditText)findViewById(R.id.editText_pass);
        editText_pass.setText(null);


        Started_button= (Button) findViewById(R.id.Started_button);



        Started_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String empname = editText_name.getText().toString();
                String empcellno = editText_ph.getText().toString();
                String emppass= editText_pass.getText().toString();
                if ( empname.length() <= 0 || empcellno.length() <= 0 || emppass.length() <=0)
                {
                    Toast.makeText(getApplicationContext(), "do not insert empty field ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mydatabase.insertRecord(empname, empcellno,emppass);

                    Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                    startActivity(intent);
                }



            }
        });


    }
}

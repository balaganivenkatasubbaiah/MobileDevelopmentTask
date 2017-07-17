package com.example.mobiledevelopmenttask;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Calendar;

public class SubmitFormActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private  String[] currentLoacationdata = {"Hyderabad", "Mumbai", "Pune", "Banglore","Chennai","Noida","Navi Mimbai","KolKata"};
    private String[] academicQualData = {"B.Tech", "M.Tech", "MCA", "M.B.A","Bcom","Degree"};
    private String[] experienceData = {"6 months", "1 Year", "2 Years", "3 Years", "4 Year", "5 Years", "6 Years", "7 Year", "8 Years", "9 Years"};
    private MaterialBetterSpinner currentLocationSpinner,experienceSpinner,academeicQualSpinner;
    private String currentLocation,academicQual,experience,firstName,lastName,email;

    private EditText inputFirstName,inputLastName,inputEmail,inputPhone,inputFunction,inputDateOfBirth;
    private Button submit;
    private CheckBox agreeCheckBox;
    private DatePickerDialog datePickerDialog;
    private int Year, Month, Day ;
    private Calendar calendar ;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_form);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setTitle(getResources().getString(R.string.register1));
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SubmitFormActivity.this.finish();
            }
        });
        calendar = Calendar.getInstance();

        Year = calendar.get(Calendar.YEAR) ;
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, currentLoacationdata);
        currentLocationSpinner = (MaterialBetterSpinner)
                findViewById(R.id.current_location_spinner);
        currentLocationSpinner.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, academicQualData);
        academeicQualSpinner = (MaterialBetterSpinner)
                findViewById(R.id.academic_qualificatio_spinner);
        academeicQualSpinner.setAdapter(arrayAdapter1);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, experienceData);
        experienceSpinner = (MaterialBetterSpinner)
                findViewById(R.id.experience_spinner);
        experienceSpinner.setAdapter(arrayAdapter2);

        currentLocationSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                currentLocation = currentLoacationdata[position];
            }
        });
        experienceSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                experience = experienceData[position];
            }
        });

        academeicQualSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                academicQual = academicQualData[position];
            }
        });

        inputFirstName = (EditText) findViewById(R.id.input_first_name);
        inputLastName = (EditText) findViewById(R.id.input_last_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPhone = (EditText) findViewById(R.id.input_phone);
        inputFunction = (EditText) findViewById(R.id.input_function);

        inputDateOfBirth = (EditText) findViewById(R.id.input_date_of_birth);
        agreeCheckBox = (CheckBox) findViewById(R.id.accept_terms_conds_check);

        datePickerDialog = DatePickerDialog.newInstance(SubmitFormActivity.this, Year, Month, Day);

        datePickerDialog.setThemeDark(false);

        datePickerDialog.showYearPickerFirst(false);

        datePickerDialog.setAccentColor(Color.parseColor("#009688"));

        datePickerDialog.setTitle("Select Date From DatePickerDialog");


        inputDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
            }
        });
        submit = (Button) findViewById(R.id.btn_signup);


        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(agreeCheckBox.isChecked())
                {
                    StringBuilder builder = new StringBuilder();
                    builder.append("First Name :").append(inputFirstName.getText().toString().trim()).append("\n");
                    builder.append("Last Name : ").append(inputLastName.getText().toString().trim()).append("\n");
                    builder.append("Date of Birth : ").append(inputDateOfBirth.getText().toString().trim()).append("\n");
                    builder.append("Email : ").append(inputEmail.getText().toString().trim()).append("\n");
                    builder.append("Phone : ").append(inputPhone.getText().toString().trim()).append("\n");
                    builder.append("Current Location : ").append(currentLocation).append("\n");
                    builder.append("Total Experience : ").append(experience).append("\n");
                    builder.append("Academic Qualification : ").append(academicQual).append("\n");
                    builder.append("Function : ").append(inputFunction.getText().toString().trim()).append("\n");


                    String formData = builder.toString();

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/html");
                    intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"venkatasubbaiah.b@indiaapps.com"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Form data");
                    intent.putExtra(Intent.EXTRA_TEXT   , formData);
                    try {
                        startActivity(Intent.createChooser(intent, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(SubmitFormActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(SubmitFormActivity.this, "Please Accept Terms & Conditions.", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth)
    {
        String date = "" + dayOfMonth + "-" + (monthOfYear+1)+ "-" + year;
        inputDateOfBirth.setText(date);
    }
}

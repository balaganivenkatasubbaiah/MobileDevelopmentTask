package com.example.mobiledevelopmenttask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class MainActivity extends AppCompatActivity {

    String[] SPINNERLIST = {"Android Material Design", "Material Design Spinner", "Spinner Using Material Library", "Material Spinner Example"};
    String[] SPINNERLIST1 = {"Android Material Design", "Material Design Spinner", "Spinner Using Material Library", "Material Spinner Example"};
    String[] SPINNERLIST2 = {"Android Material Design", "Material Design Spinner", "Spinner Using Material Library", "Material Spinner Example"};
    private MaterialBetterSpinner currentLocationSpinner,experienceSpinner,academeicQualSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up1);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        currentLocationSpinner = (MaterialBetterSpinner)
                findViewById(R.id.current_location_spinner);
        currentLocationSpinner.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST1);
        academeicQualSpinner = (MaterialBetterSpinner)
                findViewById(R.id.academic_qualificatio_spinner);
        academeicQualSpinner.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST2);
        experienceSpinner = (MaterialBetterSpinner)
                findViewById(R.id.experience_spinner);
        experienceSpinner.setAdapter(arrayAdapter);
    }
}

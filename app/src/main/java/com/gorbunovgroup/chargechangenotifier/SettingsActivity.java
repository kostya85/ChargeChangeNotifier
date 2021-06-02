package com.gorbunovgroup.chargechangenotifier;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ImageButton;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import android.widget.TextView;
import android.widget.NumberPicker;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    Button saveAndBack;
    TextView lowLevel, highLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        lowLevel = findViewById(R.id.lowLevelValue);
        highLevel = findViewById(R.id.highLevelValue);
        saveAndBack = findViewById(R.id.back);
        saveAndBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IsCorrect();
            }
        });




    }

    public boolean IsCorrect(){
        try {

            Double low = new Double(lowLevel.getText().toString());
            if(low<0)throw new Exception();
            else {
                Toast.makeText(this, low.toString(),
                        Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this, "Error",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }


}
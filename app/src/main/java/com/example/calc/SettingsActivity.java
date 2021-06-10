package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class SettingsActivity extends AppCompatActivity {

    protected Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings = new Settings();

        initThemeChooser();
        Button buttonChooseTheme = findViewById(R.id.button_choose_theme);
        buttonChooseTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentResult = new Intent();
                intentResult.putExtra(Constants.YOUR_SETTINGS, settings);
                setResult(RESULT_OK, intentResult);
                finish();
            }
        });
    }

    private void initThemeChooser() {
        initRadioButton(findViewById(R.id.radioButtonMaterialDark),
                Constants.THEME_DARK_ID);
        initRadioButton(findViewById(R.id.radioButtonMaterialLight),
                Constants.THEME_LIGHT_ID);
    }

    private void initRadioButton(View button, int theme){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setThemeId(theme);
            }
        });
    }

}
package com.example.calc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SETTING_ACTIVITY = 99;
    private static final String APP_THEME = "APP_THEME";

    private TextView textView;
    private Calculator calculator;
    private Settings settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getSharedPreferences(Constants.YOUR_SETTINGS, MODE_PRIVATE);
        settings = new Settings(sharedPref.getInt(APP_THEME, Constants.THEME_LIGHT_ID));
        setThemeBySettings(settings);

        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.resultTextView);
        calculator = new Calculator();
        initButtons();
    }

    private void setThemeBySettings (Settings settings) {

            switch(settings.getThemeId()){
                case Constants.THEME_LIGHT_ID:
                     setTheme(R.style.AppThemeLight);
                     break;
                case Constants.THEME_DARK_ID:
                    setTheme(R.style.AppThemeDark);
                    break;
                default:
                    setTheme(R.style.AppThemeLight);
        }
    }

    public void initButtons() {
        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewAfterBtn("1");
            }
        });
        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewAfterBtn("2");
            }
        });
        Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewAfterBtn("3");
            }
        });
        Button button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewAfterBtn("4");
            }
        });
        Button button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewAfterBtn("5");
            }
        });
        Button button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewAfterBtn("6");
            }
        });
        Button button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewAfterBtn("7");
            }
        });
        Button button8 = findViewById(R.id.button_8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewAfterBtn("8");
            }
        });
        Button button9 = findViewById(R.id.button_9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewAfterBtn("9");
            }
        });
        Button button0 = findViewById(R.id.button_0);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewAfterBtn("0");
            }
        });
        Button buttonPoint = findViewById(R.id.button_point);
        buttonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewAfterBtn(".");
            }
        });
        Button buttonC = findViewById(R.id.button_C);
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.reset();
                textView.setText(String.format(Locale.getDefault(), "%s", calculator.getValueForTextView()));
            }
        });
        Button buttonSum = findViewById(R.id.button_sum);
        buttonSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.setOperation(Calculator.Operations.SUM);
            }
        });
        Button buttonMult = findViewById(R.id.button_mult);
        buttonMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.setOperation(Calculator.Operations.MULT);
            }
        });
        Button buttonDiff = findViewById(R.id.button_differ);
        buttonDiff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.setOperation(Calculator.Operations.DIFF);
            }
        });
        Button buttonDiv = findViewById(R.id.button_div);
        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.setOperation(Calculator.Operations.DIV);
            }
        });
        Button buttonRes = findViewById(R.id.button_res);
        buttonRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(String.format(Locale.getDefault(), "%s", calculator.getResult()));
            }
        });
        ImageButton buttonSettings = findViewById(R.id.button_settings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(settingsIntent, REQUEST_CODE_SETTING_ACTIVITY);

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_SETTING_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK){

            settings = data.getParcelableExtra(Constants.YOUR_SETTINGS);
            SharedPreferences sharedPref = getSharedPreferences(Constants.YOUR_SETTINGS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(APP_THEME, settings.getThemeId());
            editor.apply();

            recreate();
        }
    }

    private void setTextViewAfterBtn (String numberButton) {
        calculator.setValueToNumberTemp(numberButton);
        textView.setText(String.format(Locale.getDefault(), "%s", calculator.getValueForTextView()));
    }
}
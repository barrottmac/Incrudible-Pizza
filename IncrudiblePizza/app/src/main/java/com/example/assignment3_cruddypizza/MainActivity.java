package com.example.assignment3_cruddypizza;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    RadioButton radioButtonEnglish,
            radioButtonFrench;

    Button enterButton,
        viewOrderButton;

    public SharedPreferences prefs;
    private static final String TEXT_LANGUGE_KEY="textLanguage";

//    Resources res = getResources();
//    String[] strings;
//    String[] frencshStrings = res.getStringArray(R.array.French);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();
        String[] strings;

        radioButtonEnglish = findViewById(R.id.englishRadioButton);
        radioButtonFrench = findViewById(R.id.frenchRadioButton);

        enterButton = findViewById(R.id.Enter_Button);
        viewOrderButton = findViewById(R.id.ViewOrders_Button);

        enterButton.setOnClickListener(view -> {
            Intent intent= new Intent(MainActivity.this, PlaceOrder.class);
            startActivity(intent);});

        viewOrderButton.setOnClickListener(view -> {
            Intent intent= new Intent(MainActivity.this, ViewOrder.class);
            startActivity(intent);});



        prefs=getSharedPreferences("prefs",MODE_PRIVATE);
        String language= prefs.getString(TEXT_LANGUGE_KEY, "English");
        if (language.equals("English")){
            strings = res.getStringArray(R.array.English);
            enterButton.setText(strings[0]);
            viewOrderButton.setText(strings[1]);
        }
        else if (language.equals("French")){
            strings = res.getStringArray(R.array.French);

            enterButton.setText(strings[0]);
            viewOrderButton.setText(strings[1]);
        }



    }
    public void onRadioButtonClicked(@NonNull View view){
        Resources res = getResources();
        String[] strings;
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {

            case R.id.englishRadioButton:
                if (checked)
                    prefs=getSharedPreferences("prefs",MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(TEXT_LANGUGE_KEY,"English");
                    editor.apply();
                    strings = res.getStringArray(R.array.English);

                    enterButton.setText(strings[0]);
                    viewOrderButton.setText(strings[1]);
                    radioButtonEnglish.setText(strings[2]);
                    radioButtonFrench.setText(strings[3]);
                    break;
            case R.id.frenchRadioButton:
                if (checked)
                    prefs=getSharedPreferences("prefs",MODE_PRIVATE);
                    editor = prefs.edit();
                    editor.putString(TEXT_LANGUGE_KEY,"French");
                    editor.apply();
                    strings = res.getStringArray(R.array.French);

                    enterButton.setText(strings[0]);
                    viewOrderButton.setText(strings[1]);
                    radioButtonEnglish.setText(strings[2]);
                    radioButtonFrench.setText(strings[3]);
                    break;
        }
    }


}
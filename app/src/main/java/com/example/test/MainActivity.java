package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;
//has the textview stuff
//testing new changes
//testing sending it back up

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this is the first code ran on startup
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //calls the activity main XML file
        //TODO: test to do list
    }

    public void encode(View view) {
        //function that reflects the "Android:onClick" code in the button xml info
        TextView textOutput = findViewById(R.id.textOutput);

        //create a textview named txtHello, and grab the ID of from the text in the xml file

        EditText editInput = findViewById(R.id.editTextInput);
        String name = editInput.getText().toString();
        textOutput.setText("Code: " + name);
    }

    public void decode(View view) {
        TextView textOutput = findViewById(R.id.textOutput);

        EditText editInput = findViewById(R.id.editTextInput);
        String input = editInput.getText().toString();
        String output = "";
        for(int x = input.length() - 1; x >= 0 ; x--){
            output = output + input.charAt(x);
        }
        textOutput.setText("Code: " + output);
    }
}

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
        TextView textOutput = findViewById(R.id.textOutput);
        //creates a "TextView" object named textOutput that calls the ID of the output textbox at the bottom of the XML file
        EditText editInput = findViewById(R.id.editTextInput);
        //creates an "EditText" object name editInput that uses the ID of the input textbox in the XML File.
        String name = editInput.getText().toString();
        //this grabs the text written by the user in the editText object and converts it to a string.
        textOutput.setText("Code: " + name);
        //this sets the textOutput to display the string.
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

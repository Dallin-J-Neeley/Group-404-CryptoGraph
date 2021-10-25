package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.RadioGroup;
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
    public void translate(View view) {
        RadioButton input1 = findViewById(R.id.input1);
        RadioButton output1 = findViewById(R.id.output1);

        RadioButton input2 = findViewById(R.id.input2);
        RadioButton output2 = findViewById(R.id.output2);

        RadioButton input3 = findViewById(R.id.input3);
        RadioButton output3 = findViewById(R.id.output3);

        EditText editInput = findViewById(R.id.editTextInput);
        TextView textOutput = findViewById(R.id.textOutput);
        String input = editInput.getText().toString();
        String output = "";

        if(input1.isChecked()){
            //input 1 is english input
            output = "input box 1 is checked: ";
        }

        if(input2.isChecked()){
            //input 1 is english input
            output = "input box 2 is checked: ";
        }

        if(input3.isChecked()){
            //input 1 is english input
            output = "input box 3 is checked: ";
        }

        if(output1.isChecked()){
            //output1 is english output
            output = output + "output box 1";
        }
        if(output2.isChecked()){
            //output1 is english output
            output = output + "output box 2";
        }
        if(output3.isChecked()){
            //output1 is english output
            output = output + "output box 3";
        }

        textOutput.setText("Code: " + output);
    }


    public String invert(String input){
        String output = "";
        for(int x = input.length() - 1; x >= 0 ; x--){
            output = output + input.charAt(x);
        }
        return output;
    }

}


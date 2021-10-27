
package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;
import java.util.HashMap; // import the HashMap class
import java.util.Objects;

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
        MainActivity myObject = new MainActivity();
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
            output = myObject.encodeMorse(input);

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
            output = myObject.decodeMorse(input);
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

    public String morseHashmap (String input, String name) {
        HashMap<String, String> morseCode = new HashMap<String, String>();
        morseCode.put("._", "A");
        morseCode.put("_...", "B");
        morseCode.put("_._.", "C");
        morseCode.put("_..", "D");
        morseCode.put(".", "E");
        morseCode.put(".._.", "F");
        morseCode.put("__.", "G");
        morseCode.put("....", "H");
        morseCode.put("..", "I");
        morseCode.put(".___", "J");
        morseCode.put("_._", "K");
        morseCode.put("._..", "L");
        morseCode.put("__", "M");
        morseCode.put("_.", "N");
        morseCode.put("___", "O");
        morseCode.put(".__.", "P");
        morseCode.put("__._", "Q");
        morseCode.put("._.", "R");
        morseCode.put("...", "S");
        morseCode.put("_", "T");
        morseCode.put(".._", "U");
        morseCode.put("..._", "V");
        morseCode.put(".__", "W");
        morseCode.put("_.._", "X");
        morseCode.put("_.__", "Y");
        morseCode.put("__..", "Z");
        morseCode.put(".____", "1");
        morseCode.put("..___", "2");
        morseCode.put("...__", "3");
        morseCode.put("...._", "4");
        morseCode.put(".....", "5");
        morseCode.put("_....", "6");
        morseCode.put("__....", "7");
        morseCode.put("___..", "8");
        morseCode.put("____.", "9");
        morseCode.put("_____", "0");
        morseCode.put(" ", " ");
        String output = "";
        if (input == null){
            for (String i : morseCode.keySet()) {
                if (Objects.equals(name, morseCode.get(i))) {
                    output = i;
                    System.out.println(i);
                }
            }
        }
        else if (name == null) {
            output = morseCode.get(input);
        }
        else {
            output = "Null";
        }
        return output;
    }

    public String encodeMorse(String name) {
        MainActivity myObject = new MainActivity();
        String output = "";
        String getMorse = "";
        String getLetter = "";
        for(int x = 0; x < name.length() ; x++) {
            if (name.charAt(x) == ' ' || x == name.length() - 1) {
                if (x == name.length() - 1 && name.charAt(x) != ' '){
                    getMorse += String.valueOf(name.charAt(x));
                }
                getLetter = myObject.morseHashmap(getMorse, null);
                output += getLetter;
                getMorse = ""; //please notify me when the media is done playing so I can play the next one //function
            }
            else {
                getMorse += String.valueOf(name.charAt(x));
            }
        }
        return output;
        //this sets the textOutput to display the string.
    }

    public String decodeMorse(String input) {
        String output = "";
        for(int x = 0; x < input.length() ; x++){
            MainActivity myObject = new MainActivity();
            output += myObject.morseHashmap(null, String.valueOf(input.charAt(x)));
            output += " ";
        }
        return output;
    }

}

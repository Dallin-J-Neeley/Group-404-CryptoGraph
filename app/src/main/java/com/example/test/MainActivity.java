package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.HashMap;

import java.util.Locale;
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

        //TODO: Set up QR Codes to send to other phones?
        //have a password to enter to unhash a phrase.
        //side tail, rail fence, cyphers. look into more cyphers.
    }
    public void translate(View view) {
        MainActivity myObj = new MainActivity();
        RadioButton input1 = findViewById(R.id.input1);
        RadioButton output1 = findViewById(R.id.output1);

        RadioButton input2 = findViewById(R.id.input2);
        RadioButton output2 = findViewById(R.id.output2);

        RadioButton input3 = findViewById(R.id.input3);
        RadioButton output3 = findViewById(R.id.output3);

        RadioButton input4 = findViewById(R.id.input4);
        RadioButton output4 = findViewById(R.id.output4);

        RadioButton input5 = findViewById(R.id.input5);
        RadioButton output5 = findViewById(R.id.output5);

        EditText editInput = findViewById(R.id.editTextInput);
        TextView textOutput = findViewById(R.id.textOutput);
        String input = editInput.getText().toString();
        String output = "";

        if(input1.isChecked()){
            //input 1 is english input
            output = input;
        }
        if(input2.isChecked()){
            //input 2 is Morse input
            output = encodeMorse(input);
        }
        if(input3.isChecked()){
            //input 1 is ToBlue
            output = myObj.binaryToEnglish(input);
        }
        if(input4.isChecked()){
            //input 1 is english input
            output = "toBlue";
        }
        if(input5.isChecked()){
            //input 1 is english input
            output = "ToBlue input is checked: ";
        }
//type in a key toBlue?

        if(!output1.isChecked()){
            //output1 is english output
            //do nothing
            input = output;
        }
        if(output2.isChecked()){
            //out 2 is the Morse coder
            output = decodeMorse(input);
        }
        if(output3.isChecked()){
            //output1 is Binary
            output = "Binary output";
            output = myObj.englishToBinary(input);
        }
        if(output4.isChecked()){
            //output1 is ToBlue output
            output = "toBlue";
        }
        if(output5.isChecked()){
            //output1 is ToBlue output
            output = toBlue(input);
        }

        textOutput.setText("Code: " + output);
    }

    public String invertText(String input){
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
        morseCode.put("/n", ".");
        String output = "";
        if (input == null){
            for (String i : morseCode.keySet()) {
                if (Objects.equals(name, morseCode.get(i))) {
                    output = i;
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
        for(int x = 0, count = 0; x < name.length() ; x++) {
            if (name.charAt(x) == ' ' || x == name.length() - 1) {
                if (x == name.length() - 1 && name.charAt(x) != ' '){
                    getMorse += String.valueOf(name.charAt(x));
                }
                /*if (name.charAt(x - 1) == '.') {
                    count = 0;
                }*/
                getLetter = myObject.morseHashmap(getMorse, null);
                if (count != 0){
                    getLetter = getLetter.toLowerCase();
                }
                output += getLetter;
                getMorse = ""; //please notify me when the media is done playing so I can play the next one //function
                count += 1;
            }
            else {
                getMorse += String.valueOf(name.charAt(x));
            }
        }
        return output;
        //this sets the textOutput to display the string.
    }

    public String decodeMorse(String input) {
        input = input.toUpperCase();
        String output = "";
        for(int x = 0; x < input.length() ; x++){
            MainActivity myObject = new MainActivity();
            output += myObject.morseHashmap(null, String.valueOf(input.charAt(x)));
            output += " ";
        }
        return output;
    }

    public String toBlue(String input){
        HashMap<String, String> alphabet = new HashMap<String, String>();
        alphabet.put("A", "I");
        alphabet.put("B", "N");
        alphabet.put("C", "T");
        alphabet.put("D", "H");
        alphabet.put("E", "E");
        alphabet.put("F", "B");
        alphabet.put("G", "L");
        alphabet.put("H", "U");
        alphabet.put("I", "A");
        alphabet.put("J", "C");
        alphabet.put("K", "D");
        alphabet.put("L", "F");
        alphabet.put("M", "G");
        alphabet.put("N", "J");
        alphabet.put("O", "K");
        alphabet.put("P", "M");
        alphabet.put("Q", "O");
        alphabet.put("R", "P");
        alphabet.put("S", "Q");
        alphabet.put("T", "R");
        alphabet.put("U", "S");
        alphabet.put("V", "V");
        alphabet.put("W", "W");
        alphabet.put("X", "X");
        alphabet.put("Y", "Y");
        alphabet.put("Z", "Z");
        alphabet.put(" ", "(");
        String output = " ";
        input = input.toUpperCase();
        for(int x = 0; x < input.length(); x++){
            output += alphabet.get(String.valueOf(input.charAt(x)));
        }
        return output;
    }

    public static String englishToBinary(String input)
    {
        input = input.toUpperCase();

        HashMap<String, String> alphabet = new HashMap<String, String>();
        alphabet.put("A", "01000001");
        alphabet.put("B", "01000010");
        alphabet.put("C", "01000011");
        alphabet.put("D", "01000100");
        alphabet.put("E", "01000101");
        alphabet.put("F", "01000110");
        alphabet.put("G", "01000111");
        alphabet.put("H", "01001000");
        alphabet.put("I", "01001001");
        alphabet.put("J", "01001010");
        alphabet.put("K", "01001011");
        alphabet.put("L", "01001100");
        alphabet.put("M", "01001101");
        alphabet.put("N", "01001110");
        alphabet.put("O", "01001111");
        alphabet.put("P", "01010000");
        alphabet.put("Q", "01010001");
        alphabet.put("R", "01010010");
        alphabet.put("S", "01010011");
        alphabet.put("T", "01010100");
        alphabet.put("U", "01010101");
        alphabet.put("V", "01010110");
        alphabet.put("W", "01010111");
        alphabet.put("X", "01011000");
        alphabet.put("Y", "01011001");
        alphabet.put("Z", "01011010");
        alphabet.put(" ", " ");
        String output = ""; // empty string to be outputted

        // For Loop changes character to character
        for(int i = 0; i < input.length(); i++)
        {
            output += (alphabet.get(String.valueOf(input.charAt(i)))+ " ");
        }
        return output;
    }

    public static String binaryToEnglish(String input)
    {

        HashMap<String, String> alphabet = new HashMap<String, String>();
        alphabet.put("01000001", "A");
        alphabet.put("01000010", "B");
        alphabet.put("01000011", "C");
        alphabet.put("01000100", "D");
        alphabet.put("01000101", "E");
        alphabet.put("01000110", "F");
        alphabet.put("01000111", "G");
        alphabet.put("01001000", "H");
        alphabet.put("01001001", "I");
        alphabet.put("01001010", "J");
        alphabet.put("01001011", "K");
        alphabet.put("01001100", "L");
        alphabet.put("01001101", "M");
        alphabet.put("01001110", "N");
        alphabet.put("01001111", "O");
        alphabet.put("01010000", "P");
        alphabet.put("01010001", "Q");
        alphabet.put("01010010", "R");
        alphabet.put("01010011", "S");
        alphabet.put("01010100", "T");
        alphabet.put("01010101", "U");
        alphabet.put("01010110", "V");
        alphabet.put("01010111", "W");
        alphabet.put("01011000", "X");
        alphabet.put("01011001", "Y");
        alphabet.put("01011010", "Z");
        alphabet.put(" ", " ");
        String output = ""; // empty string to be outputted

        // For Loop changes character to character
        for(int i = 0; i < input.length(); i++)
        {
            String stream = "";
            if(input.charAt(i) == '1' || input.charAt(i) == '0')
            {
                for(int x = 0; x < 8; x++)
                {
                    stream += input.charAt(i);
                    i++;
                }
                output += alphabet.get(stream);
            }
            else if (input.charAt(i+1) != ' ')
            {
                output += " ";
            }

        }
        output = output.toLowerCase();
        return output;
    }
 
}

package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.HashMap;

import java.util.Locale;
import java.util.Objects;
//has the textview stuff
//testing new changes
//testing sending it back up

public class MainActivity extends AppCompatActivity {
    Socket s = null;
    Client client = new Client();
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
        
        //create the individaul buttons and assign IDs
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
        
        //Grab the text typed in by the user
        String input = editInput.getText().toString();
        String output = "";
        
        //look for any checked boxes and call the appropriate function.
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
            output = myObj.hextoEnglish(input);
        }
        if(input5.isChecked()){
            //input 1 is english input
            output = myObj.BluetoEnglish(input);
        }


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
            output = myObj.englishToBinary(input);
        }
        if(output4.isChecked()){
            //output1 is ToBlue output
            output = myObj.englishtoHex(input);
        }
        if(output5.isChecked()){
            //output1 is ToBlue output
            output = englishtoBlue(input);
        }

        //Assign the output and display
        textOutput.setText("Code: " + output);
        //sockets obj = new sockets(this);
        //obj.execute(output);
        client.send(output);
        receiveMessage msg = new receiveMessage(this);
        msg.execute();
    }

    public String invertText(String input){
        String output = "";
        for(int x = input.length() - 1; x >= 0 ; x--){
            output = output + input.charAt(x);
        }
        return output;
    }

    public String morseHashmap (String input, String name) {
        //Hashmap for the Morsecode 
        HashMap<String, String> morseCode = new HashMap<String, String>();
        morseCode.put("._", "A"); // morse dictionary
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
        if (input == null){ // english to morse //the key is morse and the value is english
            for (String i : morseCode.keySet()) { // shuffle through dictionary
                if (Objects.equals(name, morseCode.get(i))) { // find the key based on value
                    output = i; // return output
                }
            }
        }
        else if (name == null) {// morse to english // .get(morseLetter) = value
            output = morseCode.get(input);
        }
        else {
            output = "Null";
        }
        return output;
    }

    public String encodeMorse(String name) { // Morse to english
        MainActivity myObject = new MainActivity();
        String output = "";
        String getMorse = "";
        String getLetter = "";
        for(int x = 0, count = 0; x < name.length() ; x++) { //cycle through morse
            if (name.charAt(x) == ' ' || x == name.length() - 1) { // if letter = " " or is the last character
                if (x == name.length() - 1 && name.charAt(x) != ' '){ // if is last character and does not equal a space
                    getMorse += String.valueOf(name.charAt(x)); // then add character to string
                    // this makes it so that we don't accidentally add white space
                }
                /*if (name.charAt(x - 1) == '.') {
                    count = 0;
                }*/
                getLetter = myObject.morseHashmap(getMorse, null); // translate getMorse
                if (count != 0){ // if it isn't the first letter then don't capitalize
                    getLetter = getLetter.toLowerCase();
                }
                output += getLetter; // set to output
                getMorse = ""; // empty getMorse variable for next set of dots and underscores // --> random notes // please notify me when the media is done playing so I can play the next one //function
                count += 1; // control captitalization
            }
            else {
                getMorse += String.valueOf(name.charAt(x)); // continue to add to make a whole letter in morse
            }
        }
        return output;
        //this sets the textOutput to display the string.
    }

    public String decodeMorse(String input) { //english to morse
        input = input.toUpperCase();
        String output = "";
        for(int x = 0; x < input.length() ; x++){
            MainActivity myObject = new MainActivity();
            output += myObject.morseHashmap(null, String.valueOf(input.charAt(x))); //translate each letter
            output += " ";// add spaces between each morseletter
        }
        return output;
    }

    public String toBlue(String input, String name){
        HashMap<String, String> alphabet = new HashMap<String, String>();
        alphabet.put("A", "I"); //same logic as above!!
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
        alphabet.put(" ", " ");
        String output = "";
        if (input == null){
            name = name.toUpperCase(); //set everything to uppercase
            for (String i : alphabet.keySet()) {
                if (Objects.equals(name, alphabet.get(i))) {
                    output = i;
                }
            }
        }
        else if (name == null) {
            input = input.toUpperCase(); // set everything to uppercase
            output = alphabet.get(input);
        }
        else {
            output = "Null"; // error handling
        }
        return output;
        /*for(int x = 0; x < input.length(); x++){
            output += alphabet.get(String.valueOf(input.charAt(x)));
        }
        return output;*/
    }

    public String BluetoEnglish(String name) {
        MainActivity myObject = new MainActivity();
        String output = "";
        String getLetter = "";
        for(int x = 0; x < name.length() ; x++) {
                getLetter = myObject.toBlue(String.valueOf(name.charAt(x)), null); // decode each letter
                output += getLetter;
        }
        return output;
        //this sets the textOutput to display the string.
    }

    public String englishtoBlue(String input) {
        String output = "";
        MainActivity myObject = new MainActivity();
        for(int x = 0; x < input.length() ; x++){
            if (Character.isLowerCase(input.charAt(x))) { // output toBlue code as lowercase if english was lowercased
                output += myObject.toBlue(null, String.valueOf(input.charAt(x))).toLowerCase(Locale.ROOT);
            }else {
                output += myObject.toBlue(null, String.valueOf(input.charAt(x)));
            }
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
            output += (alphabet.get(String.valueOf(input.charAt(i)))+ " "); // translate each letter
        }
        return output;
    }

    public static String binaryToEnglish(String input)
    {

        HashMap<String, String> alphabet = new HashMap<String, String>();
        alphabet.put("01000001", "A"); // binary hashmap
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
            if(input.charAt(i) == '1' || input.charAt(i) == '0') //error handling
            {
                for(int x = 0; x < 8; x++) // only eight numbers!
                {
                    stream += input.charAt(i); // add each number to stream
                    i++; // increment i
                }
                output += alphabet.get(stream); // translate stream
            }
            else if (input.charAt(i+1) != ' ') // keep proper spacing
            {
                output += " ";
            }

        }
        output = output.toLowerCase(); // output in lowercase
        return output;
    }

    public String hexHashmap (String input, String name) {
        HashMap<String, String> hex = new HashMap<String, String>();
        hex.put("61", "a"); //same logic as toBlue and Morse
        hex.put("62", "b");
        hex.put("63", "c");
        hex.put("64", "d");
        hex.put("65", "e");
        hex.put("66", "f");
        hex.put("67", "g");
        hex.put("68", "h");
        hex.put("69", "i");
        hex.put("6a", "j");
        hex.put("6b", "K");
        hex.put("6c", "l");
        hex.put("6d", "m");
        hex.put("6e", "n");
        hex.put("6f", "o");
        hex.put("70", "p");
        hex.put("71", "q");
        hex.put("72", "r");
        hex.put("73", "s");
        hex.put("74", "t");
        hex.put("75", "u");
        hex.put("76", "v");
        hex.put("77", "w");
        hex.put("78", "x");
        hex.put("79", "y");
        hex.put("7a", "z");
        hex.put("41", "A");
        hex.put("42", "B");
        hex.put("43", "C");
        hex.put("44", "D");
        hex.put("45", "E");
        hex.put("46", "F");
        hex.put("47", "G");
        hex.put("48", "H");
        hex.put("49", "I");
        hex.put("4a", "J");
        hex.put("4b", "K");
        hex.put("4c", "L");
        hex.put("4d", "M");
        hex.put("4e", "N");
        hex.put("4f", "O");
        hex.put("50", "P");
        hex.put("51", "Q");
        hex.put("52", "R");
        hex.put("53", "S");
        hex.put("54", "T");
        hex.put("55", "U");
        hex.put("56", "V");
        hex.put("57", "W");
        hex.put("58", "X");
        hex.put("59", "Y");
        hex.put("5a", "Z");
        hex.put("30", "0");
        hex.put("31", "1");
        hex.put("32", "2");
        hex.put("33", "3");
        hex.put("34", "4");
        hex.put("35", "5");
        hex.put("36", "6");
        hex.put("37", "7");
        hex.put("38", "8");
        hex.put("39", "9");

        hex.put("20", " ");
        hex.put("2e", ".");

        String output = "";
        if (input == null){
            for (String i : hex.keySet()) {
                if (Objects.equals(name, hex.get(i))) {
                    output = i;
                }
            }
        }
        else if (name == null) {
            output = hex.get(input);
        }
        else {
            output = "Null";
        }
        return output;
    }

    public String hextoEnglish(String name) {
        MainActivity myObject = new MainActivity();
        String output = "";
        String getHex = "";
        String getLetter = "";
        for(int x = 0, count = 0; x < name.length() ; x++) {
            if (name.charAt(x) == ' ' || x == name.length() - 1) {
                if (x == name.length() - 1 && name.charAt(x) != ' '){
                    getHex += String.valueOf(name.charAt(x));
                }
                /*if (name.charAt(x - 1) == '.') {
                    count = 0;
                }*/
                getLetter = myObject.hexHashmap(getHex, null);
                output += getLetter;
                getHex = ""; //please notify me when the media is done playing so I can play the next one //function
                count += 1;
            }
            else {
                getHex += String.valueOf(name.charAt(x));
            }
        }
        return output;
        //this sets the textOutput to display the string.
    }

    public String englishtoHex(String input) {
        String output = "";
        MainActivity myObject = new MainActivity();
        for(int x = 0; x < input.length() ; x++){
            output += myObject.hexHashmap(null, String.valueOf(input.charAt(x)));
            output += " ";
        }
        return output;
    }

    public void checkInternet(View view) throws IOException {
        this.s = client.sockets(); // connects socket
    }

    private static class receiveMessage extends AsyncTask<String, String, String> { // Async Task runs in the background
        private WeakReference<MainActivity> myObj; // allows access to objects in MainActivity

        receiveMessage(MainActivity obj) {
            myObj = new WeakReference<MainActivity>(obj);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                MainActivity mainObj = myObj.get();
                if (mainObj == null || mainObj.isFinishing()) {
                    //return;
                }
                String msgFromGroup;

                InputStreamReader inputStreamReader = new InputStreamReader(mainObj.client.s.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                while (mainObj.client.s.isConnected()) { //get messages while connected
                    msgFromGroup = bufferedReader.readLine();
                    publishProgress(msgFromGroup); // send to onProgressUpdate
                    System.out.println(msgFromGroup);
                }
            } catch (IOException e) {
                return "Server Failed";
            }
            return "Server Failed"; // tell user server failed returns to onPostExecute
        }

        @Override
        protected void onProgressUpdate(String... strings) {
            super.onProgressUpdate(strings);
            MainActivity obj = myObj.get();
            if (obj == null || obj.isFinishing()) {
                //return;
            }
            Toast.makeText(obj, strings[0], Toast.LENGTH_SHORT).show(); // display message from other clients
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            MainActivity obj = myObj.get();
            if (obj == null || obj.isFinishing()) {
                //return;
            }
            if (s.equals("Server connected")) {
                Toast.makeText(obj, s, Toast.LENGTH_SHORT).show(); // displays error if server fails
            }
        }
    }

}

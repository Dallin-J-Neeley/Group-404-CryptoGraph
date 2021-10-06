package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
//has the textview stuff
//testing new changes

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this is the first code ran on startup
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //calls the activity main XML file

        //TODO: test
    }

    public void onButtonClick(View view) {
        //function that reflects the "Android:onClick" code in the button xml info
        TextView textFirstName = findViewById(R.id.textViewFirstName);
        TextView textLastName = findViewById(R.id.textViewLastName);
        TextView textEmail = findViewById(R.id.textViewEmail);
        //create a textview named txtHello, and grab the ID of from the text in the xml file

        EditText editTextFirstName = findViewById(R.id.editTextFirstName);
        String name = editTextFirstName.getText().toString();
        textFirstName.setText(name);

        EditText editTextLastName = findViewById((R.id.editTextLastName));
        textLastName.setText(editTextLastName.getText().toString());

        EditText editTextEmail = findViewById((R.id.editTextEmail));
        textEmail.setText("email: " + editTextEmail.getText().toString());



        //creates an EditText object that is linked to the textbox in the xml.


    }
}

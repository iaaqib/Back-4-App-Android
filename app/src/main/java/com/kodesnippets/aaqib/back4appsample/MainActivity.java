package com.kodesnippets.aaqib.back4appsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ParseObject parseObject;
    ParseQuery query;
    Button save,update,delete;
    EditText editText, updateOrDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = (Button) findViewById(R.id.save);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        editText = (EditText) findViewById(R.id.editText);
        updateOrDelete = (EditText) findViewById(R.id.editText2);
        //Init Parse
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("DXGlf9AEJIOvvWh5QkHpxdQZb1yr41ZX2lfFVbXQ")
                .clientKey("fnqQwNlCnexGZPbwCkTCOq45hWDddLjQYVuiTMTb")
                .server("https://parseapi.back4app.com/").build()
        );


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String setText = editText.getText().toString();
            if (setText != "") {
                parseObject = new ParseObject("Back4AppAndroid");
                parseObject.put("text", setText);
                editText.setText("");
                updateOrDelete.setText("");
                parseObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null)
                            Util.showToast("Saved on server Successfully",getApplicationContext());
                        else
                            Util.showToast(e.getMessage().toString(),getApplicationContext());
                    }
                });
            }
                else{
                Util.showToast("Enter a text to send.",getApplicationContext());


            }

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String setText = editText.getText().toString();
                String find = updateOrDelete.getText().toString();
                if (setText != "" || find != "") {
                    try {
                        query = ParseQuery.getQuery("Back4AppAndroid");
                        query.whereEqualTo("text", find);
                        parseObject = query.getFirst();
                        parseObject.put("text",setText);
                        // parseObject.deleteInBackground();
                        editText.setText("");
                        updateOrDelete.setText("");
                        parseObject.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null)
                                Util.showToast("Saved on server Successfully",getApplicationContext());
                                else
                                    Util.showToast(e.getMessage().toString(),getApplicationContext());
                            }
                        });
                    } catch (ParseException ex) {
                        Util.showToast(ex.getMessage().toString(),getApplicationContext());
                    }
                }
                else{

                    Util.showToast("Fill both fields to proceed.",getApplicationContext());
                }

            }
        });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String setText = editText.getText().toString();
                String find = updateOrDelete.getText().toString();
                if (setText != "") {
                    try {
                        query = ParseQuery.getQuery("Back4AppAndroid");
                        query.whereEqualTo("text", setText);
                        parseObject = query.getFirst();
                        editText.setText("");
                        updateOrDelete.setText("");
                        parseObject.deleteInBackground(new DeleteCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null)
                                    Util.showToast("Saved on server Successfully",getApplicationContext());
                                else
                                    Util.showToast(e.getMessage().toString(),getApplicationContext());
                            }

                        });

                    } catch (ParseException ex) {
                        Util.showToast(ex.getMessage().toString(),getApplicationContext());
                    }
                }
                else{

                    Util.showToast("Fill both fields to proceed.",getApplicationContext());
                }

            }
        });

        parseObject = new ParseObject("Back4AppAndroid");
        parseObject.fetchIfNeededInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                object.deleteInBackground();
            }
        });

      //  parseObject.put("text","Welcome To Back4App");
    //    parseObject.saveInBackground();
        ParseQuery query =  ParseQuery.getQuery("Back4AppAndroid");
        try {
            query.whereEqualTo("text","Welcome");

            parseObject = query.getFirst();
            parseObject.put("text","Welcome Home");
           // parseObject.deleteInBackground();
            parseObject.saveInBackground();
        }
        catch(ParseException ex){

        }


    }
}

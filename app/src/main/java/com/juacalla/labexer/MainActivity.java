package com.juacalla.labexer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText et_user, et_pass;
    Button btnshare, btninternal, btnnext;
    SharedPreferences preferences;
    FileOutputStream output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_user=(EditText) findViewById(R.id.etUser);
        et_pass=(EditText) findViewById(R.id.editText);
        btnshare=(Button) findViewById(R.id.btn_share);
        btninternal=(Button) findViewById(R.id.btn_internal);
        btnnext=(Button) findViewById(R.id.btn_Next);
        preferences= getPreferences(Context.MODE_PRIVATE);
    }
    public void Next(View view){
        Intent intent=new Intent(this, SecondActivity.class);
        startActivity(intent);

    }
    public void SharedPref(View view){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", et_user.getText().toString());
        editor.putString("password", et_pass.getText().toString());
        editor.commit();
        Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();

    }
    public void InternalStore (View view) {
        String username = et_user.getText().toString();
        String space = ("\r\n");
        String password = et_pass.getText().toString();
        try {
            output = openFileOutput("output.txt", Context.MODE_PRIVATE);
            output.write(username.getBytes());
            output.write(space.getBytes());
            output.write(password.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();
    }

}

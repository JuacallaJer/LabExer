package com.juacalla.labexer;

        import android.widget.Button;
        import android.widget.TextView;
        import java.io.BufferedReader;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.preference.PreferenceManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;


public class SecondActivity extends AppCompatActivity {

    Button btnShare,btnInternal,btnClear,btnBack;
    TextView tvdisplay;
    FileInputStream input;
    BufferedReader reader;
    String user, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnShare = (Button) findViewById(R.id.btn_load1);
        btnInternal = (Button) findViewById(R.id.btn_load2);
        btnClear= (Button) findViewById(R.id.btn_clear);
        btnBack = (Button) findViewById(R.id.btn_back);
        tvdisplay = (TextView) findViewById(R.id.textView);

    }

    public void LoadSP (View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String user = preferences.getString("username","");
        String pwd = preferences.getString("password","");
        tvdisplay.setText("The password of " + user + " is " + pwd);
    }

    public void LoadIS (View view) throws IOException {
        String show = "";
        try{
            input = openFileInput("output.txt");
            reader = new BufferedReader(new InputStreamReader(input));
            if ((show = reader.readLine()) != null)
                user = show;
            if ((show = reader.readLine()) != null)
                pwd = show;
            input.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tvdisplay.setText("The password of " + user + " is " + pwd);
    }

    public void Back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Clear(View view) {
        tvdisplay.setText("");
    }
}
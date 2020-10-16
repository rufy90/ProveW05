package com.example.provew05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DisplayMessageActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    EditText scripture;
    EditText scripture2;
    EditText scripture3;
    Button btnSave;
    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        btnSave = (Button) findViewById(R.id.button2);
        btnShow = (Button) findViewById(R.id.button3);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        Log.i(TAG, "Received intent with: " + message);
        textView.setText(message);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("SavedScripture", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor =   preferences.edit();
                editor.putString("SavedScripture", message);
                editor.commit();
                Toast.makeText(getApplicationContext(), "Scripture Saved!", Toast.LENGTH_SHORT).show();
            }
        })
        ;
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("SavedScripture", Context.MODE_PRIVATE);
                String data = preferences.getString("SavedScripture", "Invalid Id");
                TextView showpiece = (TextView) findViewById(R.id.textView2);
                String preference = "Data saved: " + data;
                showpiece.setText(preference);
            }
        });
    }
}
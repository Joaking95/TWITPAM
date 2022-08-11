package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveOrDeleteActivity extends AppCompatActivity {
    TextView tvDrafts;
    Button btnS;
    Button btnK;
    Context context;
    EditText etCompose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_or_delete);
        btnS = findViewById(R.id.btButtonS);
        btnK = findViewById(R.id.btButtonK);
        tvDrafts = findViewById(R.id.tvDrafts);

            btnS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteFile("myfile");
                    Intent intent = new Intent(SaveOrDeleteActivity.this, TimelineActivity.class);
                    startActivity(intent);
                }
            });

            btnK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent(SaveOrDeleteActivity.this, TimelineActivity.class);
                    startActivity(intent);

                }
            });

    }
}
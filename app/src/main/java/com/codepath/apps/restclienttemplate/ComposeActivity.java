package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import okhttp3.Headers;

public class ComposeActivity extends AppCompatActivity {
 
    public static final String TAG = "ComposeActivity";

            
    public static final int MAX_LENGHT = 280;
    EditText etCompose;
    Button btButton;
    TwitterClient client;
    TextView tvCompteur;
    Button btnB;
    FloatingActionButton ReturnButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        etCompose = findViewById(R.id.etCompose);
        btButton = findViewById(R.id.btButton);
        btnB = findViewById(R.id.btButtonB);
        ReturnButton = findViewById(R.id.ReturnButton);
        tvCompteur = findViewById(R.id.tvCompteur);
        client = TwitterApp.getRestClient(this);
        etCompose.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              tvCompteur.setText(String.valueOf(MAX_LENGHT-charSequence.length()));
                ReturnButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tweetContent = etCompose.getText().toString();
                        if (charSequence.length()>0){
                            Log.i("hm","good");
                            String filename = "myfile"+".txt";
                            String fileContents = tweetContent;
                            try (FileOutputStream fos = openFileOutput(filename, Context.MODE_APPEND)) {
                                fos.write(fileContents.getBytes(StandardCharsets.UTF_8));
                                fos.flush();
                                fos.close();
                                Log.i("ouii","ok save draft");
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Intent intent = new Intent(ComposeActivity.this,SaveOrDeleteActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });



        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComposeActivity.this, SaveActivity.class);
                startActivity(intent);
            }
        });

        ReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweetContent = etCompose.getText().toString();
                if (tweetContent.length()>0){
                    Log.i("hm","good");
                    Intent intent = new Intent(ComposeActivity.this,SaveOrDeleteActivity.class);
                    startActivity(intent);
                }
                if(tweetContent.isEmpty() ){
                    Intent intent = new Intent(ComposeActivity.this,TimelineActivity.class);
                    startActivity(intent);

                }

            }
        });

        btButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweetContent = etCompose.getText().toString();
                if(tweetContent.isEmpty() ){
                    Toast.makeText(ComposeActivity.this, "cannoot null", Toast.LENGTH_SHORT).show();
                }
                if(tweetContent.length()>MAX_LENGHT){
                    Toast.makeText(ComposeActivity.this, "trop long", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(ComposeActivity.this, tweetContent, Toast.LENGTH_SHORT).show();
                client.TweetsPublis(tweetContent,new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        Log.i(TAG,"sikse");
                        try {
                            Tweet tweet = Tweet.fromJson(json.jsonObject);
                            Log.i(TAG,"siksssse:" +tweet.body);
                            Intent i = new Intent();
                            i.putExtra("tweet", Parcels.wrap(tweet));
                            setResult(RESULT_OK, i);
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                        Log.e(TAG,"ECHEK", throwable);
                    }
                });
            }
        });
    }
}
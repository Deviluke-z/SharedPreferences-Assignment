package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  TextView tvAccountInfo;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    tvAccountInfo = findViewById(R.id.tvAccountInfo);

    Cursor cursor = getContentResolver().query(Uri.parse("content://virus/ACCOUNT"),
        null,
        null,
        null,
        null);
    while (cursor.moveToNext()){

      String website = cursor.getString(0);
      String username = cursor.getString(1);
      String email = cursor.getString(2);
      String address = cursor.getString(3);
      String payment = cursor.getString(4);

      String info = " UserName: " + username
          + "\n Website: " + website
          + "\n Email: " + email
          + "\n Address: " + address
          + "\n Payment: " + payment;

      tvAccountInfo.setText(info);
    }
  }
}
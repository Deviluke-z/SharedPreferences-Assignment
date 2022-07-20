package com.example.baseproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

  EditText edWebsite, edUserName, edEmail, edAddress;
  Spinner spPayment;
  Button btnRegister;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    edWebsite = findViewById(R.id.edWebsite);
    edUserName = findViewById(R.id.edUserName);
    edEmail = findViewById(R.id.edEmail);
    edAddress = findViewById(R.id.edAddress);
    spPayment = findViewById(R.id.spPayment);
    btnRegister = findViewById(R.id.btnRegister);

    ArrayAdapter<CharSequence> mSpinnerAdapter = ArrayAdapter.createFromResource(
        this,
        R.array.payment_info,
        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
    );
    mSpinnerAdapter.setDropDownViewResource(
        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
    spPayment.setAdapter(mSpinnerAdapter);

    btnRegister.setOnClickListener(v -> {
      try {
        getUserData(edWebsite, edUserName, edEmail, edAddress, spPayment);
      } catch (GeneralSecurityException | IOException e) {
        e.printStackTrace();
      }
    });
  }

  private void getUserData(EditText editTextWebsite, EditText editTextName,
                           EditText editTextEmail, EditText editTextAddress,
                           Spinner spinnerPayment) throws GeneralSecurityException, IOException {

    if (editTextWebsite.getText().toString().isEmpty() ||
        editTextName.getText().toString().isEmpty() ||
        editTextEmail.getText().toString().isEmpty() ||
        editTextAddress.getText().toString().isEmpty()) {

      Toast.makeText(this, "You should complete the form!", Toast.LENGTH_SHORT).show();

    } else {

      SharedPreferences.Editor editor =
          MySharedPreferences.getEncryptedSharedPreferences(this).edit();

      editor.putString(Constants.KEY_WEBSITE, editTextWebsite.getText().toString());
      editor.putString(Constants.KEY_USERNAME, editTextName.getText().toString());
      editor.putString(Constants.KEY_EMAIL, editTextEmail.getText().toString());
      editor.putString(Constants.KEY_ADDRESS, editTextAddress.getText().toString());
      editor.putString(Constants.KEY_PAYMENT, spinnerPayment.getSelectedItem().toString());

      editor.apply();

      Toast.makeText(this, "Successfully", Toast.LENGTH_SHORT).show();
    }
  }
}
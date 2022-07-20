package com.example.baseproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MySharedPreferences {
  public static SharedPreferences getEncryptedSharedPreferences(Context context)
      throws GeneralSecurityException, IOException {

    MasterKey masterKeyAlias =
        new MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build();

    Log.d("Debug", "getEncryptedSharedPreferences() " + masterKeyAlias);
    SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
        context,
        Constants.FILE_NAME,
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    );
    return sharedPreferences;
  }
}

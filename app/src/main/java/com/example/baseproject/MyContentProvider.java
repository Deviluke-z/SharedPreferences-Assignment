package com.example.baseproject;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MyContentProvider extends ContentProvider {

  public static final String AUTHORITY = "virus";

  public static final String PATH_ACCOUNT = "ACCOUNT";

  //get account
  public static final int MANAGE_ACCOUNT = 100;

  public static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

  static {
    URI_MATCHER.addURI(AUTHORITY, PATH_ACCOUNT, MANAGE_ACCOUNT);
  }

  private Account mAccount;
  private SharedPreferences mSharePre;

  @Override
  public boolean onCreate() {
    try {
      mSharePre = MySharedPreferences.getEncryptedSharedPreferences(getContext());
    } catch (GeneralSecurityException | IOException e) {
      e.printStackTrace();
    }
    return true;
  }

  @Nullable
  @Override
  public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
    switch (URI_MATCHER.match(uri)) {
      case MANAGE_ACCOUNT: {
        return handleData();
      }
    }
    Log.d("Debug", "nhảy");
    return null;
  }

  private Cursor handleData() {
    getData();
    String[] columns = new String[]{
        Constants.KEY_WEBSITE,
        Constants.KEY_USERNAME,
        Constants.KEY_EMAIL,
        Constants.KEY_ADDRESS,
        Constants.KEY_PAYMENT
    };
    MatrixCursor matrixCursor = new MatrixCursor(columns);
    matrixCursor.addRow(new Object[]{
        mAccount.getWebsiteDomain(),
        mAccount.getUsername(),
        mAccount.getEmail(),
        mAccount.getAddress(),
        mAccount.getPayment()
    });
    return matrixCursor;
  }

  private void getData() {
    mAccount = new Account(
        mSharePre.getString(Constants.KEY_WEBSITE, Constants.DEFAULT_VALUE_STRING),
        mSharePre.getString(Constants.KEY_USERNAME, Constants.DEFAULT_VALUE_STRING),
        mSharePre.getString(Constants.KEY_EMAIL, Constants.DEFAULT_VALUE_STRING),
        mSharePre.getString(Constants.KEY_ADDRESS, Constants.DEFAULT_VALUE_STRING),
        mSharePre.getString(Constants.KEY_PAYMENT, Constants.DEFAULT_VALUE_STRING)
    );
  }

  @Nullable
  @Override
  public String getType(@NonNull Uri uri) {
    return null;
  }

  @Nullable
  @Override
  public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
    return null;
  }

  @Override
  public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
    return 0;
  }

  @Override
  public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
    return 0;
  }
}

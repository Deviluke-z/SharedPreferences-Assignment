package com.example.baseproject;

public class Account {
  private String mWebsiteDomain;
  private String mUsername;
  private String mEmail;
  private String mAddress;
  private String mPayment;

  public Account(String mWebsiteDomain, String mUsername, String mEmail, String mAddress, String mPayment) {
    this.mWebsiteDomain = mWebsiteDomain;
    this.mUsername = mUsername;
    this.mEmail = mEmail;
    this.mAddress = mAddress;
    this.mPayment = mPayment;
  }

  public String getWebsiteDomain() {
    return mWebsiteDomain;
  }

  public void setWebsiteDomain(String mWebsiteDomain) {
    this.mWebsiteDomain = mWebsiteDomain;
  }

  public String getUsername() {
    return mUsername;
  }

  public void setUsername(String mUsername) {
    this.mUsername = mUsername;
  }

  public String getEmail() {
    return mEmail;
  }

  public void setEmail(String mEmail) {
    this.mEmail = mEmail;
  }

  public String getAddress() {
    return mAddress;
  }

  public void setAddress(String mAddress) {
    this.mAddress = mAddress;
  }

  public String getPayment() {
    return mPayment;
  }

  public void setPayment(String mPayment) {
    this.mPayment = mPayment;
  }

  @Override
  public String toString() {
    return "Account(" +
        "website: " + mWebsiteDomain +
        ", user: " + mUsername +
        ", email: " + mEmail +
        ", address: " + mAddress +
        ", payment: " + mPayment + ')';
  }
}

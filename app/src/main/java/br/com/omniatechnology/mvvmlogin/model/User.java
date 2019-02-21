package br.com.omniatechnology.mvvmlogin.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

public class User {

    @NonNull
    @SerializedName("usuario")
    private String mUsuario;

    @NonNull
    @SerializedName("senha")
    private String mSenha;

    public User(@NonNull String mUsuario, @NonNull String mSenha) {
        this.mUsuario = mUsuario;
        this.mSenha = mSenha;
    }

    public User() {
    }

    @NonNull
    public String getUsuario() {
        return mUsuario;
    }

    public void setUsuario(@NonNull String mUsuario) {
        this.mUsuario = mUsuario;
    }

    @NonNull
    public String getSenha() {
        return mSenha;
    }

    public void setSenha(@NonNull String mSenha) {
        this.mSenha = mSenha;
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(getUsuario()) && getSenha().length() > 2;
    }
}

package br.com.omniatechnology.mvvmlogin.model;

import android.databinding.ObservableField;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

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
        return !TextUtils.isEmpty(getUsuario()) && (getSenha() !=null && getSenha().length() > 2);
    }




    protected User(Parcel in) {
        this.mUsuario = in.readString();
        this.mSenha = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mUsuario);
        dest.writeString(this.mSenha);
    }
}

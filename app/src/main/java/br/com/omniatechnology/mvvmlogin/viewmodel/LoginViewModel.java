package br.com.omniatechnology.mvvmlogin.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;

import br.com.omniatechnology.mvvmlogin.data.ApiServiceImpl;
import br.com.omniatechnology.mvvmlogin.model.User;
import rx.Observer;
import rx.functions.Action0;

public class LoginViewModel extends BaseObservable {

    private User user;
    public ObservableInt progressBar;
    public ObservableField<String> usuario;
    public ObservableField<String> password;


    private String successMessage = "Login was successful";
    private String errorMessage = "Email or Password not valid";

    @Bindable
    public String toastMessage = null;

    public void setSnackbarMessage(String snackbarMessage) {
        this.snackbarMessage = snackbarMessage;
        notifyPropertyChanged(BR.snackbarMessage);
    }

    @Bindable
    public boolean isStartActivity;

    @Bindable
    public String snackbarMessage = null;

    public void isStartActivity(boolean isStartActivity){
        this.isStartActivity = isStartActivity;
        notifyPropertyChanged(BR.isStartActivity);
    }


    public String getSnackbarMessage() {
        return snackbarMessage;
    }


    public String getToastMessage() {
        return toastMessage;
    }


    private void setToastMessage(String toastMessage) {

        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }


    public LoginViewModel() {
        user = new User("", "");
        this.progressBar = new ObservableInt(View.GONE);
        this.usuario = new ObservableField<>();
        this.password = new ObservableField<>();

    }

    public void afterUsuarioTextChanged(CharSequence s) {
        usuario.set(s.toString());
    }

    public void afterSenhaTextChanged(CharSequence s) {
        password.set(s.toString());
    }

    private void clear(){

        usuario.set("");
        password.set("");

    }

    public void onLoginClicked() {
        user = new User(usuario.get(), password.get());
        if (!user.isInputDataValid()) {
            clear();
            isStartActivity(false);
            setSnackbarMessage(errorMessage);
        }else{
            login();
        }

    }

    private void login(){
        new ApiServiceImpl().login(user)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        progressBar.set(View.VISIBLE);
                    }
                })
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        progressBar.set(View.GONE);
                    }
                })
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                       setToastMessage(successMessage+": "+user.getUsuario());

                    }

                    @Override
                    public void onError(Throwable e) {
                        setToastMessage("Erro ao chamar Retrofit :"+e.getMessage());

                        clear();

                    }

                    @Override
                    public void onNext(User usuario) {
                        user  = usuario;
                    }
                });
         }
    }


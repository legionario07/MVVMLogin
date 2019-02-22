package br.com.omniatechnology.mvvmlogin.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;

import br.com.omniatechnology.mvvmlogin.data.ApiServiceImpl;
import br.com.omniatechnology.mvvmlogin.model.User;
import br.com.omniatechnology.mvvmlogin.presentation.IView;
import rx.Observer;
import rx.functions.Action0;

public class LoginViewModel extends BaseObservable {

    private User user;
    public ObservableInt progressBar;
    private IView view;

    private String successMessage = "Login was successful";
    private String errorMessage = "Email or Password not valid";

    @Bindable
    public String toastMessage = null;


    public String getToastMessage() {
        return toastMessage;
    }


    private void setToastMessage(String toastMessage) {

        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public LoginViewModel(IView view){
        this();
        this.view = view;
    }

    public LoginViewModel() {
        user = new User("", "");
        this.progressBar = new ObservableInt(View.GONE);
    }

    public void afterUsuarioTextChanged(CharSequence s) {
        user.setUsuario(s.toString());
    }

    public void afterSenhaTextChanged(CharSequence s) {
        user.setSenha(s.toString());
    }

    public void onLoginClicked() {
        if (!user.isInputDataValid()) {
            //setToastMessage(errorMessage);
            view.onError(errorMessage);
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
                       // setToastMessage(successMessage+": "+user.getUsuario());
                        view.onSuccess(successMessage+": "+user.getUsuario());
                    }

                    @Override
                    public void onError(Throwable e) {
                        //setToastMessage("Erro ao chamar Retrofit :"+e.getMessage());
                        view.onError("Erro ao chamar Retrofit :"+e.getMessage());
                        user = new User();
                    }

                    @Override
                    public void onNext(User usuario) {
                        user  = usuario;
                    }
                });
         }
    }


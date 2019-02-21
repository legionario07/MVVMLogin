package br.com.omniatechnology.mvvmlogin.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import br.com.omniatechnology.mvvmlogin.data.ApiServiceImpl;
import br.com.omniatechnology.mvvmlogin.model.User;
import rx.Observer;

public class LoginViewModel extends BaseObservable {

    private User user;

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

    public LoginViewModel() {
        user = new User("", "");
    }

    public void afterUsuarioTextChanged(CharSequence s) {
        user.setUsuario(s.toString());
    }

    public void afterSenhaTextChanged(CharSequence s) {
        user.setSenha(s.toString());
    }

    public void onLoginClicked() {
        if (!user.isInputDataValid())
            setToastMessage(errorMessage);
        else{
            login();
        }

    }

    private void login(){
        new ApiServiceImpl().login(user)
                //.doOnSubscribe(ViewHelper.showProgressDialogAction(context))
                //.doAfterTerminate(ViewHelper.closeProgressDialogAction(context))
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                        setToastMessage(successMessage+": "+user.getUsuario());
                    }

                    @Override
                    public void onError(Throwable e) {
                        setToastMessage("Erro ao chamar Retrofit :"+e.getMessage());
                        user = new User();
                    }

                    @Override
                    public void onNext(User usuario) {
                        user  = usuario;
                    }
                });
         }
    }


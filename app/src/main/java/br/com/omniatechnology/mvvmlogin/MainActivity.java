package br.com.omniatechnology.mvvmlogin;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.omniatechnology.mvvmlogin.databinding.ActivityMainBinding;
import br.com.omniatechnology.mvvmlogin.presentation.IView;
import br.com.omniatechnology.mvvmlogin.viewmodel.LoginViewModel;

public class MainActivity extends AppCompatActivity implements IView {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewModel(new LoginViewModel(this));
        activityMainBinding.executePendingBindings();



    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        activityMainBinding.inEmail.getText().clear();
        activityMainBinding.inPassword.getText().clear();
    }
}

package br.com.omniatechnology.mvvmlogin;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.omniatechnology.mvvmlogin.databinding.ActivityMainBinding;
import br.com.omniatechnology.mvvmlogin.viewmodel.LoginViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewModel(new LoginViewModel());
        activityMainBinding.executePendingBindings();



    }

    @BindingAdapter({"toastMessage"})
    public static void showToast(View view, String message) {
        if (message != null) {
           Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
        }

    }

    @BindingAdapter({"snackbarMessage"})
    public static void showSnackBar(View view, String message) {
        if (message != null) {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
        }

    }


}

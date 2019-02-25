package br.com.omniatechnology.mvvmlogin;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowProgressBar;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.DEBUG)
public class MainActivityTest {

    private MainActivity activity;


    @Before
    public void setUp() throws Exception{
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void shouldHaveHint() throws Exception{

        EditText edtPassword = activity.findViewById(R.id.inPassword);
        assertEquals("Password", edtPassword.getHint().toString());

        Context context = activity.getApplicationContext();

        assertNotNull(context);

    }

    @Test
    public void testButtonClickShouldShowToastOnError() throws Exception{

        Button btnLogin = activity.findViewById(R.id.btnLogin);

        assertNotNull(btnLogin);

        btnLogin.performClick();

        assertThat(ShadowToast.getTextOfLatestToast(), new IsEqual("Email or Password not valid"));


    }


    @Test
    public void testButtonClickShouldShowToastOnSucess() throws Exception{

        Button btnLogin = activity.findViewById(R.id.btnLogin);
        EditText edtUsuario = activity.findViewById(R.id.inEmail);
        EditText edtPassword = activity.findViewById(R.id.inPassword);
        edtUsuario.setText("paulo");
        edtPassword.setText("123");
        ProgressBar progressBar = activity.findViewById(R.id.progressbar);
        int visibility = progressBar.getVisibility();

        assertEquals(View.GONE, visibility);

        assertNotNull(btnLogin);
        btnLogin.performClick();

        ShadowProgressBar.visualize(progressBar);

        assertEquals(View.VISIBLE, progressBar.getVisibility());


    }




}

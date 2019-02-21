package br.com.omniatechnology.mvvmlogin.data;


import br.com.omniatechnology.mvvmlogin.model.User;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface ApiService {

    String URL_LOGIN = "usuarios/login";

    @POST(URL_LOGIN)
    Observable<User> login(@Body User user);

}

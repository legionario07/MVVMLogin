package br.com.omniatechnology.mvvmlogin.data;


import br.com.omniatechnology.mvvmlogin.model.User;
import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



public class ApiServiceImpl implements ApiService{

    private Retrofit retrofit;
    private ApiService service;

    @Override
    public Observable<User> login(User user) {

        retrofit = RetrofitConfig.getRetrofitInstance();

        service = retrofit.create(ApiService.class);


        return service.login(user).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

    }
}

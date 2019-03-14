package panto.technoevents.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDjsSingleton {
    private RetrofitDjsSingleton() {}

    private static Retrofit djsRetrofitInstance;

    public static Retrofit getDjsRetrofitInstance() {
        if (djsRetrofitInstance == null) {
            djsRetrofitInstance = new Retrofit.Builder()
                    .baseUrl("https://gist.githubusercontent.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return djsRetrofitInstance;
    }
}

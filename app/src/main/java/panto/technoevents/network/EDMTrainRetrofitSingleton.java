package panto.technoevents.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class EDMTrainRetrofitSingleton {
    private static Retrofit edmTrainRetrofitInstance;

    private EDMTrainRetrofitSingleton() {
    }

    public static Retrofit getEDMTrainRetrofitInstance() {
        if (edmTrainRetrofitInstance == null) {
            edmTrainRetrofitInstance = new Retrofit.Builder()
                    .baseUrl("https://edmtrain.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return edmTrainRetrofitInstance;
    }
}

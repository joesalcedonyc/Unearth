package panto.technoevents.network;

import java.util.List;

import androidx.annotation.NonNull;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import panto.technoevents.apimodels.djs.DjModel;
import panto.technoevents.apimodels.djs.DjResponse;
import panto.technoevents.apimodels.edmtrain.EDMTrainResponse;
import panto.technoevents.apimodels.edmtrain.Event;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DjRepository {
    private static DjRepository instance;
    private final DjApi djApi;
    private static Retrofit djRetrofitInstance;
    private static Retrofit eventsRetrofitInstance;
    private final EventsApi eventsApi;

    private DjRepository(@NonNull DjApi djApi, @NonNull EventsApi eventsApi) {
        this.djApi = djApi;
        this.eventsApi = eventsApi;
    }

    public static DjRepository getInstance() {
        if (instance == null) {
            Retrofit djRetrofit = buildRetrofit();
            DjApi djApi = djRetrofit.create(DjApi.class);
            Retrofit eventsRetrofit = buildEventsRetrofit();
            EventsApi eventsApi = eventsRetrofit.create(EventsApi.class);
            instance = new DjRepository(djApi, eventsApi);
        }
        return instance;
    }

    private static Retrofit buildRetrofit() {
        if (djRetrofitInstance == null) {
            djRetrofitInstance = new Retrofit.Builder()
              .baseUrl("https://unearth-67bf6.firebaseio.com/")
              .addConverterFactory(GsonConverterFactory.create())
              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
              .build();
        }
        return djRetrofitInstance;
    }

    private static Retrofit buildEventsRetrofit() {
        if (eventsRetrofitInstance == null) {
            eventsRetrofitInstance = new Retrofit.Builder()
              .baseUrl("https://edmtrain.com/")
              .addConverterFactory(GsonConverterFactory.create())
              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
              .build();
        }
        return eventsRetrofitInstance;
    }

    public Single<List<DjModel>> getAllDjs() {
        return djApi.getDjs()
          .map(DjResponse::getDjs)
          .subscribeOn(Schedulers.io());
    }

    public Single<List<Event>> getAllDjEvents(int djId) {
        return eventsApi.getArtistEvents(djId)
          .map(EDMTrainResponse::getEvents)
          .subscribeOn(Schedulers.io());
    }
}

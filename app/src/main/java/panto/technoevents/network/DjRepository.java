package panto.technoevents.network;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import panto.technoevents.apimodels.edmtrain.EDMTrainResponse;
import panto.technoevents.apimodels.edmtrain.Event;
import panto.technoevents.apimodels.djs.DjModel;
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
                    .baseUrl("https://gist.githubusercontent.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return djRetrofitInstance;
    }

    public static Retrofit buildEventsRetrofit() {
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
                .map(response -> response.getDjs())
                .subscribeOn(Schedulers.io());
    }

    public Single<List<Event>> getAllDjEvents(int djId) {
        return eventsApi.getArtistEvents(djId)
                .map(new Function<EDMTrainResponse, List<Event>>() {
                    @Override
                    public List<Event> apply(EDMTrainResponse response) throws Exception {
                        return response.getEvents();
                    }
                })
                .subscribeOn(Schedulers.io());
    }

//    public Single<List<Event>> getRecentEvents(int djId, int count) {
//        return getAllDjEvents(djId)
//                .flatMapIterable(items -> items)
//                .take(3)
//                .toList()
//                .toObservable();
//    }

}

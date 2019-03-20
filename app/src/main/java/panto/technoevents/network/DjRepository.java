package panto.technoevents.network;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
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
    private final EventsApi eventsApi;

    private DjRepository(@NonNull DjApi djApi, @NonNull EventsApi eventsApi) {
        this.djApi = djApi;
        this.eventsApi = eventsApi;
    }

    public static DjRepository getInstance() {
        if (instance == null) {
            Retrofit djRetrofit = buildRetrofit();
            DjApi djApi = djRetrofit.create(DjApi.class);

            Retrofit eventsRetrofit = EventRetrofitProvider.getInstance();
            EventsApi eventsApi = eventsRetrofit.create(EventsApi.class);

            instance = new DjRepository(djApi, eventsApi);
        }

        return instance;
    }

    // TODO make retrofit instance a singleton
    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Observable<List<DjModel>> getAllDjs() {
        return djApi.getDjs()
                .map(response -> response.getDjs())
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<Event>> getAllDjEvents(int djId) {
        return eventsApi.getArtistEvents(djId)
                .map(new Function<EDMTrainResponse, List<Event>>() {
                    @Override
                    public List<Event> apply(EDMTrainResponse response) throws Exception {
                        return response.getEvents();
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<Event>> getRecentEvents(int djId, int count) {
        return getAllDjEvents(djId)
                .flatMapIterable(items -> items)
                .take(3)
                .toList()
                .toObservable();
    }
}

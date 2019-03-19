package panto.technoevents.network;

import io.reactivex.Observable;
import panto.technoevents.apimodels.edmtrain.EDMTrainResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EventsApi {
    @GET("api/events?artistIds&locations?state&client=e3e2b425-c506-419c-ae55-4de36c886a03")
    Observable<EDMTrainResponse> getArtistEvents(@Query("artistIds") int artistIds);
}

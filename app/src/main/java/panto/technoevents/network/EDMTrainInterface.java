package panto.technoevents.network;

import io.reactivex.Observable;
import panto.technoevents.model.EDMTrain.EDMTrainResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EDMTrainInterface {
    @GET("api/events?artistIds&client=e3e2b425-c506-419c-ae55-4de36c886a03")
    Observable<EDMTrainResponse> getEDMResponse(@Query("artistIds") int artistIds);
}

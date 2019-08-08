package panto.technoevents.network;

import io.reactivex.Single;
import panto.technoevents.apimodels.djs.DjResponse;
import retrofit2.http.GET;

public interface DjApi {
    @GET(".json")
    Single<DjResponse> getDjs();
}

package panto.technoevents.network;

import io.reactivex.Single;
import panto.technoevents.apimodels.djs.DjResponse;
import retrofit2.http.GET;

public interface DjApi {
    @GET("joesalcedonyc/86d3bf61d0bc61d2638e6ad8100bd3b2/raw/e836b690ab0f1c29191d5d1d237aded6d31462f9/technodjs.json")
    Single<DjResponse> getDjs();
}

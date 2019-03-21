package panto.technoevents.network;

import io.reactivex.Observable;
import panto.technoevents.apimodels.djs.DjResponse;
import retrofit2.http.GET;

public interface DjApi {
    @GET("joesalcedonyc/86d3bf61d0bc61d2638e6ad8100bd3b2/raw/b1a0a2b96014c607cc7078ed33f0083d290a5e46/technodjs.json")
    Observable<DjResponse> getDjs();
}

package panto.technoevents.network;

import io.reactivex.Observable;
import panto.technoevents.apimodels.djs.DjResponse;
import retrofit2.http.GET;

public interface DjApi {
    @GET("joesalcedonyc/86d3bf61d0bc61d2638e6ad8100bd3b2/raw/9cf56863f026e481b686cd43d31edc1dd2f91dbc/technodjs.json")
    Observable<DjResponse> getDjs();
}

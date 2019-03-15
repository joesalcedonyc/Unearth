package panto.technoevents.network;

import io.reactivex.Observable;
import panto.technoevents.apimodels.djs.DjResponse;
import retrofit2.http.GET;

public interface DjApi {
    @GET("joesalcedonyc/86d3bf61d0bc61d2638e6ad8100bd3b2/raw/28fcd90c4d229b243dc731bb280e64cd3006eccf/technodjs.json")
    Observable<DjResponse> getDjs();
}

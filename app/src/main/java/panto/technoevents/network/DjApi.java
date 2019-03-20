package panto.technoevents.network;

import io.reactivex.Observable;
import panto.technoevents.apimodels.djs.DjResponse;
import retrofit2.http.GET;

public interface DjApi {
    @GET("joesalcedonyc/86d3bf61d0bc61d2638e6ad8100bd3b2/raw/72dc59261ddd6cc62a81e35111b2283dd926c26c/technodjs.json")
    Observable<DjResponse> getDjs();
}

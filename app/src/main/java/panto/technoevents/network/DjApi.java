package panto.technoevents.network;

import io.reactivex.Observable;
import panto.technoevents.apimodels.djs.DjResponse;
import retrofit2.http.GET;

public interface DjApi {
    @GET("joesalcedonyc/86d3bf61d0bc61d2638e6ad8100bd3b2/raw/a71deea6bc1b9db7483e9ab78b96202e2d9e3660/technodjs.json")
    Observable<DjResponse> getDjs();
}

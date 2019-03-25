package panto.technoevents.network;

import io.reactivex.Observable;
import io.reactivex.Single;
import panto.technoevents.apimodels.djs.DjResponse;
import retrofit2.http.GET;

public interface DjApi {
    @GET("joesalcedonyc/86d3bf61d0bc61d2638e6ad8100bd3b2/raw/b724027a7c2be64244a96271f620aaddef6d5a03/technodjs.json")
    Single<DjResponse> getDjs();
}

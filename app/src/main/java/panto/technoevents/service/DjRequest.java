package panto.technoevents.service;

import panto.technoevents.DjWrapper;
import panto.technoevents.model.DjModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DjRequest {

    @GET("joesalcedonyc/86d3bf61d0bc61d2638e6ad8100bd3b2/raw/5d74d9e8515ce32f5c8d0fa891efeaaf6f7429f3/technodjs.json")
    Call<DjWrapper> getDjs();
}

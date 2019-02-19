package panto.technoevents.service;

import panto.technoevents.DjWrapper;
import panto.technoevents.model.DjModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DjRequest {

    @GET("joesalcedonyc/86d3bf61d0bc61d2638e6ad8100bd3b2/raw/729bbaa64598e2c04054da8da827db88dafec287/technodjs.json")
    Call<DjWrapper> getDjs();
}

package panto.technoevents.service;

import panto.technoevents.DjWrapper;
import panto.technoevents.model.DjModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DjRequest {

    @GET("joesalcedonyc/86d3bf61d0bc61d2638e6ad8100bd3b2/raw/48d44a95cf9c2623b2c7963edd66153988ce5457/technodjs.json")
    Call<DjWrapper> getDjs();
}

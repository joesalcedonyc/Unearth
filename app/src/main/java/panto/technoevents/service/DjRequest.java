package panto.technoevents.service;

import panto.technoevents.DjWrapper;
import panto.technoevents.model.DjModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DjRequest {

    @GET("joesalcedonyc/86d3bf61d0bc61d2638e6ad8100bd3b2/raw/d4a048c91f6a0a6473c7eac52e4e9d4399bf2855/technodjs.json")
    Call<DjWrapper> getDjs();
}

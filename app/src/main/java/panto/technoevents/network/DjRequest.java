package panto.technoevents.network;

import panto.technoevents.model.DjResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DjRequest {
    @GET("joesalcedonyc/86d3bf61d0bc61d2638e6ad8100bd3b2/raw/e688a6291857b6b54d5a8f9fc9d5d0ec27270056/technodjs.json")
    Call<DjResponse> getDjs();
}

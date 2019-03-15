package panto.technoevents.apimodels.edmtrain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EDMTrainResponse {
    @SerializedName("data")
    private List<Events> events;

    public List<Events> getEvents() {
        return events;
    }
}

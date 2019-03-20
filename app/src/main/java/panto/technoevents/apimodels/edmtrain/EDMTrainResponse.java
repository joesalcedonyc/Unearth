package panto.technoevents.apimodels.edmtrain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EDMTrainResponse {
    @SerializedName("data")
    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }
}

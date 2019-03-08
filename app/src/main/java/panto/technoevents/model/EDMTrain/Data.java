package panto.technoevents.model.EDMTrain;

import java.util.List;

public class Data {
    private String link;
    private String ticketLink;
    private String date;
    private Venue venue;
    private List<ArtistList> artistList;

    public String getLink() {
        return link;
    }

    public String getTicketLink() {
        return ticketLink;
    }

    public String getDate() {
        return date;
    }

    public Venue getVenue() {
        return venue;
    }

    public List<ArtistList> getArtistList() {
        return artistList;
    }
}

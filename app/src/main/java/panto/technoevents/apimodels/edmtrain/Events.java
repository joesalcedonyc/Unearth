package panto.technoevents.apimodels.edmtrain;

import java.util.List;

public class Events {
    private String link;
    private String ticketLink;
    private String name;
    private String date;
    private Venue venue;
    private List<ArtistList> artistList;

    public String getLink() {
        return link;
    }

    public String getTicketLink() {
        return ticketLink;
    }

    public String getName() {
        return name;
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

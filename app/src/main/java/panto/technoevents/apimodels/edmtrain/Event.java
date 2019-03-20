package panto.technoevents.apimodels.edmtrain;

import java.util.List;

public class Event {
    private String link;
    private String ticketLink;
    private String name;
    private String date;
    private Venue venue;
    private List<Artist> artistList;

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

    public List<Artist> getArtistList() {
        return artistList;
    }

}

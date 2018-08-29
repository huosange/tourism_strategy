package com.tourism.strategy.tourism_strategy.model;

import java.util.List;

public class AttractionTrips {
    private List<Trip> attraction_trips;
    private int id;
    private String name;
    private int trips_count;

    public List<Trip> getAttraction_trips() {
        return attraction_trips;
    }

    public void setAttraction_trips(List<Trip> attraction_trips) {
        this.attraction_trips = attraction_trips;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTrips_count() {
        return trips_count;
    }

    public void setTrips_count(int trips_count) {
        this.trips_count = trips_count;
    }

    public class Trip{
        private int photos_count;
        private String comment;
        private int node_id;
        private List<Note> notes;

        public int getPhotos_count() {
            return photos_count;
        }

        public void setPhotos_count(int photos_count) {
            this.photos_count = photos_count;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getNode_id() {
            return node_id;
        }

        public void setNode_id(int node_id) {
            this.node_id = node_id;
        }

        public List<Note> getNotes() {
            return notes;
        }

        public void setNotes(List<Note> notes) {
            this.notes = notes;
        }
    }
}

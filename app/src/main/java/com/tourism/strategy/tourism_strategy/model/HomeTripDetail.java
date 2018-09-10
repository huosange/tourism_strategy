package com.tourism.strategy.tourism_strategy.model;

import java.util.List;

public class HomeTripDetail {
    private String name;
    private List<TripDay> trip_days;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TripDay> getTrip_days() {
        return trip_days;
    }

    public void setTrip_days(List<TripDay> trip_days) {
        this.trip_days = trip_days;
    }

    public class TripDay {
        private String trip_date;
        private int day;
        private List<TripNode> nodes;

        public String getTrip_date() {
            return trip_date;
        }

        public void setTrip_date(String trip_date) {
            this.trip_date = trip_date;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public List<TripNode> getNodes() {
            return nodes;
        }

        public void setNodes(List<TripNode> nodes) {
            this.nodes = nodes;
        }

        public class TripNode {
            private List<TripNote> notes;

            public List<TripNote> getNotes() {
                return notes;
            }

            public void setNotes(List<TripNote> notes) {
                this.notes = notes;
            }

            public class TripNote {
                private String description;

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }
            }
        }
    }
}

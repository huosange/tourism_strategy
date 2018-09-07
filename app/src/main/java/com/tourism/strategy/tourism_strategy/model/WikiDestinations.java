package com.tourism.strategy.tourism_strategy.model;

import com.zaihuishou.expandablerecycleradapter.model.ExpandableListItem;

import java.io.Serializable;
import java.util.List;

public class WikiDestinations {
    private String category;
    private List<Destination> destinations;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public class Destination{
        private int id;
        private String name_zh_cn;
        private String name_en;
        private String image_url;
        private List<Child> children;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName_zh_cn() {
            return name_zh_cn;
        }

        public void setName_zh_cn(String name_zh_cn) {
            this.name_zh_cn = name_zh_cn;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public List<Child> getChildren() {
            return children;
        }

        public void setChildren(List<Child> children) {
            this.children = children;
        }

        public class Child{
            private int id;
            private String name_zh_cn;
            private String name_en;
            private boolean locked;
            private int destination_trips_count;
            private String image_url;
            private int items_count;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName_zh_cn() {
                return name_zh_cn;
            }

            public void setName_zh_cn(String name_zh_cn) {
                this.name_zh_cn = name_zh_cn;
            }

            public String getName_en() {
                return name_en;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }

            public boolean isLocked() {
                return locked;
            }

            public void setLocked(boolean locked) {
                this.locked = locked;
            }

            public int getDestination_trips_count() {
                return destination_trips_count;
            }

            public void setDestination_trips_count(int destination_trips_count) {
                this.destination_trips_count = destination_trips_count;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public int getItems_count() {
                return items_count;
            }

            public void setItems_count(int items_count) {
                this.items_count = items_count;
            }
        }
    }

}

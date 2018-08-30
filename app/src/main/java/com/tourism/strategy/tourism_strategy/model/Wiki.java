package com.tourism.strategy.tourism_strategy.model;

import java.io.Serializable;
import java.util.List;

public class Wiki {
    private int category_type;
    private List<Page> pages;

    public int getCategory_type() {
        return category_type;
    }

    public void setCategory_type(int category_type) {
        this.category_type = category_type;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public class Page implements Serializable{
        private int id;
        private String title;
        private List<Child> children;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Child> getChildren() {
            return children;
        }

        public void setChildren(List<Child> children) {
            this.children = children;
        }

        public class Child implements Serializable{
            private int id;
            private String title;
            private List<Section> sections;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public List<Section> getSections() {
                return sections;
            }

            public void setSections(List<Section> sections) {
                this.sections = sections;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public class Section implements Serializable{
                private int id;
                private String title;
                private String description;
                private List<Photo> photos;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getId() {

                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public List<Photo> getPhotos() {
                    return photos;
                }

                public void setPhotos(List<Photo> photos) {
                    this.photos = photos;
                }

                public class Photo implements Serializable{
                    private String image_url;
                    private int image_width;
                    private int image_height;
                    private String description;

                    public String getImage_url() {
                        return image_url;
                    }

                    public void setImage_url(String image_url) {
                        this.image_url = image_url;
                    }

                    public int getImage_width() {
                        return image_width;
                    }

                    public void setImage_width(int image_width) {
                        this.image_width = image_width;
                    }

                    public int getImage_height() {
                        return image_height;
                    }

                    public void setImage_height(int image_height) {
                        this.image_height = image_height;
                    }

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
}

package com.tourism.strategy.tourism_strategy.model;

import java.util.List;

public class PlanDetail {
    private int id;
    private String name;
    private String description;
    private List<PlanDay> plan_days;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PlanDay> getPlan_days() {
        return plan_days;
    }

    public void setPlan_days(List<PlanDay> plan_days) {
        this.plan_days = plan_days;
    }

    public class PlanDay{
        private int id;
        private String memo;
        private List<PlanNode> plan_nodes;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public List<PlanNode> getPlan_nodes() {
            return plan_nodes;
        }

        public void setPlan_nodes(List<PlanNode> plan_nodes) {
            this.plan_nodes = plan_nodes;
        }

        public class PlanNode{
            private int id;
            private String tips;
            private String image_url;
            private String entry_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTips() {
                return tips;
            }

            public void setTips(String tips) {
                this.tips = tips;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getEntry_name() {
                return entry_name;
            }

            public void setEntry_name(String entry_name) {
                this.entry_name = entry_name;
            }
        }
    }
}

package com.tourism.strategy.tourism_strategy.model;

import java.util.List;

public class AttractionContent extends MultipleItem{
    private String description;
    private List<Note> notes;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}

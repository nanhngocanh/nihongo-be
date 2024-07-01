package com.hedspi.nihongobe.payload.data;

import com.hedspi.nihongobe.enums.Type;

public class Theory {
    private Integer id;
    private Type type;
    private String term;
    private String definition;
    private String description;

    public Theory(Type type, String term, String definition, String description) {
        this.type = type;
        this.term = term;
        this.definition = definition;
        this.description = description;
    }

    public Theory(Integer id, Type type, String term, String definition, String description) {
        this.id = id;
        this.type = type;
        this.term = term;
        this.definition = definition;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Theory() {
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

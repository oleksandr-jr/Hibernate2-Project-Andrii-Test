package org.anch.entity;

public enum Rating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");
    public final String label;

    Rating(String label) {
        this.label = label;
    }
}

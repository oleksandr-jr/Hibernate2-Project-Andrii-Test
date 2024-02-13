package org.anch.entity;

public enum Rating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");
    private String label;

    Rating(String label) {
        this.label = label;
    }

    public static Rating valueOfLabel(String label) {
        for (Rating rating : values()) {
            if (rating.label.equals(label)) {
                return rating;
            }
        }
        throw new IllegalArgumentException("There is no such rating in the rating list");
    }

    public String getLabel() {
        return this.label;
    }

}

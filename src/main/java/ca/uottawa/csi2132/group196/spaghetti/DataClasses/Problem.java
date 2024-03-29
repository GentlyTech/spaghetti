package ca.uottawa.csi2132.group196.spaghetti.DataClasses;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.MappedField;

public class Problem {
    @MappedField
    private String caption;

    @MappedField
    private String description;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

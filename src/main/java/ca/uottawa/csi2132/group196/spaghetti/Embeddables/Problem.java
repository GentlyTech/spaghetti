package ca.uottawa.csi2132.group196.spaghetti.Embeddables;

import jakarta.persistence.Embeddable;

@Embeddable
public class Problem {
    private String caption;
    
    private String description;

    protected Problem() {

    }

    public Problem(String caption, String description) {
        this.caption = caption;
        this.description = description;
    }
    
    public String getCaption() {
        return this.caption;
    }
    
    public String getDescription() {
        return this.description;
    }
}

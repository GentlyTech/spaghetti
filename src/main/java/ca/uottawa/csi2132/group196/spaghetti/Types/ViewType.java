package ca.uottawa.csi2132.group196.spaghetti.Types;

public enum ViewType {
    BORING(0),
    OCEAN(1),
    MOUNTAIN(2);

    private final int value;
    ViewType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ViewType{" +
                "value=" + value +
                '}';
    }
}

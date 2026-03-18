package model;

public class Location {
    private String id;
    private String name;
    private String area;
    private boolean wheelchairAccessible;
    private boolean tactileSupport;
    private boolean liftAvailable;
    private String notes;

    public Location(String id, String name, String area,
                    boolean wheelchairAccessible,
                    boolean tactileSupport,
                    boolean liftAvailable,
                    String notes) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.wheelchairAccessible = wheelchairAccessible;
        this.tactileSupport = tactileSupport;
        this.liftAvailable = liftAvailable;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public boolean isWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public boolean isTactileSupport() {
        return tactileSupport;
    }

    public boolean isLiftAvailable() {
        return liftAvailable;
    }

    public String getNotes() {
        return notes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setWheelchairAccessible(boolean wheelchairAccessible) {
        this.wheelchairAccessible = wheelchairAccessible;
    }

    public void setTactileSupport(boolean tactileSupport) {
        this.tactileSupport = tactileSupport;
    }

    public void setLiftAvailable(boolean liftAvailable) {
        this.liftAvailable = liftAvailable;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAccessibilitySummary() {
        return "Wheelchair: " + (wheelchairAccessible ? "Yes" : "No")
                + ", Tactile: " + (tactileSupport ? "Yes" : "No")
                + ", Lift: " + (liftAvailable ? "Yes" : "No");
    }

    @Override
    public String toString() {
        return "Location ID: " + id
                + " | Name: " + name
                + " | Area: " + area
                + " | " + getAccessibilitySummary()
                + " | Notes: " + notes;
    }
}
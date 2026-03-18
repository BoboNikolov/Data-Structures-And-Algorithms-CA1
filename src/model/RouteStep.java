package model;

// Is one step in a route. And the objects are stored inside the singly linked list.

public class RouteStep {
    private int stepNumber;
    private String fromLocationId;
    private String toLocationId;
    private String instruction;
    private int estimatedMinutes;
    private boolean accessible;

    public RouteStep(int stepNumber, String fromLocationId, String toLocationId,
                     String instruction, int estimatedMinutes, boolean accessible) {
        this.stepNumber = stepNumber;
        this.fromLocationId = fromLocationId;
        this.toLocationId = toLocationId;
        this.instruction = instruction;
        this.estimatedMinutes = estimatedMinutes;
        this.accessible = accessible;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public String getFromLocationId() {
        return fromLocationId;
    }

    public String getToLocationId() {
        return toLocationId;
    }

    public String getInstruction() {
        return instruction;
    }

    public int getEstimatedMinutes() {
        return estimatedMinutes;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public void setFromLocationId(String fromLocationId) {
        this.fromLocationId = fromLocationId;
    }

    public void setToLocationId(String toLocationId) {
        this.toLocationId = toLocationId;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void setEstimatedMinutes(int estimatedMinutes) {
        this.estimatedMinutes = estimatedMinutes;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    @Override
    public String toString() {
        return "Step " + stepNumber
                + " | From: " + fromLocationId
                + " | To: " + toLocationId
                + " | Instruction: " + instruction
                + " | Minutes: " + estimatedMinutes
                + " | Accessible: " + (accessible ? "Yes" : "No");
    }
}

package model;

public abstract class DockIssue {
    protected String issueId;
    protected String title;
    protected String locationId;
    protected String description;
    protected int severity;
    protected String status;

    public DockIssue(String issueId, String title, String locationId,
                     String description, int severity, String status) {
        this.issueId = issueId;
        this.title = title;
        this.locationId = locationId;
        this.description = description;
        this.severity = severity;
        this.status = status;
    }

    public String getIssueId() {
        return issueId;
    }

    public String getTitle() {
        return title;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getDescription() {
        return description;
    }

    public int getSeverity() {
        return severity;
    }

    public String getStatus() {
        return status;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract String getIssueType();

    public String getPriorityLabel() {
        if (severity == 1) return "Critical";
        if (severity == 2) return "High";
        if (severity == 3) return "Medium";
        return "Low";
    }

    public String getDisplayText() {
        return "Issue ID: " + issueId
                + " | Type: " + getIssueType()
                + " | Title: " + title
                + " | Location: " + locationId
                + " | Severity: " + severity + " (" + getPriorityLabel() + ")"
                + " | Status: " + status
                + " | Description: " + description;
    }

    @Override
    public String toString() {
        return getDisplayText();
    }
}
package model;

// Subclass representing a safety-related issue

public class SafetyIssue extends DockIssue {

    public SafetyIssue(String issueId, String title, String locationId,
                       String description, int severity, String status) {
        super(issueId, title, locationId, description, severity, status);
    }

    @Override
    public String getIssueType() {
        return "Safety";
    }
}

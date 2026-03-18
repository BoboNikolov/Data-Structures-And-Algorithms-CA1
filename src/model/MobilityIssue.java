package model;

// Subclass representing a mobility-related issue.

public class MobilityIssue extends DockIssue {

    public MobilityIssue(String issueId, String title, String locationId,
                         String description, int severity, String status) {
        super(issueId, title, locationId, description, severity, status);
    }

    @Override
    public String getIssueType() {
        return "Mobility";
    }
}

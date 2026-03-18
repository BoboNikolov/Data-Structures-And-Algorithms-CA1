package gui;

import adt.IssuePriorityQueue;
import adt.LocationMap;
import adt.SinglyLinkedList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.DockIssue;
import model.Location;
import model.MobilityIssue;
import model.RouteStep;
import model.SafetyIssue;
import model.VisualAccessIssue;

public class MainFrame extends JFrame {

    private LocationMap locationMap;
    private SinglyLinkedList<RouteStep> routeList;
    private IssuePriorityQueue issueQueue;

    private JTextArea locationOutputArea;
    private JTextArea routeOutputArea;
    private JTextArea issueOutputArea;
    private JTextArea dashboardOutputArea;

    private JTextField txtLocationId;
    private JTextField txtLocationName;
    private JTextField txtLocationArea;
    private JTextField txtLocationNotes;
    private JCheckBox chkWheelchair;
    private JCheckBox chkTactile;
    private JCheckBox chkLift;
    private JTextField txtLocationSearchId;
    private JTextField txtLocationDeleteId;
    private JTextField txtLocationUpdateId;

    private JTextField txtStepNumber;
    private JTextField txtFromLocation;
    private JTextField txtToLocation;
    private JTextField txtInstruction;
    private JTextField txtMinutes;
    private JCheckBox chkRouteAccessible;
    private JTextField txtRouteIndexRead;
    private JTextField txtRouteIndexDelete;
    private JTextField txtRouteIndexUpdate;

    private JTextField txtIssueId;
    private JTextField txtIssueTitle;
    private JTextField txtIssueLocationId;
    private JTextField txtIssueDescription;
    private JComboBox<String> cmbIssueType;
    private JComboBox<String> cmbSeverity;

    public MainFrame() {
        locationMap = new LocationMap();
        routeList = new SinglyLinkedList<RouteStep>();
        issueQueue = new IssuePriorityQueue();

        setTitle("DockAccess Manager");
        setSize(1200, 760);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initialiseGui();
    }

    private void initialiseGui() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Locations", createLocationPanel());
        tabbedPane.addTab("Routes", createRoutePanel());
        tabbedPane.addTab("Issues Queue", createIssuePanel());
        tabbedPane.addTab("Dashboard", createDashboardPanel());
        add(tabbedPane);
    }

    private JPanel createLocationPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createTitledBorder("Location Management"));

        txtLocationId = new JTextField();
        txtLocationName = new JTextField();
        txtLocationArea = new JTextField();
        txtLocationNotes = new JTextField();
        chkWheelchair = new JCheckBox("Wheelchair Accessible");
        chkTactile = new JCheckBox("Tactile Support");
        chkLift = new JCheckBox("Lift Available");
        txtLocationSearchId = new JTextField();
        txtLocationDeleteId = new JTextField();
        txtLocationUpdateId = new JTextField();

        formPanel.add(new JLabel("Location ID"));
        formPanel.add(txtLocationId);
        formPanel.add(new JLabel("Location Name"));
        formPanel.add(txtLocationName);
        formPanel.add(new JLabel("Area"));
        formPanel.add(txtLocationArea);
        formPanel.add(new JLabel("Notes"));
        formPanel.add(txtLocationNotes);
        formPanel.add(chkWheelchair);
        formPanel.add(chkTactile);
        formPanel.add(chkLift);
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel("Search by ID"));
        formPanel.add(txtLocationSearchId);
        formPanel.add(new JLabel("Delete by ID"));
        formPanel.add(txtLocationDeleteId);
        formPanel.add(new JLabel("Update by Existing ID"));
        formPanel.add(txtLocationUpdateId);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 8, 8));
        JButton btnAdd = new JButton("Create Location");
        JButton btnRead = new JButton("Read Location");
        JButton btnUpdate = new JButton("Update Location");
        JButton btnDelete = new JButton("Delete Location");
        JButton btnViewAll = new JButton("View All");
        JButton btnViewAccessible = new JButton("Accessible Only");
        JButton btnSearchArea = new JButton("Search Area");
        JButton btnClear = new JButton("Clear Form");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRead);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnViewAll);
        buttonPanel.add(btnViewAccessible);
        buttonPanel.add(btnSearchArea);
        buttonPanel.add(btnClear);

        locationOutputArea = createOutputArea();

        btnAdd.addActionListener(e -> createLocation());
        btnRead.addActionListener(e -> readLocation());
        btnUpdate.addActionListener(e -> updateLocation());
        btnDelete.addActionListener(e -> deleteLocation());
        btnViewAll.addActionListener(e -> showAllLocations());
        btnViewAccessible.addActionListener(e -> showAccessibleLocations());
        btnSearchArea.addActionListener(e -> searchLocationsByArea());
        btnClear.addActionListener(e -> clearLocationFields());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(locationOutputArea), BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createRoutePanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createTitledBorder("Route Step Management"));

        txtStepNumber = new JTextField();
        txtFromLocation = new JTextField();
        txtToLocation = new JTextField();
        txtInstruction = new JTextField();
        txtMinutes = new JTextField();
        chkRouteAccessible = new JCheckBox("Accessible Step");
        txtRouteIndexRead = new JTextField();
        txtRouteIndexDelete = new JTextField();
        txtRouteIndexUpdate = new JTextField();

        formPanel.add(new JLabel("Step Number"));
        formPanel.add(txtStepNumber);
        formPanel.add(new JLabel("From Location ID"));
        formPanel.add(txtFromLocation);
        formPanel.add(new JLabel("To Location ID"));
        formPanel.add(txtToLocation);
        formPanel.add(new JLabel("Instruction"));
        formPanel.add(txtInstruction);
        formPanel.add(new JLabel("Estimated Minutes"));
        formPanel.add(txtMinutes);
        formPanel.add(chkRouteAccessible);
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel("Read Step by Index"));
        formPanel.add(txtRouteIndexRead);
        formPanel.add(new JLabel("Delete Step by Index"));
        formPanel.add(txtRouteIndexDelete);
        formPanel.add(new JLabel("Update Step by Index"));
        formPanel.add(txtRouteIndexUpdate);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 8, 8));
        JButton btnAddStep = new JButton("Create Step");
        JButton btnReadStep = new JButton("Read Step");
        JButton btnUpdateStep = new JButton("Update Step");
        JButton btnDeleteStep = new JButton("Delete Step");
        JButton btnViewRoute = new JButton("View Route");
        JButton btnSummary = new JButton("Route Summary");
        JButton btnClearRoute = new JButton("Clear Route");
        JButton btnClearRouteForm = new JButton("Clear Form");

        buttonPanel.add(btnAddStep);
        buttonPanel.add(btnReadStep);
        buttonPanel.add(btnUpdateStep);
        buttonPanel.add(btnDeleteStep);
        buttonPanel.add(btnViewRoute);
        buttonPanel.add(btnSummary);
        buttonPanel.add(btnClearRoute);
        buttonPanel.add(btnClearRouteForm);

        routeOutputArea = createOutputArea();

        btnAddStep.addActionListener(e -> createRouteStep());
        btnReadStep.addActionListener(e -> readRouteStep());
        btnUpdateStep.addActionListener(e -> updateRouteStep());
        btnDeleteStep.addActionListener(e -> deleteRouteStep());
        btnViewRoute.addActionListener(e -> showAllRouteSteps());
        btnSummary.addActionListener(e -> showRouteSummary());
        btnClearRoute.addActionListener(e -> clearRouteList());
        btnClearRouteForm.addActionListener(e -> clearRouteFields());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(routeOutputArea), BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createIssuePanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createTitledBorder("Issue Priority Queue"));

        txtIssueId = new JTextField();
        txtIssueTitle = new JTextField();
        txtIssueLocationId = new JTextField();
        txtIssueDescription = new JTextField();
        cmbIssueType = new JComboBox<String>(new String[]{"Mobility", "Visual Access", "Safety"});
        cmbSeverity = new JComboBox<String>(new String[]{"1", "2", "3", "4"});

        formPanel.add(new JLabel("Issue ID"));
        formPanel.add(txtIssueId);
        formPanel.add(new JLabel("Title"));
        formPanel.add(txtIssueTitle);
        formPanel.add(new JLabel("Location ID"));
        formPanel.add(txtIssueLocationId);
        formPanel.add(new JLabel("Description"));
        formPanel.add(txtIssueDescription);
        formPanel.add(new JLabel("Issue Type"));
        formPanel.add(cmbIssueType);
        formPanel.add(new JLabel("Severity (1 highest priority)"));
        formPanel.add(cmbSeverity);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 8, 8));
        JButton btnEnqueue = new JButton("Add Issue");
        JButton btnPeek = new JButton("Peek Next");
        JButton btnDequeue = new JButton("Process Next");
        JButton btnResolve = new JButton("Mark Peek Resolved");
        JButton btnViewAll = new JButton("View Queue");
        JButton btnOpenOnly = new JButton("View Open Issues");
        JButton btnByLocation = new JButton("Search by Location");
        JButton btnClearIssueForm = new JButton("Clear Form");

        buttonPanel.add(btnEnqueue);
        buttonPanel.add(btnPeek);
        buttonPanel.add(btnDequeue);
        buttonPanel.add(btnResolve);
        buttonPanel.add(btnViewAll);
        buttonPanel.add(btnOpenOnly);
        buttonPanel.add(btnByLocation);
        buttonPanel.add(btnClearIssueForm);

        issueOutputArea = createOutputArea();

        btnEnqueue.addActionListener(e -> createIssue());
        btnPeek.addActionListener(e -> peekIssue());
        btnDequeue.addActionListener(e -> dequeueIssue());
        btnResolve.addActionListener(e -> resolvePeekedIssue());
        btnViewAll.addActionListener(e -> showAllIssues());
        btnOpenOnly.addActionListener(e -> showOpenIssues());
        btnByLocation.addActionListener(e -> searchIssuesByLocation());
        btnClearIssueForm.addActionListener(e -> clearIssueFields());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(issueOutputArea), BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        JButton btnRefresh = new JButton("Refresh Dashboard");
        topPanel.add(btnRefresh);

        dashboardOutputArea = createOutputArea();
        dashboardOutputArea.setPreferredSize(new Dimension(1000, 600));

        btnRefresh.addActionListener(e -> refreshDashboard());

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(dashboardOutputArea), BorderLayout.CENTER);

        return panel;
    }

    private JTextArea createOutputArea() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        return area;
    }

    private void createLocation() {
        try {
            String id = txtLocationId.getText().trim();
            String name = txtLocationName.getText().trim();
            String area = txtLocationArea.getText().trim();
            String notes = txtLocationNotes.getText().trim();

            if (id.isEmpty() || name.isEmpty() || area.isEmpty()) {
                showMessage("Location ID, name and area are required.");
                return;
            }

            Location location = new Location(
                    id,
                    name,
                    area,
                    chkWheelchair.isSelected(),
                    chkTactile.isSelected(),
                    chkLift.isSelected(),
                    notes
            );

            boolean created = locationMap.create(location);

            if (created) {
                locationOutputArea.setText("Location created successfully.\n\n" + location.toString());
                refreshDashboard();
            } else {
                locationOutputArea.setText("Location creation failed. ID may already exist.");
            }
        } catch (Exception ex) {
            locationOutputArea.setText("Error creating location: " + ex.getMessage());
        }
    }

    private void readLocation() {
        String id = txtLocationSearchId.getText().trim();

        if (id.isEmpty()) {
            showMessage("Enter a Location ID to read.");
            return;
        }

        Location location = locationMap.read(id);

        if (location == null) {
            locationOutputArea.setText("No location found with ID: " + id);
        } else {
            locationOutputArea.setText(location.toString());
        }
    }

    private void updateLocation() {
        try {
            String existingId = txtLocationUpdateId.getText().trim();

            if (existingId.isEmpty()) {
                showMessage("Enter the existing ID of the location to update.");
                return;
            }

            if (locationMap.read(existingId) == null) {
                locationOutputArea.setText("No existing location found for update.");
                return;
            }

            String id = txtLocationId.getText().trim();
            String name = txtLocationName.getText().trim();
            String area = txtLocationArea.getText().trim();
            String notes = txtLocationNotes.getText().trim();

            if (id.isEmpty() || name.isEmpty() || area.isEmpty()) {
                showMessage("New location values must be entered in the form.");
                return;
            }

            Location updated = new Location(
                    id,
                    name,
                    area,
                    chkWheelchair.isSelected(),
                    chkTactile.isSelected(),
                    chkLift.isSelected(),
                    notes
            );

            boolean updatedResult = locationMap.update(existingId, updated);

            if (updatedResult) {
                if (!existingId.equals(id)) {
                    locationMap.delete(existingId);
                    locationMap.create(updated);
                }
                locationOutputArea.setText("Location updated successfully.\n\n" + updated.toString());
                refreshDashboard();
            } else {
                locationOutputArea.setText("Location update failed.");
            }
        } catch (Exception ex) {
            locationOutputArea.setText("Error updating location: " + ex.getMessage());
        }
    }

    private void deleteLocation() {
        String id = txtLocationDeleteId.getText().trim();

        if (id.isEmpty()) {
            showMessage("Enter a Location ID to delete.");
            return;
        }

        boolean deleted = locationMap.delete(id);

        if (deleted) {
            locationOutputArea.setText("Location deleted successfully: " + id);
            refreshDashboard();
        } else {
            locationOutputArea.setText("No location found to delete.");
        }
    }

    private void showAllLocations() {
        List<Location> locations = locationMap.getAll();

        if (locations.isEmpty()) {
            locationOutputArea.setText("No locations stored.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("All Locations\n");
        sb.append("=============\n");
        for (Location location : locations) {
            sb.append(location).append("\n");
        }
        locationOutputArea.setText(sb.toString());
    }

    private void showAccessibleLocations() {
        List<Location> locations = locationMap.getAccessibleLocations();

        if (locations.isEmpty()) {
            locationOutputArea.setText("No accessible locations found.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Accessible Locations\n");
        sb.append("====================\n");
        for (Location location : locations) {
            sb.append(location).append("\n");
        }
        locationOutputArea.setText(sb.toString());
    }

    private void searchLocationsByArea() {
        String area = txtLocationArea.getText().trim();

        if (area.isEmpty()) {
            showMessage("Enter an area in the Area field.");
            return;
        }

        List<Location> matches = locationMap.searchByArea(area);

        if (matches.isEmpty()) {
            locationOutputArea.setText("No locations found in area: " + area);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Locations in area: ").append(area).append("\n");
        sb.append("=============================\n");
        for (Location location : matches) {
            sb.append(location).append("\n");
        }
        locationOutputArea.setText(sb.toString());
    }

    private void clearLocationFields() {
        txtLocationId.setText("");
        txtLocationName.setText("");
        txtLocationArea.setText("");
        txtLocationNotes.setText("");
        txtLocationSearchId.setText("");
        txtLocationDeleteId.setText("");
        txtLocationUpdateId.setText("");
        chkWheelchair.setSelected(false);
        chkTactile.setSelected(false);
        chkLift.setSelected(false);
    }

    private void createRouteStep() {
        try {
            int stepNumber = Integer.parseInt(txtStepNumber.getText().trim());
            String from = txtFromLocation.getText().trim();
            String to = txtToLocation.getText().trim();
            String instruction = txtInstruction.getText().trim();
            int minutes = Integer.parseInt(txtMinutes.getText().trim());
            boolean accessible = chkRouteAccessible.isSelected();

            if (from.isEmpty() || to.isEmpty() || instruction.isEmpty()) {
                showMessage("All route text fields are required.");
                return;
            }

            RouteStep step = new RouteStep(stepNumber, from, to, instruction, minutes, accessible);
            routeList.create(step);

            routeOutputArea.setText("Route step added successfully.\n\n" + step.toString());
            refreshDashboard();
        } catch (NumberFormatException ex) {
            routeOutputArea.setText("Step Number and Estimated Minutes must be valid numbers.");
        } catch (Exception ex) {
            routeOutputArea.setText("Error creating route step: " + ex.getMessage());
        }
    }

    private void readRouteStep() {
        try {
            int index = Integer.parseInt(txtRouteIndexRead.getText().trim());
            RouteStep step = routeList.read(index);

            if (step == null) {
                routeOutputArea.setText("No route step found at index: " + index);
            } else {
                routeOutputArea.setText(step.toString());
            }
        } catch (NumberFormatException ex) {
            routeOutputArea.setText("Enter a valid index number.");
        }
    }

    private void updateRouteStep() {
        try {
            int index = Integer.parseInt(txtRouteIndexUpdate.getText().trim());
            int stepNumber = Integer.parseInt(txtStepNumber.getText().trim());
            String from = txtFromLocation.getText().trim();
            String to = txtToLocation.getText().trim();
            String instruction = txtInstruction.getText().trim();
            int minutes = Integer.parseInt(txtMinutes.getText().trim());
            boolean accessible = chkRouteAccessible.isSelected();

            RouteStep updated = new RouteStep(stepNumber, from, to, instruction, minutes, accessible);
            boolean result = routeList.update(index, updated);

            if (result) {
                routeOutputArea.setText("Route step updated successfully.\n\n" + updated.toString());
                refreshDashboard();
            } else {
                routeOutputArea.setText("Update failed. Invalid route index.");
            }
        } catch (NumberFormatException ex) {
            routeOutputArea.setText("Enter valid numeric values.");
        }
    }

    private void deleteRouteStep() {
        try {
            int index = Integer.parseInt(txtRouteIndexDelete.getText().trim());
            boolean deleted = routeList.delete(index);

            if (deleted) {
                routeOutputArea.setText("Route step deleted successfully at index: " + index);
                refreshDashboard();
            } else {
                routeOutputArea.setText("Delete failed. Invalid route index.");
            }
        } catch (NumberFormatException ex) {
            routeOutputArea.setText("Enter a valid route index.");
        }
    }

    private void showAllRouteSteps() {
        List<RouteStep> steps = routeList.getAll();

        if (steps.isEmpty()) {
            routeOutputArea.setText("No route steps stored.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("All Route Steps\n");
        sb.append("===============\n");
        for (int i = 0; i < steps.size(); i++) {
            sb.append("Index ").append(i).append(": ").append(steps.get(i)).append("\n");
        }
        routeOutputArea.setText(sb.toString());
    }

    private void showRouteSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Route Summary\n");
        sb.append("=============\n");
        sb.append("Total steps: ").append(routeList.size()).append("\n");
        sb.append("Accessible steps: ").append(routeList.countAccessibleSteps()).append("\n");
        sb.append("Total estimated minutes: ").append(routeList.getTotalEstimatedMinutes()).append("\n");
        routeOutputArea.setText(sb.toString());
    }

    private void clearRouteList() {
        routeList.clear();
        routeOutputArea.setText("All route steps cleared.");
        refreshDashboard();
    }

    private void clearRouteFields() {
        txtStepNumber.setText("");
        txtFromLocation.setText("");
        txtToLocation.setText("");
        txtInstruction.setText("");
        txtMinutes.setText("");
        txtRouteIndexRead.setText("");
        txtRouteIndexDelete.setText("");
        txtRouteIndexUpdate.setText("");
        chkRouteAccessible.setSelected(false);
    }

    private void createIssue() {
        try {
            String issueId = txtIssueId.getText().trim();
            String title = txtIssueTitle.getText().trim();
            String locationId = txtIssueLocationId.getText().trim();
            String description = txtIssueDescription.getText().trim();
            String type = cmbIssueType.getSelectedItem().toString();
            int severity = Integer.parseInt(cmbSeverity.getSelectedItem().toString());

            if (issueId.isEmpty() || title.isEmpty() || locationId.isEmpty() || description.isEmpty()) {
                showMessage("All issue fields are required.");
                return;
            }

            DockIssue issue;

            if ("Mobility".equals(type)) {
                issue = new MobilityIssue(issueId, title, locationId, description, severity, "Open");
            } else if ("Visual Access".equals(type)) {
                issue = new VisualAccessIssue(issueId, title, locationId, description, severity, "Open");
            } else {
                issue = new SafetyIssue(issueId, title, locationId, description, severity, "Open");
            }

            issueQueue.enqueue(issue);
            issueOutputArea.setText("Issue added to priority queue.\n\n" + issue.getDisplayText());
            refreshDashboard();
        } catch (Exception ex) {
            issueOutputArea.setText("Error adding issue: " + ex.getMessage());
        }
    }

    private void peekIssue() {
        DockIssue issue = issueQueue.peek();

        if (issue == null) {
            issueOutputArea.setText("Issue queue is empty.");
        } else {
            issueOutputArea.setText("Next highest priority issue:\n\n" + issue.getDisplayText());
        }
    }

    private void dequeueIssue() {
        DockIssue issue = issueQueue.dequeue();

        if (issue == null) {
            issueOutputArea.setText("Issue queue is empty.");
        } else {
            issueOutputArea.setText("Processed issue removed from queue:\n\n" + issue.getDisplayText());
            refreshDashboard();
        }
    }

    private void resolvePeekedIssue() {
        boolean result = issueQueue.markPeekedIssueResolved();

        if (result) {
            DockIssue issue = issueQueue.peek();
            if (issue != null) {
                issueOutputArea.setText("Top issue marked resolved:\n\n" + issue.getDisplayText());
            } else {
                issueOutputArea.setText("Top issue was marked resolved.");
            }
            refreshDashboard();
        } else {
            issueOutputArea.setText("No issue available to resolve.");
        }
    }

    private void showAllIssues() {
        List<DockIssue> issues = issueQueue.getAll();

        if (issues.isEmpty()) {
            issueOutputArea.setText("No issues in priority queue.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("All Issues in Priority Order\n");
        sb.append("============================\n");
        for (DockIssue issue : issues) {
            sb.append(issue.getDisplayText()).append("\n");
        }
        issueOutputArea.setText(sb.toString());
    }

    private void showOpenIssues() {
        List<DockIssue> issues = issueQueue.getOpenIssues();

        if (issues.isEmpty()) {
            issueOutputArea.setText("No open issues found.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Open Issues\n");
        sb.append("===========\n");
        for (DockIssue issue : issues) {
            sb.append(issue.getDisplayText()).append("\n");
        }
        issueOutputArea.setText(sb.toString());
    }

    private void searchIssuesByLocation() {
        String locationId = txtIssueLocationId.getText().trim();

        if (locationId.isEmpty()) {
            showMessage("Enter a Location ID in the issue form.");
            return;
        }

        List<DockIssue> matches = issueQueue.getIssuesByLocation(locationId);

        if (matches.isEmpty()) {
            issueOutputArea.setText("No issues found for location: " + locationId);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Issues for Location ").append(locationId).append("\n");
        sb.append("============================\n");
        for (DockIssue issue : matches) {
            sb.append(issue.getDisplayText()).append("\n");
        }
        issueOutputArea.setText(sb.toString());
    }

    private void clearIssueFields() {
        txtIssueId.setText("");
        txtIssueTitle.setText("");
        txtIssueLocationId.setText("");
        txtIssueDescription.setText("");
        cmbIssueType.setSelectedIndex(0);
        cmbSeverity.setSelectedIndex(0);
    }

    private void refreshDashboard() {
        int totalLocations = locationMap.size();
        int totalAccessibleLocations = locationMap.getAccessibleLocations().size();
        int totalRouteSteps = routeList.size();
        int totalAccessibleSteps = routeList.countAccessibleSteps();
        int totalRouteMinutes = routeList.getTotalEstimatedMinutes();
        int totalIssues = issueQueue.size();
        int openIssues = issueQueue.getOpenIssues().size();

        DockIssue nextIssue = issueQueue.peek();

        StringBuilder sb = new StringBuilder();
        sb.append("DockAccess Manager Dashboard\n");
        sb.append("========================================\n");
        sb.append("Total locations: ").append(totalLocations).append("\n");
        sb.append("Accessible locations: ").append(totalAccessibleLocations).append("\n");
        sb.append("Total route steps: ").append(totalRouteSteps).append("\n");
        sb.append("Accessible route steps: ").append(totalAccessibleSteps).append("\n");
        sb.append("Total route time: ").append(totalRouteMinutes).append(" minutes\n");
        sb.append("Issues in priority queue: ").append(totalIssues).append("\n");
        sb.append("Open issues: ").append(openIssues).append("\n\n");

        sb.append("Next issue to process\n");
        sb.append("----------------------------------------\n");
        if (nextIssue == null) {
            sb.append("No issue currently queued.\n\n");
        } else {
            sb.append(nextIssue.getDisplayText()).append("\n\n");
        }

        sb.append("Stored Locations\n");
        sb.append("----------------------------------------\n");
        for (Location location : locationMap.getAll()) {
            sb.append(location).append("\n");
        }

        sb.append("\nStored Route Steps\n");
        sb.append("----------------------------------------\n");
        for (RouteStep step : routeList.getAll()) {
            sb.append(step).append("\n");
        }

        sb.append("\nStored Issues\n");
        sb.append("----------------------------------------\n");
        for (DockIssue issue : issueQueue.getAll()) {
            sb.append(issue.getDisplayText()).append("\n");
        }

        dashboardOutputArea.setText(sb.toString());
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
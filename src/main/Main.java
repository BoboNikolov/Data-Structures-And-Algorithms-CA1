package main;

import adt.IssuePriorityQueue;
import adt.LocationMap;
import adt.SinglyLinkedList;
import gui.MainFrame;
import javax.swing.SwingUtilities;
import model.DockIssue;
import model.Location;
import model.MobilityIssue;
import model.RouteStep;
import model.SafetyIssue;
import model.VisualAccessIssue;

public class Main {

    public static void main(String[] args) {
        runConsoleTests();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    private static void runConsoleTests() {
        System.out.println("========== TESTING START ==========\n");

        testLocationMap();
        testRouteList();
        testIssuePriorityQueue();

        System.out.println("\n========== TESTING END ==========");
    }

    private static void testLocationMap() {
        System.out.println("---- Testing HashMap ADT: LocationMap ----");

        LocationMap map = new LocationMap();

        Location l1 = new Location("L01", "Grand Canal Dock Station", "Docklands", true, true, true, "Main rail access point");
        Location l2 = new Location("L02", "Convention Centre", "North Wall", true, false, true, "Public venue");
        Location l3 = new Location("L03", "Dock Plaza", "Docklands", false, false, false, "Needs improvements");

        System.out.println("Create L01: " + map.create(l1));
        System.out.println("Create L02: " + map.create(l2));
        System.out.println("Create L03: " + map.create(l3));

        System.out.println("Read L02: " + map.read("L02"));
        System.out.println("All Locations: " + map.getAll());

        Location updated = new Location("L03", "Dock Plaza East", "Docklands", true, true, false, "Temporary ramp installed");
        System.out.println("Update L03: " + map.update("L03", updated));
        System.out.println("Read L03 after update: " + map.read("L03"));

        System.out.println("Delete L02: " + map.delete("L02"));
        System.out.println("All Locations after delete: " + map.getAll());
        System.out.println("Accessible Locations: " + map.getAccessibleLocations());
        System.out.println();
    }

    private static void testRouteList() {
        System.out.println("---- Testing Singly Linked List ADT ----");

        SinglyLinkedList<RouteStep> routeList = new SinglyLinkedList<RouteStep>();

        RouteStep s1 = new RouteStep(1, "L01", "L03", "Use ramp beside station entrance", 4, true);
        RouteStep s2 = new RouteStep(2, "L03", "L04", "Follow marked footpath", 6, true);
        RouteStep s3 = new RouteStep(3, "L04", "L05", "Cross at signal-controlled crossing", 3, true);

        System.out.println("Create step 1: " + routeList.create(s1));
        System.out.println("Create step 2: " + routeList.create(s2));
        System.out.println("Create step 3: " + routeList.create(s3));

        System.out.println("Read index 1: " + routeList.read(1));
        System.out.println("All Steps: " + routeList.getAll());

        RouteStep updatedStep = new RouteStep(2, "L03", "L04", "Follow tactile path on left side", 7, true);
        System.out.println("Update index 1: " + routeList.update(1, updatedStep));
        System.out.println("Read index 1 after update: " + routeList.read(1));

        System.out.println("Delete index 0: " + routeList.delete(0));
        System.out.println("All Steps after delete: " + routeList.getAll());

        System.out.println("Total route minutes: " + routeList.getTotalEstimatedMinutes());
        System.out.println("Accessible steps count: " + routeList.countAccessibleSteps());
        System.out.println();
    }

    private static void testIssuePriorityQueue() {
        System.out.println("---- Testing Priority Queue ADT ----");

        IssuePriorityQueue queue = new IssuePriorityQueue();

        DockIssue i1 = new MobilityIssue("I01", "Lift Out of Service", "L01", "Main lift unavailable", 1, "Open");
        DockIssue i2 = new VisualAccessIssue("I02", "Tactile Path Damage", "L03", "Tiles missing", 3, "Open");
        DockIssue i3 = new SafetyIssue("I03", "Broken Handrail", "L04", "Handrail unstable", 2, "Open");

        queue.enqueue(i1);
        queue.enqueue(i2);
        queue.enqueue(i3);

        System.out.println("All issues in priority order: " + queue.getAll());
        System.out.println("Peek highest priority: " + queue.peek());
        System.out.println("Mark peeked issue resolved: " + queue.markPeekedIssueResolved());
        System.out.println("Peek after resolve: " + queue.peek());
        System.out.println("Dequeue processed issue: " + queue.dequeue());
        System.out.println("Remaining issues: " + queue.getAll());
        System.out.println("Open issues only: " + queue.getOpenIssues());
        System.out.println();
    }
}
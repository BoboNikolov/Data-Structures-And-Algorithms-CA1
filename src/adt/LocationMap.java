package adt;

import interfaces.CrudOperations;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Location;

public class LocationMap implements CrudOperations<Location, String> {

    private HashMap<String, Location> map;

    public LocationMap() {
        map = new HashMap<String, Location>();
    }

    @Override
    public boolean create(Location item) {
        if (item == null || item.getId() == null || item.getId().trim().isEmpty()) {
            return false;
        }

        if (map.containsKey(item.getId())) {
            return false;
        }

        map.put(item.getId(), item);
        return true;
    }

    @Override
    public Location read(String key) {
        return map.get(key);
    }

    @Override
    public boolean update(String key, Location updatedItem) {
        if (key == null || updatedItem == null || !map.containsKey(key)) {
            return false;
        }

        map.put(key, updatedItem);
        return true;
    }

    @Override
    public boolean delete(String key) {
        if (key == null || !map.containsKey(key)) {
            return false;
        }

        map.remove(key);
        return true;
    }

    @Override
    public List<Location> getAll() {
        return new ArrayList<Location>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }

    public List<Location> getAccessibleLocations() {
        List<Location> accessible = new ArrayList<Location>();
        for (Location location : map.values()) {
            if (location.isWheelchairAccessible() || location.isLiftAvailable() || location.isTactileSupport()) {
                accessible.add(location);
            }
        }
        return accessible;
    }

    public List<Location> searchByArea(String area) {
        List<Location> matches = new ArrayList<Location>();
        if (area == null) {
            return matches;
        }

        for (Location location : map.values()) {
            if (location.getArea().equalsIgnoreCase(area.trim())) {
                matches.add(location);
            }
        }
        return matches;
    }
}
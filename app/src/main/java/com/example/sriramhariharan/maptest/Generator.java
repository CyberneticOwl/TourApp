package com.example.sriramhariharan.maptest;

/**
 * Created by User1 on 8/29/2016.
 */
public class Generator {
    /*public static ArrayList<Place> getTour(double maxDistance, double lat, double lon) {
        ArrayList<Place> tour = new ArrayList<Place>();
        ArrayList<Place> locations = getLocations();
        int i = 0;
        while(i < locations.size())
            if(isMatch(locations.get(i)))
                ++i;
            else
                locations.remove(i);
        double traveled = 0;
        tour.add(new Place("Start", "", "", lat, lon, ""));

        while(true) {
            if(locations.size() == 0)
                return tour;
            Place min = locations.get(0);
            for(int j = 0; j < locations.size(); j++) {
                Place loc = locations.get(j);
                if(getDistance(tour.get(tour.size() - 1), loc) < getDistance(tour.get(tour.size() - 1), min)
                        && (traveled < maxDistance / 2
                        || getDistance(tour.get(0), loc) < getDistance(tour.get(0), tour.get(tour.size() - 1))))
                    min = loc;
            }
            double dist = getDistance(tour.get(tour.size() - 1), min);
            if(traveled + dist + getDistance(tour.get(0), min) <= maxDistance) {
                tour.add(min);
                locations.remove(min);
                traveled += dist;
            } else
                return tour;
        }
    }

    public static double getDistance(Place l1, Place l2) {
        double lat1 = l1.getLatitude();
        double lon1 = l1.getLongitude();
        double lat2 = l2.getLatitude();
        double lon2 = l2.getLongitude();
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return dist;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    private static boolean isMatch(Place loc) {
        if(Values.history && loc.getTags().contains("history"))
            return true;
        if(Values.culture && loc.getTags().contains("culture"))
            return true;
        if(Values.art && loc.getTags().contains("art"))
            return true;
        if(Values.science && loc.getTags().contains("science"))
            return true;
        if(Values.food && loc.getTags().contains("food"))
            return true;
        if(Values.museum && loc.getTags().contains("museum"))
            return true;
        if(Values.shopping && loc.getTags().contains("shopping"))
            return true;
        if(Values.park && loc.getTags().contains("park"))
            return true;
        if(Values.landmark && loc.getTags().contains("landmark"))
            return true;
        if(Values.spiritual && loc.getTags().contains("spiritual"))
            return true;
        if(Values.entertainment && loc.getTags().contains("entertainment"))
            return true;
        return false;
    }*/
}

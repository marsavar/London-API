package LondonAPI.London.GetResponses.Helpers;

public class DistanceFromLondon {

    /**
    Formula to calculate distance between two points on Earth.
     Source: GeeksforGeeks
     Adapted for distance from London.
     */

    private static final double LondonLatitude = 51.5074;
    private static final double LondonLongitude = -0.1278;

    public static double distance(double lat, double lon)
    {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat - LondonLatitude);
        double dLon = Math.toRadians(lon - LondonLongitude);

        // convert to radians
        double lat1 = Math.toRadians(LondonLatitude);
        lat = Math.toRadians(lat);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat);

        double rad = 3958.8; // radius of the Earth in miles
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }


}

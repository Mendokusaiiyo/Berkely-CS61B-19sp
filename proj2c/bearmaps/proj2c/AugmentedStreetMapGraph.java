package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.KDTree;
import bearmaps.proj2ab.Point;

import java.security.Key;
import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    HashMap<Point, Long> pointToID;
    KDTree myKDTree;
    MyTrieSet myTrieSet;
    HashMap<String, List<Node>> cleanedNameToNodes;
    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
        List<Node> nodes = this.getNodes();
        List<Point> points = new LinkedList<>();
        myTrieSet = new MyTrieSet();
        pointToID = new HashMap<>();
        cleanedNameToNodes = new HashMap<>();
        List<Node> nodesWithCleanedName;
        for (Node x : nodes) {
            if (x.name() != null) {
                String cleanedName = x.name().replaceAll("[^A-Za-z]", "").toLowerCase();
                myTrieSet.add(cleanedName);
                if (!cleanedNameToNodes.containsKey(cleanedName)) {
                    cleanedNameToNodes.put(cleanedName, new LinkedList<>());
                }
                // Replace the old list that mapped to the specific cleanedName with new one.
                nodesWithCleanedName = cleanedNameToNodes.get(cleanedName);
                nodesWithCleanedName.add(x);
                cleanedNameToNodes.put(cleanedName, nodesWithCleanedName);
            }
            long id = x.id();
            if (!this.neighbors(id).isEmpty()) {
                Point p = new Point(x.lon(), x.lat());
                points.add(p);
                pointToID.put(p, id);
            }
        }
        myKDTree = new KDTree(points);
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        return pointToID.get(myKDTree.nearest(lon, lat));
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        String cleanName = prefix.replaceAll("[^A-Za-z]", "").toLowerCase();
        List<String> nameWithPrefix = myTrieSet.keysWithPrefix(cleanName);
        List<String> matchName = new LinkedList<>();
        for (String s : nameWithPrefix) {
            for (Node n : cleanedNameToNodes.get(s)) {
                matchName.add(n.name());
            }
        }

        return matchName;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        List<Map<String, Object>> locations = new LinkedList<>();
        String cleanedLocationName = locationName.replaceAll("[^A-Za-z]", "").toLowerCase();

        if (cleanedLocationName.contains(cleanedLocationName)) {
            for (Node n :cleanedNameToNodes.get(cleanedLocationName)) {
                HashMap<String, Object> locationInfo = new HashMap<>();
                locationInfo.put("lat", n.lat());
                locationInfo.put("lon", n.lon());
                locationInfo.put("name", n.name());
                locationInfo.put("id", n.id());
                locations.add(locationInfo);
            }
        }
        return locations;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}

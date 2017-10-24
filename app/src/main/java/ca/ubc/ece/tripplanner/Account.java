package ca.ubc.ece.tripplanner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {

    /** Name */
    public String name;

    /** Route */
    public List<String> destinations;

    /** Preferences */
    public List<String> preferences;

    /** Starting Location */
    public String startLoc;

    public Account() {
        name = "User Name";

        destinations = new ArrayList<String>();
        destinations.add("Museum of Anthropology");
        destinations.add("Stanley Park");
        destinations.add("Science World");

        preferences = new ArrayList<String>();
        preferences.add("History");
        preferences.add("Outdoor");
        preferences.add("Technology");

        startLoc = "UBC MCLD";
    }
}

package training.busboard.web;

import training.busboard.BusArrival;

import java.util.List;

public class BusInfo {
    
    private final String postcode;
    private final List<BusArrival> buses;

    public BusInfo(String postcode, List<BusArrival> buses) {
        this.postcode = postcode;
        this.buses = buses;
    }
    public String getPostcode() {
        return postcode;
    }
    public List<BusArrival> getBuses() {
        return buses;
    }
}

package training.busboard.web;

public class BusInfo {
    
    private final String postcode;
    private final String lineName;
    private final String destinationName;
    private final int timeToStation;

    public BusInfo(String postcode, String lineName, String destinationName, int timeToStation) {
        this.postcode = postcode;
        this.lineName = lineName;
        this.destinationName = destinationName;
        this.timeToStation = timeToStation;
    }
    public String getPostcode() {
        return postcode;
    }
    public String getLineName() {
        return lineName;
    }
    public String getDestinationName() {
        return destinationName;
    }
    public int getTimeToStation() {

        return timeToStation;
    }
}

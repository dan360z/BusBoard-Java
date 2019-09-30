package training.busboard.web;

public class BusInfo {
    private final String postcode;
    private final String lineName;

    public BusInfo(String postcode, String lineName) {
        this.postcode = postcode;
        this.lineName = lineName;
    }
    public String getPostcode() {
        return postcode;
    }
    public String getLineName() {
        return lineName;
    }
}

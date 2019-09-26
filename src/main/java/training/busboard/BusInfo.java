package training.busboard;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

class BusInfo {
    public String Id;
    public String lineId;
    public String vehicleID = "hello";
}

package training.busboard;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class BusArrival {
    public String lineName;
    public String destinationName;
    public int timeToStation;

    public int getTimeToStation() {
        return timeToStation;
    }
}

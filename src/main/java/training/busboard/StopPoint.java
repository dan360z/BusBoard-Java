package training.busboard;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class StopPoint {
     //public List <Codes> stopPoints;
    public List <NaptanIDs> stopPoints;
    
}

@JsonIgnoreProperties(ignoreUnknown = true)

 class NaptanIDs {
    
    public String naptanId;
}





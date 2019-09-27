package training.busboard.web;

public class BusInfo {
    
    private final String postcode;
    private final String lineID;

    public BusInfo(String postcode, String lineID)
    {
        this.lineID = lineID;
        this.postcode = postcode;
        
    }
    
    public String getLineID(){
        return lineID;
    }

    public String getPostcode() {
        
        return postcode;
    }
}

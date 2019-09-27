package training.busboard;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.List;

public class TFL {

    public String getNaptanId(String lat, String lon) throws KeyManagementException, NoSuchAlgorithmException {
        Client client = new SslContextAndClient().getClient();
        StopPoint stops = client.target("https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanPublicBusCoachTram&lat=" + lat + "&lon=" + lon)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(StopPoint.class);

        return stops.stopPoints.get(1).naptanId;
    }

    public List<BusInfo> getOrderedBusInfo(String naptanId) throws KeyManagementException, NoSuchAlgorithmException {
        Client client = new SslContextAndClient().getClient();
        List<BusInfo> response = client.target("https://api.tfl.gov.uk/StopPoint/" + naptanId + "/Arrivals")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<BusInfo>>() {
                });
        Comparator<BusInfo> compareByTime = Comparator.comparing(BusInfo::getTimeToStation);

        response.sort(compareByTime);

        return response;
    }
}

package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Comparator;
import java.util.List;

public class TFL {
    private SSLContext getSSLContext() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext sslcontext = SSLContext.getInstance("TLS");
        sslcontext.init(null, new TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) {
            }

            public void checkServerTrusted(X509Certificate[] arg0, String arg1) {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }
        }, new java.security.SecureRandom());
        return sslcontext;
    }

    private Client getClient() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = getSSLContext();
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).sslContext(sslContext).hostnameVerifier((s1, s2) -> true).build();
        return client;
    }

    public String getNaptanId(String lat, String lon) throws KeyManagementException, NoSuchAlgorithmException {
        Client client = getClient();
        StopPoint stops = client.target("https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanPublicBusCoachTram&lat=" + lat + "&lon=" + lon)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(StopPoint.class);

        return stops.stopPoints.get(1).naptanId;
    }

    public List<BusInfo> getOrderedBusInfo(String naptanId) throws KeyManagementException, NoSuchAlgorithmException {
        Client client = getClient();
        List<BusInfo> response = client.target("https://api.tfl.gov.uk/StopPoint/" + naptanId + "/Arrivals")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<BusInfo>>() {
                });
        Comparator<BusInfo> compareByTime = Comparator.comparing(BusInfo::getTimeToStation);

        response.sort(compareByTime);

        return response;
    }
}

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
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String args[]) throws KeyManagementException, NoSuchAlgorithmException {

        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "9090");
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyPort", "9090");

        SSLContext sslcontext = SSLContext.getInstance("TLS");

        sslcontext.init(null, new TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) {}
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) {}
            public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
        }}, new java.security.SecureRandom());

        /*Scanner myObj = new Scanner(System.in);
        System.out.println("Enter code");

        String userInput = myObj.nextLine();*/

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).sslContext(sslcontext).hostnameVerifier((s1, s2) -> true).build();
        List<BusInfo> response = client.target("https://api.tfl.gov.uk/StopPoint/490008660N/Arrivals")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<BusInfo>>() {});

        for (int i = 0; i < 5; i++) {
            System.out.println("Bus No: " + response.get(i).lineName + " 🚍, Time to station: " + response.get(i).timeToStation + " ⏱, Arrives at: " + response.get(i).expectedArrival + " ⏱");
        }

    }

}
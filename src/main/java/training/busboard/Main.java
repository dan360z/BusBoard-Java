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
import java.util.Scanner;


public class Main {

    public static void main(String args[]) throws KeyManagementException, NoSuchAlgorithmException {
       
         findMyBus();
         
    }

    public static void findMyBus() throws KeyManagementException, NoSuchAlgorithmException{

        setProxySettings();

        String userInput = run();

        Results postcodeResult = postCodeResult(userInput);

        TFL tflClient = new TFL();

        String naptanId = tflClient.getNaptanId(postcodeResult.latitude, postcodeResult.longitude);

        List <BusInfo> response = tflClient.getOrderedBusInfo(naptanId);

        printNextFiveBusses(response);

    }

    private static void setProxySettings(){
        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "9090");
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyPort", "9090");
    }

    private static SSLContext getSSLContext() throws KeyManagementException, NoSuchAlgorithmException {
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

    private static Client getClient() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = getSSLContext();
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).sslContext(sslContext).hostnameVerifier((s1, s2) -> true).build();
        return client;
    }

    private static String run() {
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter postcode");

       return myObj.nextLine();
    }

    private static Results postCodeResult(String userInput) throws KeyManagementException, NoSuchAlgorithmException {
        Client client = getClient();
        Postcode postCode = client.target("https://api.postcodes.io/postcodes/" + userInput.toLowerCase())

                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Postcode.class);

        return postCode.result;
    }

    private static void printNextFiveBusses(List<BusInfo> response) {
        for (int i = 0; i < 5; i++) {
            System.out.println("Bus No: " + response.get(i).lineName + " To " + response.get(i).destinationName + " 🚍, Arrives in " + response.get(i).timeToStation / 60 + " Minutes " + response.get(i).timeToStation % 60 + " Seconds. ⏱");
        }
    }
}
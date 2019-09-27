package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class PostcodeResults {

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

    static Results postCodeResult(String userInput) throws KeyManagementException, NoSuchAlgorithmException {
        Client client = getClient();
        Postcode postCode = client.target("https://api.postcodes.io/postcodes/" + userInput.toLowerCase())

                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Postcode.class);

        return postCode.result;
    }
}

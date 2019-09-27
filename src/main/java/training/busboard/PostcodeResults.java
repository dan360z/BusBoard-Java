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

    static Results postCodeResult(String userInput) throws KeyManagementException, NoSuchAlgorithmException {
        Client client = new SslContextAndClient().getClient();
        Postcode postCode = client.target("https://api.postcodes.io/postcodes/" + userInput.toLowerCase())

                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Postcode.class);

        return postCode.result;
    }
}

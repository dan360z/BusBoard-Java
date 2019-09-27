package training.busboard;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class PostcodeResults {

    static Results postCodeResult(String userInput) throws KeyManagementException, NoSuchAlgorithmException {
        Client client = new SslContextAndClient().getClient();
        Postcode postCode = client.target("https://api.postcodes.io/postcodes/" + userInput.toLowerCase())

                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Postcode.class);

        return postCode.result;
    }
}

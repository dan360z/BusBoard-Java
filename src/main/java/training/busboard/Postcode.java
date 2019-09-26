package training.busboard;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

class Postcode {
    Results result;
}

class Results {
    String country;
};
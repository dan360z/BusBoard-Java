package training.busboard;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

class Postcode {
    public Results result;
}

@JsonIgnoreProperties(ignoreUnknown = true)

class Results {
    public String country;
};
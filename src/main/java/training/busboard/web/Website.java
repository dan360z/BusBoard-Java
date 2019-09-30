package training.busboard.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.busboard.BusArrival;
import training.busboard.PostcodeResults;
import training.busboard.Results;
import training.busboard.TFL;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static training.busboard.Main.setProxySettings;

@Controller
@EnableAutoConfiguration
public class Website {

    @RequestMapping("/")
    ModelAndView home() {

        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) throws NoSuchAlgorithmException, KeyManagementException {

        Results postcodeResult = PostcodeResults.postCodeResult(postcode);

        TFL tflClient = new TFL();

        String naptanId = tflClient.getNaptanId(postcodeResult.latitude, postcodeResult.longitude);

        List<BusArrival> response = tflClient.getOrderedBusInfo(naptanId);

        return new ModelAndView("info", "busInfo", new BusInfo(postcode, response) ) ;
    }

    public static void main(String[] args) throws Exception {
        setProxySettings();
        SpringApplication.run(Website.class, args);
    }

}
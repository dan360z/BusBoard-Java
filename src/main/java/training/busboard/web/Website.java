package training.busboard.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.busboard.Arrivals;

import java.util.ArrayList;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class Website {

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) {
        
        List<Arrivals> arrivals = new ArrayList<>();
        Arrivals arrival = new Arrivals();
        arrival.destinationName="burgess hill";
        arrival.lineName="43";
        arrival.timeToStation=34;
        arrivals.add(arrival);
        
        return new ModelAndView("info", "busInfo", new BusInfo(arrivals.get(0).lineName, arrivals.get(0).destinationName) ) ;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}
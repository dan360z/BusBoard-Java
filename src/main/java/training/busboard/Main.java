package training.busboard;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String args[]) throws KeyManagementException, NoSuchAlgorithmException {
       
         findMyBus();
         
    }

    public static void findMyBus() throws KeyManagementException, NoSuchAlgorithmException{

        setProxySettings();

        String userInput = run();

        Results postcodeResult = PostcodeResults.postCodeResult(userInput);

        TFL tflClient = new TFL();

        String naptanId = tflClient.getNaptanId(postcodeResult.latitude, postcodeResult.longitude);

        List <BusArrival> response = tflClient.getOrderedBusInfo(naptanId);

        printNextFiveBusses(response);

    }

    public static void setProxySettings(){
        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "9090");
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyPort", "9090");
    }

    private static String run() {
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter postcode");

       return myObj.nextLine();
    }

    private static void printNextFiveBusses(List<BusArrival> response) {
        for (int i = 0; i < 5; i++) {
            System.out.println("Bus No: " + response.get(i).lineName + " To " + response.get(i).destinationName + " 🚍, Arrives in " + response.get(i).timeToStation / 60 + " Minutes " + response.get(i).timeToStation % 60 + " Seconds. ⏱");
        }
    }
}
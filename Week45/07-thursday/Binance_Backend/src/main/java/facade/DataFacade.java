package facade;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import exceptions.CustomException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Runi
 */
public class DataFacade {

    private static final DataFacade INSTANCE = new DataFacade();

    private static final String URL = "https://api.binance.com/api/v3/avgPrice?symbol=";
    private static List<String> symbols = new ArrayList(Arrays.asList("ADATUSD", "ATOMTUSD", "BATTUSD", "BNBTUSD", "BTCTUSD", "EOSTUSD", "ETCTUSD", "ETHTUSD", "LTCTUSD", "NEOTUSD", "TRXTUSD", "XRPTUSD"));
    private static ExecutorService execute;
    private static List<Future<String[]>> futureList;
    private static Gson GSON = new Gson();

    /**
     * Singleton
     */
    private DataFacade() {
    }

    public static DataFacade getInstance() {
        /*
        *Hacky, but I need it initialized every time,
        *as it is shutdown after a call.
        *It's either this or not shutting down, I think.
        *I'm not sure if this could do something bad, probably not.
         */
        execute = Executors.newCachedThreadPool();
        return INSTANCE;
    }

    public Map<String, Double> getPrices() throws InterruptedException, ExecutionException {
        futureList = new ArrayList();
        Map<String, Double> result = new HashMap();

        //Get all info in an array of {symbol, price}
        for (final String symbol : symbols) {
            futureList.add(execute.submit(() -> getPrice(symbol)));
        }

        //Sort info into requested Map
        for (Future<String[]> future : futureList) {
            result.put(future.get()[0], Double.parseDouble((future.get()[1])));
        }
        execute.shutdown();
        System.out.println("DONE");
        return result;
    }

    private String[] getPrice(String symbol) throws CustomException {
        try {
            URL url = new URL(URL + symbol);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json;charset=UTF-8");
            try (Scanner scan = new Scanner(con.getInputStream())) {
                String price = "";
                while (scan.hasNext()) { //should only loop once
                    price = GSON.fromJson(scan.nextLine(), JsonObject.class).get("price").toString();
                }
                price = price.substring(1, price.length() - 1); //Remove quotation marks LOL
                System.out.println(symbol + ": \t" + price);
                return new String[]{symbol, price};
            }
        } catch (Exception ex) {
            throw new CustomException("go away warnings" + ex); //lazy
        }

    }

}

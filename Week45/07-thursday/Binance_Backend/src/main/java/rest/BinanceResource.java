package rest;

import facade.DataFacade;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("binance")
public class BinanceResource {
DataFacade facade = DataFacade.getInstance();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Map demo() throws InterruptedException, ExecutionException {
        return facade.getPrices();
    }
}

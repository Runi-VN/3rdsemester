package rest;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service (Autogenerated)
 *
 * @author runi1
 */
@Path("proxy")
public class ProxyResource {

    @Context
    private UriInfo context;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        JsonObject dbMsg = new JsonObject();
        dbMsg.addProperty("msg", "DER ER HUL IGENNEM");
        return GSON.toJson(dbMsg);
    }


    /**
     * Retrieves representation of an instance of rest.ProxyResource
     *
     * @param proxyURL
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/newproxy")
    @Produces(MediaType.APPLICATION_JSON)
    public String dotheProxyThing(@QueryParam("URL") String proxyURL) throws Exception {
        System.out.println("PROXY: " + proxyURL);
        URL url = new URL(proxyURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        System.out.println(jsonStr);
        return jsonStr;
    }

    /**
     * PUT method for updating or creating an instance of ProxyResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}

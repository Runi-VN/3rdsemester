package exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author runi1
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(Throwable ex) {
        Response.StatusType type = getStatusType(ex);
        Logger.getLogger(GenericExceptionMapper.class.getName()).log(Level.SEVERE, null, ex);
        return buildResponse(type);
    }

    private Response.StatusType getStatusType(Throwable ex) {
        if (ex instanceof WebApplicationException) {
            return ((WebApplicationException) ex).getResponse().getStatusInfo();
        }
        return Response.Status.INTERNAL_SERVER_ERROR;

    }

    /**
     * Not in original code.
     *
     * If we encounter an error 500-505 (SERVER_ERROR) we return the message
     * requested by the assignment.
     *
     * Otherwise, standard error message is returned.
     *
     * @param type
     * @return
     */
    private Response buildResponse(Response.StatusType type) {
        ExceptionDTO err;
        if (type.getFamily().equals(Response.Status.Family.SERVER_ERROR)) {
            err = new ExceptionDTO(type.getStatusCode(), "Internal Server Problem. We are sorry for the inconvenience");
        } else {
            err = new ExceptionDTO(type.getStatusCode(), type.getReasonPhrase());
        }
        return Response.status(type.getStatusCode())
                .entity(gson.toJson(err))
                .type(MediaType.APPLICATION_JSON).
                build();
    }
}

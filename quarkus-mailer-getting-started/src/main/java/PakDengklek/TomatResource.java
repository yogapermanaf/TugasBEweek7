package PakDengklek;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@Path("/tomat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class TomatResource {

    @GET
    public Response getAllTomat() {
        return Response.ok(Tomat.listAll()).build();
    }
    @POST
    @Transactional
    public Tomat add (JsonObject body){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();

        Tomat tomat = new Tomat();
        tomat.komoditas = body.getString("komoditas");
        tomat.total = body.getInt("total");
        tomat.createAt = dtf.format(date).toString();
        tomat.updateAt = dtf.format(date).toString();
        tomat.persist();
        return tomat;

    }

}

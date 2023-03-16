package PakDengklek;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@ApplicationScoped
@Path("/tambah")
public class TomatManager {

    @Inject
    TomatRepository tomatRepository;

    @POST
    @Transactional
    public Tomat add (Tomat tomat)
    {
        tomat.persist();
        return tomat;
    }


}


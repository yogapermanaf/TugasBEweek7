package PakDengklek;
import javax.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class TomatRepository implements PanacheRepository<Tomat> {

}

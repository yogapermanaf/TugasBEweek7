package PakDengklek;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "nama_tabel")
public class Tomat extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String komoditas;

    public Integer total;

    public String createAt;

    public String updateAt;

    // constructors, getters, setters, and other methods

    public void setJenisTomat(String jenisTomat) {
        this.komoditas = jenisTomat;
    }

    public void setJumlahTomat(Integer jumlahTomat) {
        this.total = jumlahTomat;
    }
}

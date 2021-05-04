package fi.tarina.tarinamittaus.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ASENNETTUANTURI", schema = "TARINAM")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AsennettuAnturi implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @Column(name = "ASENNUSKOHTAINEN_ID", nullable = false, unique = true)
    @SequenceGenerator(
            name = "seq_gen",
            sequenceName = "asennettuanturi_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_gen"
    )
    private @Setter @Getter Integer asennuskohtainen_id;

    @Column(name = "MALLI")
    private @Setter @Getter String malli;

    @Column(name = "GPS_LAT")
    private @Setter @Getter String gpsLat;

    @Column(name = "GPS_LONG")
    private @Setter @Getter String gpsLong;

    @Column(name = "ETAISYYS_RADASTA_JOS_ERI")
    private @Setter @Getter Double etaisyysRadastaJosEri;

    @Column(name = "KERROS")
    private @Setter @Getter Integer kerros;

    @Column(name = "SIJOITUSPAIKAN_LISASELITE")
    private @Setter @Getter String sijoituspaikanLisaselite;

    // MITTAUS is foreign key in the table
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "MITTAUS")
    private @Setter @Getter Mittaus mittaus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ASENNUSPAIKKA")
    private @Setter @Getter AsennuspaikanTyyppi asennuspaikanTyyppi;

    @OneToMany(mappedBy = "asennettuAnturi",
            fetch = FetchType.LAZY,
            cascade = javax.persistence.CascadeType.ALL,
            orphanRemoval = true)
    private @Setter @Getter Set<AnturikohtaisetTunnusarvot> anturikohtaisetTunnusarvotSet = new HashSet<>();

    public AsennettuAnturi(AsennuspaikanTyyppi asennuspaikanTyyppi) {
        this.asennuspaikanTyyppi = asennuspaikanTyyppi;
    }

    public void addTunnusarvotToSet(AnturikohtaisetTunnusarvot arvot) {
        this.anturikohtaisetTunnusarvotSet.add(arvot);
    }

    public AsennettuAnturi(String malli,
                           String gpsLat,
                           String gpsLong,
                           Double etaisyysRadastaJosEri,
                           Integer kerros,
                           String sijoituspaikanLisaselite) {
        this.malli = malli;
        this.gpsLat = gpsLat;
        this.gpsLong = gpsLong;
        this.etaisyysRadastaJosEri = etaisyysRadastaJosEri;
        this.kerros = kerros;
        this.sijoituspaikanLisaselite = sijoituspaikanLisaselite;
    }

}

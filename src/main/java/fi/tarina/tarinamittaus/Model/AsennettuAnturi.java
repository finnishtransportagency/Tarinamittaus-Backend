package fi.tarina.tarinamittaus.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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
    private @Setter @Getter Long etaisyysRadastaJosEri;

    @Column(name = "KERROS")
    private @Setter @Getter Integer kerros;

    @Column(name = "SIJOITUSPAIKAN_LISASELITE")
    private @Setter @Getter String sijoituspaikanLisaselite;

    // MITTAUS is foreign key in the table
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "MITTAUS", nullable = false)
    private @Setter @Getter Mittaus mittaus;

    @ManyToOne
    @JoinColumn(name = "ASENNUSPAIKKA", nullable = false)
    private @Setter @Getter AsennuspaikanTyyppi asennuspaikanTyyppi;

//    @OneToMany(mappedBy = "asennettuAnturi", fetch = FetchType.LAZY)
//    @Cascade({CascadeType.ALL})
//    private @Setter @Getter Set<AnturikohtaisetTunnusarvot> anturikohtaisetTunnusarvotSet;

    public AsennettuAnturi(String malli,
                           String gpsLat,
                           String gpsLong,
                           Long etaisyysRadastaJosEri,
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

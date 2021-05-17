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
    private Integer asennuskohtainen_id;

    @Column(name = "MALLI")
    private String malli;

    @Column(name = "GPS_LAT")
    private String gpsLat;

    @Column(name = "GPS_LONG")
    private String gpsLong;

    @Column(name = "ETAISYYS_RADASTA_JOS_ERI")
    private Double etaisyysRadastaJosEri;

    @Column(name = "KERROS")
    private Integer kerros;

    @Column(name = "SIJOITUSPAIKAN_LISASELITE")
    private String sijoituspaikanLisaselite;

    // MITTAUS is foreign key in the table
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "MITTAUS")
    private Mittaus mittaus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ASENNUSPAIKKA")
    private AsennuspaikanTyyppi asennuspaikanTyyppi;

    @OneToMany(mappedBy = "asennettuAnturi",
            fetch = FetchType.LAZY,
            cascade = javax.persistence.CascadeType.ALL,
            orphanRemoval = true)
    private Set<AnturikohtaisetTunnusarvot> anturikohtaisetTunnusarvotSet = new HashSet<>();

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getAsennuskohtainen_id() {
        return asennuskohtainen_id;
    }

    public void setAsennuskohtainen_id(Integer asennuskohtainen_id) {
        this.asennuskohtainen_id = asennuskohtainen_id;
    }

    public String getMalli() {
        return malli;
    }

    public void setMalli(String malli) {
        this.malli = malli;
    }

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    public String getGpsLong() {
        return gpsLong;
    }

    public void setGpsLong(String gpsLong) {
        this.gpsLong = gpsLong;
    }

    public Double getEtaisyysRadastaJosEri() {
        return etaisyysRadastaJosEri;
    }

    public void setEtaisyysRadastaJosEri(Double etaisyysRadastaJosEri) {
        this.etaisyysRadastaJosEri = etaisyysRadastaJosEri;
    }

    public Integer getKerros() {
        return kerros;
    }

    public void setKerros(Integer kerros) {
        this.kerros = kerros;
    }

    public String getSijoituspaikanLisaselite() {
        return sijoituspaikanLisaselite;
    }

    public void setSijoituspaikanLisaselite(String sijoituspaikanLisaselite) {
        this.sijoituspaikanLisaselite = sijoituspaikanLisaselite;
    }

    public Mittaus getMittaus() {
        return mittaus;
    }

    public void setMittaus(Mittaus mittaus) {
        this.mittaus = mittaus;
    }

    public AsennuspaikanTyyppi getAsennuspaikanTyyppi() {
        return asennuspaikanTyyppi;
    }

    public void setAsennuspaikanTyyppi(AsennuspaikanTyyppi asennuspaikanTyyppi) {
        this.asennuspaikanTyyppi = asennuspaikanTyyppi;
    }

    public Set<AnturikohtaisetTunnusarvot> getAnturikohtaisetTunnusarvotSet() {
        return anturikohtaisetTunnusarvotSet;
    }

    public void setAnturikohtaisetTunnusarvotSet(Set<AnturikohtaisetTunnusarvot> anturikohtaisetTunnusarvotSet) {
        this.anturikohtaisetTunnusarvotSet = anturikohtaisetTunnusarvotSet;
    }
}

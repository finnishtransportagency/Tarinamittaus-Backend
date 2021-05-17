package fi.tarina.tarinamittaus.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ANTURIKOHTAISETTUNNUSARVOT", schema = "TARINAM")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnturikohtaisetTunnusarvot implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @Column(name = "TUNNUSARVO_ID", nullable = false, unique = true)
    @SequenceGenerator(
            name = "seq_gen",
            sequenceName = "tunnusarvo_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_gen"
    )
    private Integer tunnusarvo_id;

    @Column(name = "MITTAUSSUUNTA_XYZ")
    @ApiModelProperty(value = "value to show", example = "X")
    private char mittaussuunta_xyz;

    @Column(name = "TARINAN_MAKSIMIARVO")
    private Double tarinan_maksimiarvo;

    @Column(name = "HALLITSEVA_TAAJUUS")
    private Double hallitseva_taajuus;

    @Column(name = "TARINAN_TUNNUSLUKU_VW95_RMS")
    private Double tarinan_tunnusluku_vw95_rms;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "ASENNETTUANTURI")
    private AsennettuAnturi asennettuAnturi;

    public AnturikohtaisetTunnusarvot(AsennettuAnturi anturi) {
        this.asennettuAnturi = anturi;
    }

    public AnturikohtaisetTunnusarvot(
            char mittaussuunta_xyz,
            Double tarinan_maksimiarvo,
            Double hallitseva_taajuus,
            Double tarinan_tunnusluku_vw95_rms
                                     ) {
        this.mittaussuunta_xyz = mittaussuunta_xyz;
        this.tarinan_maksimiarvo = tarinan_maksimiarvo;
        this.hallitseva_taajuus = hallitseva_taajuus;
        this.tarinan_tunnusluku_vw95_rms = tarinan_tunnusluku_vw95_rms;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getTunnusarvo_id() {
        return tunnusarvo_id;
    }

    public void setTunnusarvo_id(Integer tunnusarvo_id) {
        this.tunnusarvo_id = tunnusarvo_id;
    }

    public char getMittaussuunta_xyz() {
        return mittaussuunta_xyz;
    }

    public void setMittaussuunta_xyz(char mittaussuunta_xyz) {
        this.mittaussuunta_xyz = mittaussuunta_xyz;
    }

    public Double getTarinan_maksimiarvo() {
        return tarinan_maksimiarvo;
    }

    public void setTarinan_maksimiarvo(Double tarinan_maksimiarvo) {
        this.tarinan_maksimiarvo = tarinan_maksimiarvo;
    }

    public Double getHallitseva_taajuus() {
        return hallitseva_taajuus;
    }

    public void setHallitseva_taajuus(Double hallitseva_taajuus) {
        this.hallitseva_taajuus = hallitseva_taajuus;
    }

    public Double getTarinan_tunnusluku_vw95_rms() {
        return tarinan_tunnusluku_vw95_rms;
    }

    public void setTarinan_tunnusluku_vw95_rms(Double tarinan_tunnusluku_vw95_rms) {
        this.tarinan_tunnusluku_vw95_rms = tarinan_tunnusluku_vw95_rms;
    }

    public AsennettuAnturi getAsennettuAnturi() {
        return asennettuAnturi;
    }

    public void setAsennettuAnturi(AsennettuAnturi asennettuAnturi) {
        this.asennettuAnturi = asennettuAnturi;
    }
}

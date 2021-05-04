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
    private @Getter @Setter Integer tunnusarvo_id;

    @Column(name = "MITTAUSSUUNTA_XYZ")
    @ApiModelProperty(value = "value to show", example = "X")
    private @Getter @Setter char mittaussuunta_xyz;

    @Column(name = "TARINAN_MAKSIMIARVO")
    private @Getter @Setter Double tarinan_maksimiarvo;

    @Column(name = "HALLITSEVA_TAAJUUS")
    private @Getter @Setter Double hallitseva_taajuus;

    @Column(name = "TARINAN_TUNNUSLUKU_VW95_RMS")
    private @Getter @Setter Double tarinan_tunnusluku_vw95_rms;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "ASENNETTUANTURI")
    private @Getter @Setter AsennettuAnturi asennettuAnturi;

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

}

package fi.tarina.tarinamittaus.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.sql.Timestamp;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "MITTAUS", schema = "TARINAM")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Mittaus implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @Column(name = "KOHDE_ID", unique = true, nullable = false)
    @SequenceGenerator(
            name = "seq_gen",
            sequenceName = "mittaus_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_gen"
    )
    private @Getter @Setter Integer kohde_id;

    @Column(name = "ALKUAIKA")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="Europe/Helsinki")
    @ApiModelProperty(value = "value to show", example = "2021-04-27T07:44:20.000Z")
    private @Getter @Setter Timestamp alkuaika;

    @Column(name = "LOPPUAIKA")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="Europe/Helsinki")
    @ApiModelProperty(value = "value to show", example = "2021-04-27T07:44:20.000Z")
    private @Getter @Setter Timestamp loppuaika;

    @Column(name = "MITTAUS_ASIANHALLINTA_ID")
    private @Getter @Setter String mittaus_asianhallinta_id;

    @Column(name = "PDF_RAPORTIN_LINKKI")
    private @Getter @Setter String pdf_raportin_linkki;

    @Column(name = "RAKENNUKSEN_PINTA_ALA")
    private @Getter @Setter Double rakennuksen_pinta_ala;

    @Column(name = "PERUSTAMISTAPA")
    private @Getter @Setter String perustamistapa;

    @Column(name = "JULKISIVUMATERIAALI")
    private @Getter @Setter String julkisivumateriaali;

    @Column(name = "RUNKOMATERIAALI")
    private @Getter @Setter String runkomateriaali;

    @Column(name = "RAKENNUSVUOSI")
    private @Getter @Setter Integer rakennusvuosi;

    @Column(name = "KATUOSOITE")
    private @Getter @Setter String katuosoite;

    @Column(name = "POSTINUMERO")
    private @Getter @Setter String postinumero;

    @Column(name = "CREATED_BY_LX")
    private @Getter @Setter String created_by_lx;

    //mittaus refers to attribute in AsennettuAnturi class
    @OneToMany(mappedBy = "mittaus",
            fetch = FetchType.LAZY,
            cascade = javax.persistence.CascadeType.ALL,
            orphanRemoval = true)
    private @Getter @Setter Set<AsennettuAnturi> asennettuAnturiSet = new HashSet<>();

    public void addAsennettuAnturi(AsennettuAnturi anturi) {
        this.asennettuAnturiSet.add(anturi);
    }

    public Mittaus(Timestamp alkuaika,
                   Timestamp loppuaika,
                   String mittaus_asianhallinta_id,
                   String pdf_raportin_linkki,
                   Double rakennuksen_pinta_ala,
                   String perustamistapa,
                   String julkisivumateriaali,
                   String runkomateriaali,
                   Integer rakennusvuosi,
                   String katuosoite,
                   String postinumero,
                   String created_by_lx) {
        this.alkuaika = alkuaika;
        this.loppuaika = loppuaika;
        this.mittaus_asianhallinta_id = mittaus_asianhallinta_id;
        this.pdf_raportin_linkki = pdf_raportin_linkki;
        this.rakennuksen_pinta_ala = rakennuksen_pinta_ala;
        this.perustamistapa = perustamistapa;
        this.julkisivumateriaali = julkisivumateriaali;
        this.runkomateriaali = runkomateriaali;
        this.rakennusvuosi = rakennusvuosi;
        this.katuosoite = katuosoite;
        this.postinumero = postinumero;
        this.created_by_lx = created_by_lx;
    }
}

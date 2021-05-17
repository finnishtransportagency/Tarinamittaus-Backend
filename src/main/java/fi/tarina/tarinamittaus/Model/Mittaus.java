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
    private Integer kohde_id;

    @Column(name = "ALKUAIKA")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Europe/Helsinki")
    @ApiModelProperty(value = "value to show", example = "2021-04-27")
    private Timestamp alkuaika;

    @Column(name = "LOPPUAIKA")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Europe/Helsinki")
    @ApiModelProperty(value = "value to show", example = "2021-04-27")
    private Timestamp loppuaika;

    @Column(name = "MITTAUS_ASIANHALLINTA_ID")
    private String mittaus_asianhallinta_id;

    @Column(name = "PDF_RAPORTIN_LINKKI")
    private String pdf_raportin_linkki;

    @Column(name = "RAKENNUKSEN_PINTA_ALA")
    private Double rakennuksen_pinta_ala;

    @Column(name = "PERUSTAMISTAPA")
    private String perustamistapa;

    @Column(name = "JULKISIVUMATERIAALI")
    private String julkisivumateriaali;

    @Column(name = "RUNKOMATERIAALI")
    private String runkomateriaali;

    @Column(name = "RAKENNUSVUOSI")
    private Integer rakennusvuosi;

    @Column(name = "KATUOSOITE")
    private String katuosoite;

    @Column(name = "POSTINUMERO")
    private String postinumero;

    @Column(name = "CREATED_BY_LX")
    private String created_by_lx;

    //mittaus refers to attribute in AsennettuAnturi class
    @OneToMany(mappedBy = "mittaus",
            fetch = FetchType.LAZY,
            cascade = javax.persistence.CascadeType.ALL,
            orphanRemoval = true)
    private Set<AsennettuAnturi> asennettuAnturiSet = new HashSet<>();

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getKohde_id() {
        return kohde_id;
    }

    public void setKohde_id(Integer kohde_id) {
        this.kohde_id = kohde_id;
    }

    public Timestamp getAlkuaika() {
        return alkuaika;
    }

    public void setAlkuaika(Timestamp alkuaika) {
        this.alkuaika = alkuaika;
    }

    public Timestamp getLoppuaika() {
        return loppuaika;
    }

    public void setLoppuaika(Timestamp loppuaika) {
        this.loppuaika = loppuaika;
    }

    public String getMittaus_asianhallinta_id() {
        return mittaus_asianhallinta_id;
    }

    public void setMittaus_asianhallinta_id(String mittaus_asianhallinta_id) {
        this.mittaus_asianhallinta_id = mittaus_asianhallinta_id;
    }

    public String getPdf_raportin_linkki() {
        return pdf_raportin_linkki;
    }

    public void setPdf_raportin_linkki(String pdf_raportin_linkki) {
        this.pdf_raportin_linkki = pdf_raportin_linkki;
    }

    public Double getRakennuksen_pinta_ala() {
        return rakennuksen_pinta_ala;
    }

    public void setRakennuksen_pinta_ala(Double rakennuksen_pinta_ala) {
        this.rakennuksen_pinta_ala = rakennuksen_pinta_ala;
    }

    public String getPerustamistapa() {
        return perustamistapa;
    }

    public void setPerustamistapa(String perustamistapa) {
        this.perustamistapa = perustamistapa;
    }

    public String getJulkisivumateriaali() {
        return julkisivumateriaali;
    }

    public void setJulkisivumateriaali(String julkisivumateriaali) {
        this.julkisivumateriaali = julkisivumateriaali;
    }

    public String getRunkomateriaali() {
        return runkomateriaali;
    }

    public void setRunkomateriaali(String runkomateriaali) {
        this.runkomateriaali = runkomateriaali;
    }

    public Integer getRakennusvuosi() {
        return rakennusvuosi;
    }

    public void setRakennusvuosi(Integer rakennusvuosi) {
        this.rakennusvuosi = rakennusvuosi;
    }

    public String getKatuosoite() {
        return katuosoite;
    }

    public void setKatuosoite(String katuosoite) {
        this.katuosoite = katuosoite;
    }

    public String getPostinumero() {
        return postinumero;
    }

    public void setPostinumero(String postinumero) {
        this.postinumero = postinumero;
    }

    public String getCreated_by_lx() {
        return created_by_lx;
    }

    public void setCreated_by_lx(String created_by_lx) {
        this.created_by_lx = created_by_lx;
    }

    public Set<AsennettuAnturi> getAsennettuAnturiSet() {
        return asennettuAnturiSet;
    }

    public void setAsennettuAnturiSet(Set<AsennettuAnturi> asennettuAnturiSet) {
        this.asennettuAnturiSet = asennettuAnturiSet;
    }
}

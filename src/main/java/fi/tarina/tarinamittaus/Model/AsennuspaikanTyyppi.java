package fi.tarina.tarinamittaus.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.tarina.tarinamittaus.validator.Selite;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ASENNUSPAIKANTYYPPI", schema = "TARINAM")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AsennuspaikanTyyppi implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @Column(name = "PAIKKATYYPPI_ID", unique = true, nullable = false)
    @SequenceGenerator(
            name = "seq_gen",
            sequenceName = "paikka_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_gen"
    )
    private Integer paikkatyyppi_id;

    @Column(name = "SELITE")
    @ApiModelProperty(value = "maa", example = "maa")
    @Selite
    private String selite;

    @Column(name = "LISATIEDOT" )
    private String lisatiedot;

    @JsonIgnore
    @OneToMany(mappedBy = "asennuspaikanTyyppi",
            fetch = FetchType.LAZY,
            cascade = javax.persistence.CascadeType.ALL)
    private Set<AsennettuAnturi> asennettuAnturiSet = new HashSet<>();

    public void addAsennettuAnturi(AsennettuAnturi anturi) {
        this.asennettuAnturiSet.add(anturi);
    }

    public AsennuspaikanTyyppi(String selite, String lisatiedot) {
        this.selite = selite;
        this.lisatiedot = lisatiedot;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPaikkatyyppi_id() {
        return paikkatyyppi_id;
    }

    public void setPaikkatyyppi_id(Integer paikkatyyppi_id) {
        this.paikkatyyppi_id = paikkatyyppi_id;
    }

    public String getSelite() {
        return selite;
    }

    public void setSelite(String selite) {
        this.selite = selite;
    }

    public String getLisatiedot() {
        return lisatiedot;
    }

    public void setLisatiedot(String lisatiedot) {
        this.lisatiedot = lisatiedot;
    }

    public Set<AsennettuAnturi> getAsennettuAnturiSet() {
        return asennettuAnturiSet;
    }

    public void setAsennettuAnturiSet(Set<AsennettuAnturi> asennettuAnturiSet) {
        this.asennettuAnturiSet = asennettuAnturiSet;
    }
}

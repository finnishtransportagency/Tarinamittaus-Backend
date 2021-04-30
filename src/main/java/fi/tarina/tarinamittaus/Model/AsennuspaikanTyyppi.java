package fi.tarina.tarinamittaus.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private @Setter @Getter Integer paikkatyyppi_id;

    @Column(name = "SELITE")
    @ApiModelProperty(value = "maa", example = "maa")
    private @Setter @Getter String selite;

    @Column(name = "LISATIEDOT" )
    private @Setter @Getter String lisatiedot;

    @JsonIgnore
    @OneToMany(mappedBy = "asennuspaikanTyyppi",
            fetch = FetchType.LAZY,
            cascade = javax.persistence.CascadeType.ALL)
    private @Setter @Getter Set<AsennettuAnturi> asennettuAnturiSet = new HashSet<>();

    public void addAsennettuAnturi(AsennettuAnturi anturi) {
        this.asennettuAnturiSet.add(anturi);
    }

    public AsennuspaikanTyyppi(String selite, String lisatiedot) {
        this.selite = selite;
        this.lisatiedot = lisatiedot;
    }
}

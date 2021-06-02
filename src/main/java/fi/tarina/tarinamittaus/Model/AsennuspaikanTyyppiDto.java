package fi.tarina.tarinamittaus.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = {"paikkatyyppi_id", "asennettuAnturiSet"})
public class AsennuspaikanTyyppiDto implements Serializable {

    private Integer paikkatyyppi_id;
    private String selite;
    private String lisatiedot;
    private AsennettuAnturiDto asennettuAnturiDto;

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

    public AsennettuAnturiDto getAsennettuAnturiDto() {
        return asennettuAnturiDto;
    }

    public void setAsennettuAnturiDto(AsennettuAnturiDto asennettuAnturiDto) {
        this.asennettuAnturiDto = asennettuAnturiDto;
    }
}

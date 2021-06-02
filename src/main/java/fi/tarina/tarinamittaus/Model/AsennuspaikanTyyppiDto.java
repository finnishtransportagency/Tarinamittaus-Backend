package fi.tarina.tarinamittaus.Model;



public class AsennuspaikanTyyppiDto {

    private Integer paikkatyyppi_id;
    private String selite;
    private String lisatiedot;
    private AsennettuAnturi asennettuAnturiSet;

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

    public AsennettuAnturi getAsennettuAnturiSet() {
        return asennettuAnturiSet;
    }

    public void setAsennettuAnturiSet(AsennettuAnturi asennettuAnturiSet) {
        this.asennettuAnturiSet = asennettuAnturiSet;
    }
}

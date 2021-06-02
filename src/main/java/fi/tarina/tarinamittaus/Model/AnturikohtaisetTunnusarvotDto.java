package fi.tarina.tarinamittaus.Model;



public class AnturikohtaisetTunnusarvotDto {

    private Integer tunnusarvo_id;
    private char mittaussuunta_xyz;
    private Double tarinan_maksimiarvo;
    private Double hallitseva_taajuus;
    private Double tarinan_tunnusluku_vw95_rms;
    private AsennettuAnturi asennettuAnturi;

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

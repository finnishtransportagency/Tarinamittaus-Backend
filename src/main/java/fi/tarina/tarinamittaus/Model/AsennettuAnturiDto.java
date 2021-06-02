package fi.tarina.tarinamittaus.Model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AsennettuAnturiDto {

    private Integer asennuskohtainen_id;
    private String malli;
    private Double gpsLat;
    private Double gpsLong;
    private Double etaisyysRadastaJosEri;
    private Integer kerros;
    private String sijoituspaikanLisaselite;
    private Mittaus mittaus;
    private AsennuspaikanTyyppi asennuspaikanTyyppi;
    private List<AnturikohtaisetTunnusarvot> anturikohtaisetTunnusarvotSet = new ArrayList<>();

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

    public Double getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(Double gpsLat) {
        this.gpsLat = gpsLat;
    }

    public Double getGpsLong() {
        return gpsLong;
    }

    public void setGpsLong(Double gpsLong) {
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

    public List<AnturikohtaisetTunnusarvot> getAnturikohtaisetTunnusarvotSet() {
        return anturikohtaisetTunnusarvotSet;
    }

    public void setAnturikohtaisetTunnusarvotSet(List<AnturikohtaisetTunnusarvot> anturikohtaisetTunnusarvotSet) {
        this.anturikohtaisetTunnusarvotSet = anturikohtaisetTunnusarvotSet;
    }
}

package fi.tarina.tarinamittaus.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"mittausDto"})
public class AsennettuAnturiDto {

    private Integer asennuskohtainen_id;
    private String malli;
    private Double gpsLat;
    private Double gpsLong;
    private Double etaisyysRadastaJosEri;
    private Integer kerros;
    private String sijoituspaikanLisaselite;
    private MittausDto mittausDto;
    private AsennuspaikanTyyppiDto asennuspaikanTyyppiDto;
    private List<AnturikohtaisetTunnusarvotDto> anturikohtaisetTunnusarvotDtos = new ArrayList<>();

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

    public MittausDto getMittausDto() {
        return mittausDto;
    }

    public void setMittausDto(MittausDto mittausDto) {
        this.mittausDto = mittausDto;
    }

    public AsennuspaikanTyyppiDto getAsennuspaikanTyyppiDto() {
        return asennuspaikanTyyppiDto;
    }

    public void setAsennuspaikanTyyppiDto(AsennuspaikanTyyppiDto asennuspaikanTyyppiDto) {
        this.asennuspaikanTyyppiDto = asennuspaikanTyyppiDto;
    }

    public List<AnturikohtaisetTunnusarvotDto> getAnturikohtaisetTunnusarvotDtos() {
        return anturikohtaisetTunnusarvotDtos;
    }

    public void setAnturikohtaisetTunnusarvotDtos(List<AnturikohtaisetTunnusarvotDto> anturikohtaisetTunnusarvotDtos) {
        this.anturikohtaisetTunnusarvotDtos = anturikohtaisetTunnusarvotDtos;
    }

    public AsennettuAnturiDto() {
    }

    public AsennettuAnturiDto(Integer asennuskohtainen_id, String malli, Double gpsLat, Double gpsLong, Double etaisyysRadastaJosEri, Integer kerros, String sijoituspaikanLisaselite, MittausDto mittausDto, AsennuspaikanTyyppiDto asennuspaikanTyyppiDto, List<AnturikohtaisetTunnusarvotDto> anturikohtaisetTunnusarvotDtos) {
        this.asennuskohtainen_id = asennuskohtainen_id;
        this.malli = malli;
        this.gpsLat = gpsLat;
        this.gpsLong = gpsLong;
        this.etaisyysRadastaJosEri = etaisyysRadastaJosEri;
        this.kerros = kerros;
        this.sijoituspaikanLisaselite = sijoituspaikanLisaselite;
        this.mittausDto = mittausDto;
        this.asennuspaikanTyyppiDto = asennuspaikanTyyppiDto;
        this.anturikohtaisetTunnusarvotDtos = anturikohtaisetTunnusarvotDtos;
    }

    @Override
    public String toString() {
        return "AsennettuAnturiDto{" +
                "asennuskohtainen_id=" + asennuskohtainen_id +
                ", malli='" + malli + '\'' +
                ", gpsLat=" + gpsLat +
                ", gpsLong=" + gpsLong +
                ", etaisyysRadastaJosEri=" + etaisyysRadastaJosEri +
                ", kerros=" + kerros +
                ", sijoituspaikanLisaselite='" + sijoituspaikanLisaselite + '\'' +
                ", mittausDto=" + mittausDto +
                ", asennuspaikanTyyppiDto=" + asennuspaikanTyyppiDto +
                ", anturikohtaisetTunnusarvotDtos=" + anturikohtaisetTunnusarvotDtos +
                '}';
    }
}

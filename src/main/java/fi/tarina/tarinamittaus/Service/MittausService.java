package fi.tarina.tarinamittaus.Service;

import fi.tarina.tarinamittaus.Model.AnturikohtaisetTunnusarvot;
import fi.tarina.tarinamittaus.Model.AsennettuAnturi;
import fi.tarina.tarinamittaus.Model.AsennuspaikanTyyppi;
import fi.tarina.tarinamittaus.Model.Mittaus;
import fi.tarina.tarinamittaus.Repository.AnturiRepository;
import fi.tarina.tarinamittaus.Repository.AnturikohtaisetTunnusarvotRepository;
import fi.tarina.tarinamittaus.Repository.MittausRepository;
import fi.tarina.tarinamittaus.Repository.AsennuspaikanTyyppiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class MittausService {

    private final Logger LOG = LoggerFactory.getLogger(MittausService.class);

    private final MittausRepository mittausRepository;
    private final AsennuspaikanTyyppiRepository asennuspaikanTyyppiRepository;
    private final AnturiRepository anturiRepository;
    private final AnturikohtaisetTunnusarvotRepository tunnusarvotRepository;

    @Autowired
    public MittausService(MittausRepository mittausRepository,
                          AsennuspaikanTyyppiRepository asennuspaikanTyyppiRepository,
                          AnturiRepository anturiRepository,
                          AnturikohtaisetTunnusarvotRepository tunnusarvotRepository) {
        this.mittausRepository = mittausRepository;
        this.asennuspaikanTyyppiRepository = asennuspaikanTyyppiRepository;
        this.anturiRepository = anturiRepository;
        this.tunnusarvotRepository = tunnusarvotRepository;
    }

    //TODO: This should be refactored someway to make it prettier :)
    @Transactional
    public Mittaus saveMittaus(Mittaus mittausRequest) {
        Mittaus mittaus = new Mittaus(
                mittausRequest.getAlkuaika(),
                mittausRequest.getLoppuaika(),
                mittausRequest.getMittaus_asianhallinta_id(),
                mittausRequest.getPdf_raportin_linkki(),
                mittausRequest.getRakennuksen_pinta_ala(),
                mittausRequest.getPerustamistapa(),
                mittausRequest.getJulkisivumateriaali(),
                mittausRequest.getRunkomateriaali(),
                mittausRequest.getRakennusvuosi(),
                mittausRequest.getKatuosoite(),
                mittausRequest.getPostinumero(),
                mittausRequest.getCreated_by_lx()
        );

        Mittaus savedMittaus = this.mittausRepository.save(mittaus);


        for (AsennettuAnturi asennettuAnturiRequest : mittausRequest.getAsennettuAnturiSet()) {
            AsennettuAnturi asennettuAnturi = new AsennettuAnturi(
                    asennettuAnturiRequest.getMalli(),
                    asennettuAnturiRequest.getGpsLat(),
                    asennettuAnturiRequest.getGpsLong(),
                    asennettuAnturiRequest.getEtaisyysRadastaJosEri(),
                    asennettuAnturiRequest.getKerros(),
                    asennettuAnturiRequest.getSijoituspaikanLisaselite()
            );

            AsennuspaikanTyyppi asennuspaikanTyyppi = asennettuAnturiRequest.getAsennuspaikanTyyppi();
            AsennuspaikanTyyppi savedAsennuspaikanTyyppi =
                    this.asennuspaikanTyyppiRepository.save(asennuspaikanTyyppi);

            LOG.info("savedAsennuspaikanTyyppi " + savedAsennuspaikanTyyppi);

            asennettuAnturi.setAsennuspaikanTyyppi(savedAsennuspaikanTyyppi);
            asennettuAnturi.setMittaus(mittaus);
            AsennettuAnturi savedAnturi = this.anturiRepository.save(asennettuAnturi);

            for (AnturikohtaisetTunnusarvot tunnusarvotRequest :
                    asennettuAnturiRequest.getAnturikohtaisetTunnusarvotSet()) {

                AnturikohtaisetTunnusarvot anturikohtaisetTunnusarvot = new AnturikohtaisetTunnusarvot(
                        tunnusarvotRequest.getMittaussuunta_xyz(),
                        tunnusarvotRequest.getTarinan_maksimiarvo(),
                        tunnusarvotRequest.getHallitseva_taajuus(),
                        tunnusarvotRequest.getTarinan_tunnusluku_vw95_rms()
                );
                anturikohtaisetTunnusarvot.setAsennettuAnturi(savedAnturi);
                this.tunnusarvotRepository.save(anturikohtaisetTunnusarvot);
            }

            LOG.info("asennettuAnturi " + asennettuAnturi);
        }

        return savedMittaus;


    }

    public List<Mittaus> getMittausList() {
        return mittausRepository.findAll();
    }

    public Mittaus getMittaus(Integer id) {
        Optional<Mittaus> mittausOptional = mittausRepository.findById(id);
        if (mittausOptional.isPresent()) {
            return mittausOptional.get();
        }
        throw new IllegalStateException("Mittaus with id " + id + " doesn't exist");
    }

    public void deleteMittaus(Integer id) {
        boolean exists = mittausRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Mittaus with id " + id + " doesn't exist");
        } else {
            mittausRepository.deleteById(id);
        }
    }

}

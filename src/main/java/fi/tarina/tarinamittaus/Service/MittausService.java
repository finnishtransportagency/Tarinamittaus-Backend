package fi.tarina.tarinamittaus.Service;

import fi.tarina.tarinamittaus.Model.AsennettuAnturi;
import fi.tarina.tarinamittaus.Model.AsennuspaikanTyyppi;
import fi.tarina.tarinamittaus.Model.Mittaus;
import fi.tarina.tarinamittaus.Repository.AnturiRepository;
import fi.tarina.tarinamittaus.Repository.MittausRepository;
import fi.tarina.tarinamittaus.Repository.AsennuspaikanTyyppiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class MittausService {

    private final Logger LOG = LoggerFactory.getLogger(MittausService.class);

    private final MittausRepository mittausRepository;
    private final AsennuspaikanTyyppiRepository asennuspaikanTyyppiRepository;
    private final AnturiRepository anturiRepository;

    @Autowired
    public MittausService(MittausRepository mittausRepository,
                          AsennuspaikanTyyppiRepository asennuspaikanTyyppiRepository,
                          AnturiRepository anturiRepository) {
        this.mittausRepository = mittausRepository;
        this.asennuspaikanTyyppiRepository = asennuspaikanTyyppiRepository;
        this.anturiRepository = anturiRepository;
    }

    public String getForms() {
        return "ff";
    }

    public List<Mittaus> getMittausList() {
        return mittausRepository.findAll();
    }

    //TODO Refactor object creation?
    public void addNewMittaus(Mittaus mittaus) {
        Mittaus m1 = new Mittaus(
                    mittaus.getAlkuaika(),
                    mittaus.getLoppuaika(),
                    mittaus.getMittaus_asianhallinta_id(),
                    mittaus.getPdf_raportin_linkki(),
                    mittaus.getRakennuksen_pinta_ala(),
                    mittaus.getPerustamistapa(),
                    mittaus.getJulkisivumateriaali(),
                    mittaus.getRunkomateriaali(),
                    mittaus.getRakennusvuosi(),
                    mittaus.getKatuosoite(),
                    mittaus.getPostinumero(),
                    mittaus.getCreated_by_lx()
        );

        LOG.info("m1 " + m1);

        Set<AsennettuAnturi> anturiSet = new HashSet<>();

        for (AsennettuAnturi anturi : mittaus.getAsennettuAnturiSet()) {
            AsennuspaikanTyyppi newPaikka = anturi.getAsennuspaikanTyyppi();
            AsennettuAnturi newAnturi = new AsennettuAnturi(
                    anturi.getMalli(),
                    anturi.getGpsLat(),
                    anturi.getGpsLong(),
                    anturi.getEtaisyysRadastaJosEri(),
                    anturi.getKerros(),
                    anturi.getSijoituspaikanLisaselite()
            );
            newAnturi.setAsennuspaikanTyyppi(newPaikka);
            newAnturi.setMittaus(m1);
            anturiSet.add(newAnturi);
        }
        anturiSet.stream().map(it -> it).forEach(System.out::println);
        this.mittausRepository.save(m1);

        for (AsennettuAnturi anturi : anturiSet) {

            this.asennuspaikanTyyppiRepository.save(anturi.getAsennuspaikanTyyppi());
            LOG.info("Saved AsennuspaikanTyyppi");
            this.anturiRepository.save(anturi);
            LOG.info("Saved AsennettuAnturi");
        }

    }
}

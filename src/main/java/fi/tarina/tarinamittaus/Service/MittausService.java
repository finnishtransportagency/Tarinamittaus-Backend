package fi.tarina.tarinamittaus.Service;

import fi.tarina.tarinamittaus.Model.AsennettuAnturi;
import fi.tarina.tarinamittaus.Model.Mittaus;
import fi.tarina.tarinamittaus.Repository.AnturiRepository;
import fi.tarina.tarinamittaus.Repository.MittausRepository;
import fi.tarina.tarinamittaus.Repository.AsennuspaikanTyyppiRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class MittausService {

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
        Mittaus m1 = new Mittaus();
        BeanUtils.copyProperties(mittaus, m1);

//        Mittaus measurement = new Mittaus(
//                mittaus.getAlkuaika(),
//                mittaus.getLoppuaika(),
//                mittaus.getMittaus_asianhallinta_id(),
//                mittaus.getPdf_raportin_linkki(),
//                mittaus.getRakennuksen_pinta_ala(),
//                mittaus.getPerustamistapa(),
//                mittaus.getJulkisivumateriaali(),
//                mittaus.getRunkomateriaali(),
//                mittaus.getRakennusvuosi(),
//                mittaus.getKatuosoite(),
//                mittaus.getPostinumero(),
//                mittaus.getCreated_by_lx()
//        );
        Set<AsennettuAnturi> anturiSet = mittaus.getAsennettuAnturiSet();
        for(AsennettuAnturi a : anturiSet)  {
            AsennettuAnturi newAnturi = new AsennettuAnturi();
            BeanUtils.copyProperties(a, newAnturi);
            a.setMittaus(m1);
        }
//        Set<AsennuspaikanTyyppi> asennuspaikanTyyppiSet =
//        Mittaus m = mittausRepository.save(measurement);
//        System.out.println(m);
    }
}

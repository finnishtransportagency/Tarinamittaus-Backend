package fi.tarina.tarinamittaus.Configs;

//import fi.tarina.tarinamittaus.Model.AsennettuAnturi;
//import fi.tarina.tarinamittaus.Model.AsennuspaikanTyyppi;
import fi.tarina.tarinamittaus.Model.AsennettuAnturi;
import fi.tarina.tarinamittaus.Model.AsennuspaikanTyyppi;
import fi.tarina.tarinamittaus.Model.Mittaus;
import fi.tarina.tarinamittaus.Repository.AnturiRepository;
import fi.tarina.tarinamittaus.Repository.MittausRepository;
import fi.tarina.tarinamittaus.Repository.AsennuspaikanTyyppiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.LocalDate;


@Configuration
public class MittausConfig {

    private final Logger LOG = LoggerFactory.getLogger(MittausConfig.class);

    @Bean
    CommandLineRunner commandLineRunner(AsennuspaikanTyyppiRepository asennuspaikanTyyppiRepository,
                                        MittausRepository mittausRepository,
                                        AnturiRepository anturiRepository) {
        return args -> {
            AsennuspaikanTyyppi paikka = new AsennuspaikanTyyppi(
                    "maa",
                    "lisätiedot"
            );
            Mittaus m1 = new Mittaus(
                    Timestamp.valueOf(LocalDate.now().atStartOfDay()),
                    Timestamp.valueOf(LocalDate.now().atStartOfDay()),
                    "MITTAUS_ASIANHALLINTA_ID",
                    "PDF_RAPORTIN_LINKKI",
                    50.0,
                    "PERUSTAMISTAPA",
                    "JULKISIVUMATERIAALI",
                    "RUNKOMATERIAALI",
                    Integer.valueOf(1976),
                    "KATUOSOITE",
                    "00750",
                    "CREATED_BY_LX"
            );
            AsennettuAnturi as1 = new AsennettuAnturi(
                    "malli1",
                    "lat",
                    "long",
                    3L,
                    2,
                    "selite"
            );
            as1.setAsennuspaikanTyyppi(paikka);
            as1.setMittaus(m1);

            LOG.info("paikka " + paikka);
            LOG.info("anturi configissa" + as1);
            LOG.info("mittaus configissa" + m1);

            mittausRepository.save(m1);
            asennuspaikanTyyppiRepository.save(paikka);

            anturiRepository.save(as1);

            paikka.addAsennettuAnturi(as1);
            m1.addAsennettuAnturi(as1);


        };
    }
}

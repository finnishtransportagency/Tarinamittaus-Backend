package fi.tarina.tarinamittaus.Repository;


import fi.tarina.tarinamittaus.Model.AsennuspaikanTyyppi;
import fi.tarina.tarinamittaus.Model.Mittaus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MittausRepository extends JpaRepository<Mittaus, Integer> {

}


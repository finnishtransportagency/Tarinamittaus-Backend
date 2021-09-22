package fi.tarina.tarinamittaus.Util;

import fi.tarina.tarinamittaus.Model.AsennuspaikanTyyppi;
import fi.tarina.tarinamittaus.Model.AsennuspaikanTyyppiDto;
import fi.tarina.tarinamittaus.Repository.AsennuspaikanTyyppiRepository;
import org.mapstruct.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AsennuspaikanTyyppiFactory {
    @Autowired
    protected final AsennuspaikanTyyppiRepository repository;

    public AsennuspaikanTyyppiFactory(AsennuspaikanTyyppiRepository repository) {this.repository = repository;}

    @ObjectFactory
    public AsennuspaikanTyyppi create(AsennuspaikanTyyppiDto dto) {
        if (dto.getPaikkatyyppi_id() == null) return new AsennuspaikanTyyppi();
        /*
        Optional<AsennuspaikanTyyppi> asennuspaikanTyyppi = repository.findById(dto.getPaikkatyyppi_id());
        return asennuspaikanTyyppi.orElseGet(AsennuspaikanTyyppi::new);
        */
        AsennuspaikanTyyppi asennuspaikanTyyppi = repository.findOne(dto.getPaikkatyyppi_id());
        return asennuspaikanTyyppi;
    }
}

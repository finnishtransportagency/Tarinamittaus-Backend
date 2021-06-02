package fi.tarina.tarinamittaus.Util;

import fi.tarina.tarinamittaus.Model.AsennettuAnturi;
import fi.tarina.tarinamittaus.Model.AsennettuAnturiDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {AsennettuAnturiFactory.class})
public interface AsennettuAnturiMapper {

    AsennettuAnturiDto asennettuAnturiToDto(AsennettuAnturi anturi);

    AsennettuAnturi dtoToAsennettuAnturi(AsennettuAnturiDto dto);
}

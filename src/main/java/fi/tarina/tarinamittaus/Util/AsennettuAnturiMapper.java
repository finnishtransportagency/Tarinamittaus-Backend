package fi.tarina.tarinamittaus.Util;

import fi.tarina.tarinamittaus.Model.AsennettuAnturi;
import fi.tarina.tarinamittaus.Model.AsennettuAnturiDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {AsennettuAnturiFactory.class,
        AsennuspaikanTyyppiMapper.class,
        AnturikohtaisetTunnusarvotMapper.class})
public interface AsennettuAnturiMapper {

    @Mapping(source = "asennuspaikanTyyppi", target = "asennuspaikanTyyppiDto")
    @Mapping(source = "anturikohtaisetTunnusarvotSet", target = "anturikohtaisetTunnusarvotDtos")
    AsennettuAnturiDto asennettuAnturiToDto(AsennettuAnturi anturi);

    @Mapping(source = "asennuspaikanTyyppiDto", target = "asennuspaikanTyyppi")
    @Mapping(source = "anturikohtaisetTunnusarvotDtos", target = "anturikohtaisetTunnusarvotSet")
    AsennettuAnturi dtoToAsennettuAnturi(AsennettuAnturiDto dto);
}

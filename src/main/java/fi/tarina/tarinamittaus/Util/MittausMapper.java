package fi.tarina.tarinamittaus.Util;

import fi.tarina.tarinamittaus.Model.AsennettuAnturi;
import fi.tarina.tarinamittaus.Model.AsennettuAnturiDto;
import fi.tarina.tarinamittaus.Model.Mittaus;
import fi.tarina.tarinamittaus.Model.MittausDto;
import fi.tarina.tarinamittaus.Repository.AnturiRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {AsennettuAnturiMapper.class})
public interface MittausMapper {

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(source = "asennettuAnturiDtos", target = "asennettuAnturiSet")
    void updateMittausFromDto(MittausDto dto, @MappingTarget Mittaus mittaus);
    void updateDtoFromMittaus(Mittaus mittaus, @MappingTarget MittausDto mittausDto);
//    @Mapping(source = "mittausDto", target = "mittaus")
//    void updateAsennettuAnturiFromDto(AsennettuAnturiDto dto, @MappingTarget AsennettuAnturi entity);
//    MittausDto updateMittausFromDto(Mittaus mittaus);
}

package fi.tarina.tarinamittaus.Util;

import fi.tarina.tarinamittaus.Model.Mittaus;
import fi.tarina.tarinamittaus.Model.MittausDto;
import org.mapstruct.*;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MittausMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMittausFromDto(MittausDto dto, @MappingTarget Mittaus entity);
}

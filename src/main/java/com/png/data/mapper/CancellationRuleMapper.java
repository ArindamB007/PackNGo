package com.png.data.mapper;

import com.png.data.dto.property.CancellationRuleDto;
import com.png.data.entity.CancellationRule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CancellationRuleMapper {
    CancellationRuleMapper INSTANCE = Mappers.getMapper(CancellationRuleMapper.class);

    List<CancellationRuleDto> cancellationRulesToCancellationRuleDtos(List<CancellationRule> cancellationRules);

    default CancellationRuleDto CancellationRuleToCancellationRuleDto(CancellationRule cancellationRule) {
        if (cancellationRule == null) {
            return null;
        }
        CancellationRuleDto cancellationRuleDto = new CancellationRuleDto();
        cancellationRuleDto.setIdCancellationRule(cancellationRule.getIdCancellationRule());
        cancellationRuleDto.setCancelationPercent(cancellationRule.getCancelationPercent());
        cancellationRuleDto.setDaysFromCheckin(cancellationRule.getDaysFromCheckin());
        cancellationRuleDto.setCancellationDescription();
        return cancellationRuleDto;
    }
}

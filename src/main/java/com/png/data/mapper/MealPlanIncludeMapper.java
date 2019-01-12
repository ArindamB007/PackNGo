package com.png.data.mapper;

import com.png.data.dto.invoice.InvoiceDto;
import com.png.data.entity.Invoice;
import com.png.data.entity.MealPlanInclude;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MealPlanIncludeMapper {
    MealPlanIncludeMapper INSTANCE = Mappers.getMapper(MealPlanIncludeMapper.class);
    List<MealPlanInclude> IncludesToMealPlanIncludes(List<String> includes);
    List<String> MealPlanIncludesToIncludes(List<MealPlanInclude> mealPlanIncludes);
    default MealPlanInclude IncludeToMealPlanInclude(String include) {
        if (include == null) {
            return null;
        }
        MealPlanInclude mealPlanInclude = new MealPlanInclude();
        mealPlanInclude.setIncludeString(include);

        return mealPlanInclude;
    }
    default String MealPlanIncludeToInclude(MealPlanInclude mealPlanInclude) {
        if (mealPlanInclude == null) {
            return null;
        }
        return mealPlanInclude.getIncludeString();
    }
}

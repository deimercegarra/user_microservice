package com.pragma.user_microservice.application.mapper;

import com.pragma.user_microservice.application.dto.request.EmployeeRequestDto;
import com.pragma.user_microservice.domain.model.EmployeeModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeeRequestMapper {
    EmployeeModel toModel (EmployeeRequestDto employeeRequestDto);

}

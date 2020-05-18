package it.p0gram3r.playground;

import org.mapstruct.factory.Mappers;

public class MapStructMain {

    public static void main(String[] args) {
        EmployeeDTO dto = EmployeeDTO.builder()
                .employeeId(1)
                .employeeName("John")
                .build();

        EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);

        Employee entity = mapper.employeeDTOtoEmployee(dto);

        System.out.println(dto);
        System.out.println(entity);
    }
}



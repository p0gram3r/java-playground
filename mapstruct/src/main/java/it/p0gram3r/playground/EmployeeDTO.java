package it.p0gram3r.playground;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
class EmployeeDTO {
    private final int employeeId;
    private final String employeeName;
}

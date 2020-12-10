package com.example.task3;

import com.example.task3.service.SalaryService;
import com.example.task3.service.serviceException.UnacceptableValueInSalariesException;
import com.example.task3.service.serviceException.YearlySalaryIsNullException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SalaryServiceTest {

    private SalaryService salaryService = new SalaryService();

    @Test
    void calcAverageMonthlySalarySuccessCase() throws UnacceptableValueInSalariesException, YearlySalaryIsNullException {
        List<BigDecimal> yearlySalaries = new ArrayList<>();
        yearlySalaries.add(BigDecimal.valueOf(12));
        yearlySalaries.add(BigDecimal.valueOf(24));
        yearlySalaries.add(BigDecimal.valueOf(48));

        BigDecimal expected = new BigDecimal(((12.0 + 24.0 + 48.0) / 12.0) / 3.0).setScale(5, RoundingMode.HALF_UP);
        assertEquals(expected, salaryService.calcAverageMonthlySalary(yearlySalaries));

    }

    @Test
    void calcAverageMonthlySalaryWithStreamSuccessCase() throws YearlySalaryIsNullException {
        List<BigDecimal> yearlySalaries = new ArrayList<>();
        yearlySalaries.add(BigDecimal.valueOf(12));
        yearlySalaries.add(BigDecimal.valueOf(24));
        yearlySalaries.add(BigDecimal.valueOf(48));

        BigDecimal expected = new BigDecimal(((12.0 + 24.0 + 48.0) / 12.0) / 3.0).setScale(5, RoundingMode.HALF_UP);
        assertEquals(expected, salaryService.calcAverageMonthlySalaryWithStream(yearlySalaries));

    }

    @Test
    void calcAverageMonthlySalaryIfValueIsNegative() {
        List<BigDecimal> yearlySalaries = new ArrayList<>();
        yearlySalaries.add(BigDecimal.valueOf(-24));
        yearlySalaries.add(BigDecimal.valueOf(12));

        Exception exception = assertThrows(UnacceptableValueInSalariesException.class, () ->
                salaryService.calcAverageMonthlySalary(yearlySalaries));

        assertEquals("List of salaries contains null, negative number or 0. It is unacceptable.", exception.getMessage());
    }

    @Test
    void calcAverageMonthlySalaryWithStreamIfValueIsNegative() throws YearlySalaryIsNullException {
        List<BigDecimal> yearlySalaries = new ArrayList<>();
        yearlySalaries.add(BigDecimal.valueOf(12));
        yearlySalaries.add(BigDecimal.valueOf(-12));
        yearlySalaries.add(BigDecimal.valueOf(24));
        yearlySalaries.add(BigDecimal.valueOf(48));

        BigDecimal expected = new BigDecimal(((12.0 + 24.0 + 48.0) / 12.0) / 3.0).setScale(5, RoundingMode.HALF_UP);
        assertEquals(expected, salaryService.calcAverageMonthlySalaryWithStream(yearlySalaries));
    }

    @Test
    void calcAverageMonthlySalaryWithStreamIfValueIsZero() throws YearlySalaryIsNullException {
        List<BigDecimal> yearlySalaries = new ArrayList<>();
        yearlySalaries.add(BigDecimal.valueOf(12));
        yearlySalaries.add(BigDecimal.ZERO);
        yearlySalaries.add(BigDecimal.valueOf(24));
        yearlySalaries.add(BigDecimal.valueOf(48));

        BigDecimal expected = new BigDecimal(((12.0 + 24.0 + 48.0) / 12.0) / 3.0).setScale(5, RoundingMode.HALF_UP);
        assertEquals(expected, salaryService.calcAverageMonthlySalaryWithStream(yearlySalaries));
    }

    @Test
    void calcAverageMonthlySalaryWithStreamIfValueIsNull() throws YearlySalaryIsNullException {
        List<BigDecimal> yearlySalaries = new ArrayList<>();
        yearlySalaries.add(BigDecimal.valueOf(12));
        yearlySalaries.add(null);
        yearlySalaries.add(BigDecimal.valueOf(24));
        yearlySalaries.add(BigDecimal.valueOf(48));

        BigDecimal expected = new BigDecimal(((12.0 + 24.0 + 48.0) / 12.0) / 3.0).setScale(5, RoundingMode.HALF_UP);
        assertEquals(expected, salaryService.calcAverageMonthlySalaryWithStream(yearlySalaries));
    }

    @Test
    void calcAverageMonthlySalaryIfValueIsZero() {
        List<BigDecimal> yearlySalaries = new ArrayList<>();
        yearlySalaries.add(BigDecimal.ZERO);
        yearlySalaries.add(BigDecimal.valueOf(12));

        Exception exception = assertThrows(UnacceptableValueInSalariesException.class, () ->
                salaryService.calcAverageMonthlySalary(yearlySalaries));

        assertEquals("List of salaries contains null, negative number or 0. It is unacceptable.", exception.getMessage());
    }

    @Test
    void calcAverageMonthlySalaryIfValueIsNull() {
        List<BigDecimal> yearlySalaries = new ArrayList<>();
        yearlySalaries.add(null);
        yearlySalaries.add(BigDecimal.valueOf(12));

        Exception exception = assertThrows(UnacceptableValueInSalariesException.class, () ->
                salaryService.calcAverageMonthlySalary(yearlySalaries));

        assertEquals("List of salaries contains null, negative number or 0. It is unacceptable.", exception.getMessage());
    }

    @Test
    void calcAverageMonthlySalaryIfYearlySalariesIsEmpty() throws UnacceptableValueInSalariesException, YearlySalaryIsNullException {
        List<BigDecimal> yearlySalaries = new ArrayList<>();
        assertEquals(BigDecimal.ZERO, salaryService.calcAverageMonthlySalary(yearlySalaries));
    }

    @Test
    void calcAverageMonthlySalaryWithStreamIfYearlySalariesIsEmpty() throws YearlySalaryIsNullException {
        List<BigDecimal> yearlySalaries = new ArrayList<>();
        assertEquals(BigDecimal.ZERO, salaryService.calcAverageMonthlySalaryWithStream(yearlySalaries));
    }

    @Test
    void calcAverageMonthlySalaryIfYearlySalaryListIsNull() {
        Exception exception = assertThrows(YearlySalaryIsNullException.class, () ->
                salaryService.calcAverageMonthlySalary(null));

        assertEquals("List of salaries is null.", exception.getMessage());
    }

    @Test
    void calcAverageMonthlySalaryWithStreamIfYearlySalaryListIsNull() {
        Exception exception = assertThrows(YearlySalaryIsNullException.class, () ->
                salaryService.calcAverageMonthlySalaryWithStream(null));

        assertEquals("List of salaries is null.", exception.getMessage());
    }
}

package com.example.task3.service;

import com.example.task3.service.serviceException.UnacceptableValueInSalariesException;
import com.example.task3.service.serviceException.YearlySalaryIsNullException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@Component
public class SalaryService {
    private static final int NUMBER_AFTER_DOT = 5;
    private static final int COUNT_OF_MONTH = 12;

    /**
     * In case @param List<BigDecimal> yearlySalaries is empty returns 0
     *
     * @throws UnacceptableValueInSalariesException if yearlySalaries contains null,negative number or 0
     * @throws YearlySalaryIsNullException          if yearlySalaries is null
     */

    public BigDecimal calcAverageMonthlySalary(List<BigDecimal> yearlySalaries) throws UnacceptableValueInSalariesException, YearlySalaryIsNullException {

        if (yearlySalaries == null) {
            throw new YearlySalaryIsNullException("List of salaries is null.");
        }
        if (yearlySalaries.isEmpty()) {
            return new BigDecimal(0);
        }

        BigDecimal sumOfMonthSalary = new BigDecimal(0);
        for (BigDecimal salary : yearlySalaries) {
            if (salary == null || 0 >= salary.compareTo(BigDecimal.valueOf(0))) {
                throw new UnacceptableValueInSalariesException("List of salaries contains null, negative number or 0. It is unacceptable.");
            }
            BigDecimal monthSalary = salary.divide(BigDecimal.valueOf(COUNT_OF_MONTH), NUMBER_AFTER_DOT, RoundingMode.HALF_UP);
            sumOfMonthSalary = sumOfMonthSalary.add(monthSalary);
        }
        return sumOfMonthSalary.divide(BigDecimal.valueOf(yearlySalaries.size()), NUMBER_AFTER_DOT, RoundingMode.HALF_UP);
    }

    /**
     * In case @param List<BigDecimal> yearlySalaries is empty returns 0
     * If yearlySalaries contains null,negative number or 0 - method skips it
     *
     * @throws YearlySalaryIsNullException if yearlySalaries is null
     */
    public BigDecimal calcAverageMonthlySalaryWithStream(List<BigDecimal> yearlySalaries) throws YearlySalaryIsNullException {
        if (yearlySalaries == null) {
            throw new YearlySalaryIsNullException("List of salaries is null.");
        }
        if (yearlySalaries.isEmpty()) {
            return new BigDecimal(0);
        }
        BigDecimal[] sumOfMonthSalariesAndTheirCount = yearlySalaries.stream()
                .filter((salary) -> salary != null && 0 < salary.compareTo(BigDecimal.ZERO))
                .map(bigDecimal -> bigDecimal.divide(BigDecimal.valueOf(COUNT_OF_MONTH), NUMBER_AFTER_DOT, RoundingMode.HALF_UP))
                .map(bd -> new BigDecimal[]{bd, BigDecimal.ONE})
                .reduce((a, b) -> new BigDecimal[]{a[0].add(b[0]), a[1].add(BigDecimal.ONE)})
                .get();
        return sumOfMonthSalariesAndTheirCount[0].divide(sumOfMonthSalariesAndTheirCount[1], NUMBER_AFTER_DOT, RoundingMode.HALF_UP);
    }
}

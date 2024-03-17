package com.maksyank.finance.financegoal.service.process;

import com.maksyank.finance.financegoal.domain.Deposit;
import com.maksyank.finance.financegoal.domain.FinanceGoal;
import com.maksyank.finance.financegoal.domain.enums.CurrencyCode;
import com.maksyank.finance.financegoal.domain.enums.FinanceGoalState;
import com.maksyank.finance.financegoal.domain.enums.TransactionType;
import com.maksyank.finance.financegoal.exception.NotFoundException;
import com.maksyank.finance.financegoal.service.repoimpl.FinanceGoalRepoImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class DepositFundProcessTest {

    @InjectMocks
    private FundProcess fundProcess;

    @Mock
    private FinanceGoalRepoImpl financeGoalRepo;

    //    tests data
    final private FinanceGoal normalDataFinanceGoal =
            new FinanceGoal("Test Goal", FinanceGoalState.ACTIVE, CurrencyCode.USD, null,
                    BigDecimal.ZERO, BigDecimal.ZERO, null, null, LocalDateTime.now(), null);
    ;

    //    creating deposits
    LocalDateTime fundingDate1 = LocalDateTime.of(2024, 3, 10, 9, 0);
    Deposit deposit1 = new Deposit(TransactionType.FUND, "Initial deposit", fundingDate1, BigDecimal.valueOf(5), normalDataFinanceGoal);

    LocalDateTime fundingDate2 = LocalDateTime.of(2024, 3, 15, 12, 30);
    Deposit deposit2 = new Deposit(TransactionType.FUND, "Additional deposit", fundingDate2, BigDecimal.valueOf(15), normalDataFinanceGoal);


    @Test
    @DisplayName("Normal input data")
    void test_checkProcess() throws NotFoundException {

        //given
        int financeGoalId = 1;
        int year = 2024;
        int month = 1;
        int userId = 1;

        Collection<Deposit> deposits = new ArrayList<>();
        deposits.add(deposit1);
        deposits.add(deposit2);
        normalDataFinanceGoal.setDeposits(deposits);

//        mock
        when(financeGoalRepo.findByIdAndUserId(1, 1)).thenReturn(normalDataFinanceGoal);

        //when
        BigDecimal result = fundProcess.processGetFundAmountByMonth(financeGoalId, year, month, userId);

        BigDecimal expected = BigDecimal.valueOf(20);

        //then
        assertEquals(expected, result, "The fund amount for the given month should match the expected value.");
    }
}

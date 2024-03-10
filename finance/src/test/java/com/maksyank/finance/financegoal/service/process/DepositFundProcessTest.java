package com.maksyank.finance.financegoal.service.process;

import com.maksyank.finance.financegoal.domain.Deposit;
import com.maksyank.finance.financegoal.domain.FinanceGoal;
import com.maksyank.finance.financegoal.domain.enums.CurrencyCode;
import com.maksyank.finance.financegoal.domain.enums.FinanceGoalState;
import com.maksyank.finance.financegoal.domain.enums.TransactionType;
import com.maksyank.finance.financegoal.exception.NotFoundException;
import com.maksyank.finance.financegoal.service.repoimpl.FinanceGoalRepoImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

class DepositFundProcessTest {

    @InjectMocks
    private FundProcess fundProcess;
    @Mock
    FinanceGoalRepoImpl financeGoalRepo;

    //    tests data
    final private FinanceGoal normalDataFinanceGoal =
            new FinanceGoal("Test Goal", FinanceGoalState.ACTIVE, CurrencyCode.USD, null,
                    BigDecimal.ZERO, BigDecimal.ZERO, null, null, LocalDateTime.now(), null);
    ;


    //    creating deposits
    LocalDateTime fundingDate1 = LocalDateTime.of(2024, 3, 10, 9, 0);
    Deposit deposit1 = new Deposit(TransactionType.FUND, "Initial deposit", fundingDate1, BigDecimal.valueOf(100),normalDataFinanceGoal);

    LocalDateTime fundingDate2 = LocalDateTime.of(2024, 3, 15, 12, 30);
    Deposit deposit2 = new Deposit(TransactionType.FUND, "Additional deposit", fundingDate2, BigDecimal.valueOf(200),normalDataFinanceGoal);

    // Создание списка deposits и добавление в него созданных объектов
    List<Deposit> deposits = new ArrayList<>();


    @Test
    @DisplayName("Normal input data")
    void test_checkProcess() throws NotFoundException {

        //given
        int financeGoalId = 1;
        int year = 2024;
        int month = 1;
        int userId = 1;

//        mock
        Mockito.when(financeGoalRepo.findByIdAndUserId(1, 1)).thenReturn(normalDataFinanceGoal);

//      I didn't find how to add deposit to FG
//      and I don't understand how to get access to private method of FundProcess
//

        Mockito.when(fundProcess.findFundDepositsByMonth(normalDataFinanceGoal,
                        LocalDateTime.of(year, month, 1, 0, 0, 0),
                        LocalDateTime.of(year, month, YearMonth.of(year, month).lengthOfMonth(), 23, 59, 59)))
                .thenReturn(deposits);

        //when
        BigDecimal result = fundProcess.processGetFundAmountByMonth(financeGoalId, year, month, userId);

        BigDecimal expected = BigDecimal.ZERO; // Например, ожидается сумма 0, если список депозитов пустой

        //then
        assertEquals(expected, result, "The fund amount for the given month should match the expected value.");


    }
}

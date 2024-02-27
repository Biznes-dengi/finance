package com.maksyank.finance.financegoal.domain.common;

import com.maksyank.finance.financegoal.domain.common.enums.CurrencyCode;
import com.maksyank.finance.financegoal.domain.common.enums.FinanceGoalState;
import com.maksyank.finance.financegoal.domain.common.enums.RiskProfileType;
import com.maksyank.finance.user.domain.UserAccount;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "finance_goal")
public class FinanceGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_goal")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "state")
    private FinanceGoalState state;

    @Column(name = "currency")
    private CurrencyCode currency;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "target_amount")
    private BigDecimal targetAmount;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "risk_profile")
    private RiskProfileType riskProfile;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "last_change")
    private LocalDateTime lastChange;

    @ManyToOne
    @JoinColumn(name = "id_user_account")
    private UserAccount userAccount;

    @OneToMany(mappedBy = "financeGoal", fetch = FetchType.LAZY)
    private Collection<Deposit> deposits;

    public FinanceGoal(String title, FinanceGoalState state, CurrencyCode currency, String description, BigDecimal amount,
                       BigDecimal targetAmount, LocalDateTime deadline, RiskProfileType riskProfile,
                       LocalDateTime createdOn, UserAccount userAccount) {
        this.title = title;
        this.state = state;
        this.currency = currency;
        this.description = description;
        this.amount = amount;
        this.targetAmount = targetAmount;
        this.deadline = deadline;
        this.riskProfile = riskProfile;
        this.createdOn = createdOn;
        this.userAccount = userAccount;
    }

    public FinanceGoal(int id, String title, FinanceGoalState state, CurrencyCode currency, String description, BigDecimal amount,
                       BigDecimal targetAmount, LocalDateTime deadline, RiskProfileType riskProfile,
                       LocalDateTime createdOn, LocalDateTime lastChange, UserAccount userAccount) {
        this.id = id;
        this.title = title;
        this.state = state;
        this.currency = currency;
        this.description = description;
        this.amount = amount;
        this.targetAmount = targetAmount;
        this.deadline = deadline;
        this.riskProfile = riskProfile;
        this.createdOn = createdOn;
        this.lastChange = lastChange;
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "FinanceGoal(id=" + this.getId() + ", title=" + this.getTitle() +
                ", state=" + this.getState().state + ", currencyCode=" + this.getCurrency().code +
                ", description=" + this.getDescription() + ", amount=" + this.getAmount() +
                ", targetAmount=" + this.getTargetAmount() + ", deadline=" + this.getDeadline() +
                ", riskProfile=" + this.getRiskProfile().type + ", createdOn=" + this.getCreatedOn() +
                ", lastChange=" + this.getLastChange() + ", userAccountId=" + this.getUserAccount().getId() + ")";
    }
}

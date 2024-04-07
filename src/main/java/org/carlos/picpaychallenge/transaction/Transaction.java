package org.carlos.picpaychallenge.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("TRANSACTIONS")
public record Transaction(
    @Id Long id,
    Long payee,
    Long payer,
    BigDecimal value,
    @CreatedDate LocalDateTime createdAt
) {

  public Transaction {
    if (value != null) {
      value = value.setScale(2);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Transaction that)) {
      return false;
    }
    return Objects.equals(id, that.id) && Objects.equals(payee, that.payee) && Objects.equals(payer, that.payer) && Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, payee, payer, value);
  }
}

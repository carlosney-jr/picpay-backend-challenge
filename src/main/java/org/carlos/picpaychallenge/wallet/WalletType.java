package org.carlos.picpaychallenge.wallet;


public enum WalletType {

  CONSUMER(1),
  STORE(2);
  private Integer type;

  WalletType(Integer type) {
      this.type = type;
  }

  public Integer getType() {
    return type;
  }
}

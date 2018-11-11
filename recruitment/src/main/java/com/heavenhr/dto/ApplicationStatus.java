package com.heavenhr.dto;

public enum ApplicationStatus {
  APPLIED(1), INVITED(2), REJECTED(3), HIRED(3);

  private int stageOrder;


  ApplicationStatus(int stageOrder) {
    this.stageOrder = stageOrder;
  }

  /**
   * @return the stageOrder
   */
  public int getStageOrder() {
    return stageOrder;
  }



}

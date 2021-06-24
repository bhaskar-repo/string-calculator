package com.tdd.kata.string.calculator;

import org.springframework.stereotype.Service;

@Service
public class StringCalculator {

  public int add(String inputString) {
    if (inputString.isEmpty()) {
      return 0;
    }
    return -1;
  }
}

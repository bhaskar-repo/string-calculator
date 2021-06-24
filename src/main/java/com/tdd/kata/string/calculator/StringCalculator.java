package com.tdd.kata.string.calculator;

import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class StringCalculator {

  public int add(String inputString) {
    if (inputString.isEmpty()) {
      return 0;
    }
    if (inputString.length() == 1) {
      return Integer.parseInt(inputString);
    }
    String [] inputNumbers = inputString.split(",");
    if (inputNumbers.length == 2) {
      return Arrays.stream(inputNumbers)
          .mapToInt(Integer::parseInt)
          .sum();
    }
    return -1;
  }
}

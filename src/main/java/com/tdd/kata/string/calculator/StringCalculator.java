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
    String[] inputNumbers = inputString.split(",|\n");
    if (inputString.startsWith("//")) {
      StringBuilder stringBuilder = new StringBuilder();
      String[] delimiters = inputString.split("\n");
      String regex = stringBuilder.append(delimiters[0].replace("//", "")).toString();
      inputNumbers = delimiters[1].split(regex);
    }
    return Arrays.stream(inputNumbers)
        .filter(stringNumber -> !stringNumber.isEmpty())
        .mapToInt(Integer::parseInt)
        .sum();
  }
}

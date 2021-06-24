package com.tdd.kata.string.calculator;

import com.tdd.kata.string.calculator.exception.NegativeNumberException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
    List<Integer> inputNumbers = getInputNumbers(inputString);
    List<Integer> negativeNumbers = inputNumbers.stream()
        .filter(number -> number < 0)
        .collect(Collectors.toList());

    validateNegativeNumbers(negativeNumbers);
    
    return inputNumbers.stream().reduce(0, Integer::sum);
  }

  private void validateNegativeNumbers(List<Integer> negativeNumbers) {
    if (!negativeNumbers.isEmpty()) {
      String errorMessage = negativeNumbers.size() == 1 ? "negatives not allowed"
          : "negatives not allowed =" + negativeNumbers.toString();
      throw new NegativeNumberException(errorMessage);
    }
  }

  private List<Integer> getInputNumbers(String inputString) {
    String[] inputNumbers = inputString.split(",|\n");
    if (inputString.startsWith("//")) {
      StringBuilder stringBuilder = new StringBuilder();
      String[] delimiters = inputString.split("\n");
      String regex = stringBuilder.append(delimiters[0].replace("//", "")).toString();
      inputNumbers = delimiters[1].split(regex);
    }
    return Arrays.stream(inputNumbers)
        .filter(stringNumber -> !stringNumber.isEmpty())
        .map(Integer::parseInt)
        .collect(Collectors.toList());
  }
}

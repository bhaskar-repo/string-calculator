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
    List<Integer> inputNumbers = getInputNumbersFromInputString(inputString);
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

  private List<Integer> getInputNumbersFromInputString(String inputString) {

    return Arrays.stream(splitInputString(inputString))
        .filter(stringNumber -> !stringNumber.isEmpty())
        .map(Integer::parseInt)
        .filter(number -> number <= 1000)
        .collect(Collectors.toList());
  }

  private String[] splitInputString(String inputString) {
    String[] inputNumbers = inputString.split(",|\n");
    if (inputString.startsWith("//")) {
      String[] delimiters = inputString.split("\n");
      inputNumbers = delimiters[1].split(returnRegexBasedOnDelimiter(delimiters[0]));
    }
    return inputNumbers;
  }

  private String returnRegexBasedOnDelimiter(String delimiter) {

    StringBuilder stringBuilder = new StringBuilder();
    String[] multipleDelimiters = delimiter.replace("//", "").split("]");
    if (multipleDelimiters.length == 1) {
      return multipleDelimiters[0].length() == 1 ?
          stringBuilder.append(multipleDelimiters[0]).toString() :
          stringBuilder.append(multipleDelimiters[0]).append("]+").toString();
    }
    Arrays.stream(multipleDelimiters)
        .map(individualDelimiter -> individualDelimiter + "]+|")
        .forEach(stringBuilder::append);

    return stringBuilder.toString();
  }
}

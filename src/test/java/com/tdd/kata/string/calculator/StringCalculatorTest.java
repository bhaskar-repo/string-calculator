package com.tdd.kata.string.calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.tdd.kata.string.calculator.exception.NegativeNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StringCalculatorTest {

  @InjectMocks
  private StringCalculator stringCalculator;

  @Test
  public void givenAnEmptyStringsShouldReturn0() {
    //given
    String emptyString = "";
    //when & //then
    assertThat(stringCalculator.add(emptyString)).isZero();
  }

  @Test
  public void givenString_havingSingleNumber_shouldReturnSameNumber() {
    //given
    String singleNumber = "1";
    //when & //then
    assertThat(stringCalculator.add(singleNumber)).isEqualTo(Integer.parseInt(singleNumber));
  }

  @Test
  public void givenCommaSeparatedString_withTwoNumbers_shouldBeAdded () {
    //given
    String twoNumbers = "1,2";
    //when & //then
    assertThat(stringCalculator.add(twoNumbers)).isEqualTo(3);
  }

  @Test
  public void givenCommaSeparatedString_withUnknownAmountOfNumbers_shouldBeAdded() {
    //given
    String multipleNumbers = "1,2,3,4,5,6,7,8,9,10";
    //when & //then
    assertThat(stringCalculator.add(multipleNumbers)).isEqualTo(55);
  }

  @Test
  public void givenNewLineOrCommaSeparatedNumbers_shouldBeAdded() {
    //given
    String numbers = "1\n2,3";
    //when & //then
    assertThat(stringCalculator.add(numbers)).isEqualTo(6);
  }

  @Test
  public void givenNumbers_withDelimiterOtherThanCommaAndNewLine_shouldBeAdded() {
    //given
    String numbers = "//;\n1;2";
    //when & //then
    assertThat(stringCalculator.add(numbers)).isEqualTo(3);
  }

  @Test
  public void givenNumbers_withSingleNegativeNumber_shouldThrow() {
    //given
    String numbers = "//;\n1;-2";
    //when & //then
    assertThatThrownBy(() -> stringCalculator.add(numbers))
        .isInstanceOf(NegativeNumberException.class)
        .hasMessage("negatives not allowed");
  }

}

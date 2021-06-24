package com.tdd.kata.string.calculator;

import static org.assertj.core.api.Assertions.assertThat;

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

}

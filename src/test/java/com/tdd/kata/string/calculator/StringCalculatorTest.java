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

}

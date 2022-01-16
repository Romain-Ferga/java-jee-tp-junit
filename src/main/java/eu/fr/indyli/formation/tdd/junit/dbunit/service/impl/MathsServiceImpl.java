package eu.fr.indyli.formation.tdd.junit.dbunit.service.impl;

import eu.fr.indyli.formation.tdd.junit.dbunit.service.IMathsService;
import eu.fr.indyli.formation.tdd.junit.dbunit.service.exception.MathException;

public class MathsServiceImpl implements IMathsService {

  public Integer sum(Integer number1, Integer number2) {
    return Math.addExact(number1, number2);
  }

  public Integer substract(Integer number1, Integer number2) {
    return Math.subtractExact(number1, number2);
  }

  public Integer multiply(Integer number1, Integer number2) {
    return Math.multiplyExact(number1, number2);
  }

  public Integer divide(Integer number1, Integer number2) throws MathException {
    return Math.floorDiv(number1, number2);
  }

  public Boolean isPrimeNumber(Integer numP) throws MathException {
    // TODO 1: Codez cette fonction <b>isPrimeNumber</b>
    return Boolean.FALSE;
  }

  public Integer sumOfNFirstPrimeNumber(Integer numP) throws MathException {
    // TODO 2 : Codez cette fonction
    return 0;
  }

  public Integer average(Integer number1, Integer number2) {
    return (number1 + number2) / 2;
  }

  public Integer factorial(Integer numP) throws MathException {
    if (numP < 0) {
      throw new MathException("Le numbre ne peut être négatif");
    }
    if (numP == 0)
      return (1);
    else
      return (numP * factorial(numP - 1));
  }

}

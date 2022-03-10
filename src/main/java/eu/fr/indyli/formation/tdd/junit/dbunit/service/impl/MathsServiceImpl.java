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
	  
	  Boolean isPrimeNumber = Boolean.TRUE;
	  
	  if(numP < 0) throw new MathException("Le paramètre ne peut pas être négatif");
	  else if(numP < 1) {
		  
		  isPrimeNumber = Boolean.FALSE;
		  return isPrimeNumber;
		  
	  }else {
		  
		  for(int i = 2; i <= numP / 2; i++) {
			  
			  if((numP % i) == 0) {
				  
				  isPrimeNumber = Boolean.FALSE;
				  
				  break;
				  
			  }
			  
		  }
		  
		  return isPrimeNumber;
		  
	  }
	  
  }

  public Integer sumOfNFirstPrimeNumber(Integer numP) throws MathException {
	  
	  if(numP < 0) throw new MathException("Le paramètre ne peut être négatif..");
	  else if(numP == 0) return 0;
	  else {
	  
		  int sum = 0;
		  
		  for(int i = 0; i <= numP; i++) {
			  
			  if(this.isPrimeNumber(i)) sum += i;
			  
		  }
	  
		  return sum;
		  
	  }
    
  }

  public Integer average(Integer number1, Integer number2) {
    return (number1 + number2) / 2;
  }

  public Integer factorial(Integer numP) throws MathException {
    if (numP < 0) {
      throw new MathException("Le numbre ne peut �tre n�gatif");
    }
    if (numP == 0)
      return (1);
    else
      return (numP * factorial(numP - 1));
  }

}

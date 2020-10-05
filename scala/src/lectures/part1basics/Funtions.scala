package lectures.part1basics

import java.security.KeyStore.TrustedCertificateEntry

object Funtions extends  App {
  //  Name     Parameters ...       return =
  def aFunction(a: String, b: Int): String = {
    a + " " + b // String concatenation
  } // a block is an expression
  println(aFunction("hello", 21)) // call of the func is also expression
  // def aFunction(a: String, b: Int): = { // Could inference return type

  def aParameterlessFunction(): Int = 42;
  println(aParameterlessFunction())
  println(aParameterlessFunction) // Can be called without ()

  // NO LOOP -> Function Instead (Recursive)
  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n==1) aString
    else aString + aRepeatedFunction(aString, n-1)
  }
  println(aRepeatedFunction("hello", 3))
  // def aRepeatedFunction(aString: String, n: Int): = {
  // Could Not inference return type in recursive


  // WHEN YOU NEED LOOPS, USE RECURSION.
  // (fundamental Idea of functional programming)

  def aFunctionWithSideEffect(aSting: String): Unit = println(aSting)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b
    aSmallerFunction(n, n-1)
  }

  /* Exercises
    1. A greeting function (name, age) => "Hi, my name is $name and I am $age years old."
    2. Factorial function 1 * 2 * 3 * .... * n
    3. A Fibonacci function
      f(1) = 1
      f(2) = 1
      f(n) = f(n-1) + f(n-2)
    4. Test if a number is prime
   */

  def greeting(name: String, age: Int) =
    "Hi, my name is " + name + " and I am " + age + " years old."

  println(greeting("Tony", 29))

  def factorial(n: Int): Int =
    if (n == 1) 1 // if (n<=0) 1
    else n * factorial(n-1)

  println(factorial(5)) // 120

  def fibonacci(n: Int): Int =
    if (n==1 || n == 2) 1 // if (n<=2) 1
    else fibonacci(n-1) + fibonacci(n-2)
  println(fibonacci(8)) // 21
  // 1 1 2 3 5 8 13 21

  def isPrime(n: Int): Boolean = {
    /*
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t-1)
    isPrimeUntil(n/2)
    */
    def checkFactor(n: Int, checkN: Int): Boolean = {
      if (checkN == 1) true
      else if (n % checkN == 0) false
      else checkFactor(n, checkN-1)
    }
    checkFactor(n, n/2 + 1)
  }
  println(isPrime(37)) // true
  println(isPrime(2003)) // true
  println(isPrime(37 * 17)) // false


}

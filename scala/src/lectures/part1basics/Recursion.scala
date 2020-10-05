package lectures.part1basics
import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n - 1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)
      result
    }

  // RUN: println(factorial(5000))

  /*
    each call stackframe

    Computing factorial of 235 - I first need factorial of 234
    Exception in thread "main" java.lang.StackOverflowError
   */

  def anotherFactorial(n: Int): BigInt = {
    // compiler would tell you if not
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x == 1) accumulator
      else factHelper(x - 1, x * accumulator)
      // ^ this allow Scala to preserve the same stack frame
      // and not use additional stack frames for recursive calls
      // TAIL RECURSION !!! = use recursive call as the LAST expression
    }

    factHelper(n, 1)
  }

  /*
    anotherFactorial(10) = factHelper(10,1)
    = factHelper(9, 10 * 1)
    = factHelper(8, 9 * 10 * 1)
    = factHelper(7, 8 * 9 * 10 * 1)
    ...
    = factHelper(2, 3 * 4  * .. * 9 * 10 * 1)
    = factHelper(1, 2 * 3 * 4  * .. * 9 * 10 * 1)
   */
  // RUN: println(anotherFactorial(20000))

  // WHEN YOU NEED LOOPS, USE _TAIL_ RECURSION.
  // Any function could become tail recursive -> trick: accumulate
  /*
    1. Concatenate a string n times
    2. IsPrime function tail recursive
    3. Fibonacci function, tail recursive.
   */

  def stringConcatenate(s: String, n: Integer): String = {

    @tailrec
    def concatenateHelper(cs: String, n: Integer): String = {
      if (n > 0)
        concatenateHelper(cs + s, n - 1)
      else
        cs
    }

    concatenateHelper("", n)
  }

//  def concatenateTailrec(aString: String, n:Int, accumulator: String): String =
//    if (n <= 0) accumulator
//    else concatenateTailrec(aString, n-1, aString+accumulator)


  println(stringConcatenate("Tony ", 100))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeHelper(t: Int): Boolean = {
      if (t <= 1) true
      else if (n % t == 0) false
      else isPrimeHelper(t - 1)
    }

//    def isPrimeTailrec(t: Int, isStillPrime: Boolean): Boolean = {
//      if (!isStillPrime) false
//      else if (t <= 1) true
//      else isPrimeTailrec(t-1, n%t == 0 && isStillPrime)
//    }

    isPrimeHelper(n / 2)
  }

  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37 * 2003))

  def fibonacci(n: Int): Int = {
    @tailrec
    def fibonacciHelper(t1: Int, t2: Int, c: Int): Int = {
      if (c<=0) t2
      else {
        fibonacciHelper(t2, t1+t2, c-1)
      }
    }

//    def fibTailrec(i: Int, last: Int, nextToLast: Int): Int = {
//      if (i >= n) last
//      else fibTailrec(i+1, last+nextToLast, last)
//    }

    if (n<2) 1
    else fibonacciHelper(1,1,n-2)
  }

  println(fibonacci(8)) // 21
  // 1 1 2 3 5 8 13 21
}
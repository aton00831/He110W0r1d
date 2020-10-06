package lectures.part2oop

object Exceptions extends App {

  val x: String = null
  // println(x.length) // Someone throw java.lang.NullPointerException
  // this ^^ will crash with java.lang.NullPointerException

  // 1. throwing exceptions

  // val aWeirdValue: String = throw new NullPointerException // an expression return Nothing
  //               ^^ Still work, valid substitute because nothing is a valid substitution fot any other type

  // - exceptions are actually instances of classes
  // - extending from throwable type (throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtype

  // 2. how to catch exceptions

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  // AnyVal,because tried unify int(42) and Unit (catch cases)
  val potentialFail: AnyVal = try {
    // code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => println("caught a Runtime exception")
  } finally {
    // code that will get executed NO MATTER WHAT
    // finally block is optional
    // does not influence the return type of this expression
    // use finally only for side effects

    println("finally")
  }

  // Exceptions come from the Java language
  // and they are a JVM specific thing, not a Scala specific thing

  // 3. how to define your own exceptions
  class MyException extends Exception
  val exception = new MyException

  // throw exception

  /* Exercise
  1. Crash your program with an OutOfMemoryError
  2. Crash with StackOverflowError
  3. PocketCalculator (class)
    - add(x,y)
    - subtract(x,y)
    - multiply(x,y)
    - divided(x,y)

  Throw
    - OverflowExceptions if add(x,y) exceeds Int.MAX_VALUE
    - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
    - MathCalculationException for division by 0
   */

  // OOM
  // java.lang.OutOfMemoryError: Requested array size exceeds VM limit
  // val array = Array.ofDim(Int.MaxValue)

  // SO
  // def infinite: Int = 1 + infinite
  // val noLimit = infinite

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x:Int, y:Int) = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x:Int, y:Int) = {
      val result = x - y
      if (x>0 && y <0 && result < 0) throw new OverflowException
      else if (x<0 && y>9 && result >0) throw new UnderflowException
      else result
    }

    def multiply(x:Int, y:Int) = {
      val result = x * y
      if (x>0 && y>0 && result <0) throw new OverflowException
      else if (x<0 && y<0 && result <0) throw new OverflowException
      else if (x>0 && y<0 && result >0) throw new UnderflowException
      else if (x<0 && y>0 && result >0) throw new UnderflowException
      else result
    }
    def divided(x:Int, y:Int) = {
      if (y==0) throw new MathCalculationException
      else x / y
    }

  }
  // println(PocketCalculator.add(Int.MaxValue, 10))
  println(PocketCalculator.divided(Int.MaxValue, 0))

}

/* Takeaways
- Exceptions crash your program
- How to throw exceptions - throwing return Nothing
`val someValue = throw new RuntimeException`
- How to catch exceptions
```
try {
  // compute a value
} catch {
  case e: RuntimeException => /*another value*/
} finally {
  // block for side effect
}
- Define custom exceptions
`class MyKnife extends Exception
 */
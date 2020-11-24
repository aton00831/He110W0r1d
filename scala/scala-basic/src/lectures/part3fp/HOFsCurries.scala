package lectures.part3fp

object HOFsCurries extends App {

  // val superFunction: (Int, (String, (Int=>Boolean))=>Int)=>(Int=>Int) = ???
  // exercise: what is input & output?
  // Higher order function (HOF): take func as para or return func

  // e.g. map, flatMap, filter in MyList

  // function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x)))
  //                 = nTimes(f, 2, f(x))
  // nTimes(f, 3, x) = f(f(...(x))) = nTimes(f, n-1, f(x))
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimes(f, n-1, f(x))
  }

  val plusOne = (x: Int) => x + x
  println(nTimes(plusOne, 10, 1))

  // nTimesbetter(f,n) = x => f(f(f...(x))) return a lambda
  // increment10 = ntb(plusOme, 10) = x => plusOne(plusOne ... (x))
  // val y = increment10(1)
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n-1)(f(x))
  // This kind of definition is `intuition` in scala/ FP

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  // curried functions
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // y => 3 + y
  println(add3(10))
  println(superAdder(3)(10))

  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)
  // act like curried function
  // as many para lust as you want

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  /*
    1. Expand MyList
      - foreach method A => Unit
        [1,2,3].foreach(x => println(x))

      - sort function((A, A) => Int) => MyList // neg is less, positive otehrwise
        [1,2,3].sort((x, y) => y - x) => [3,2,1]

      - zipWith (list, (A, A) => B) => MyList[B]
        [1,2,3].zipWith([4,5,6], x*y) => [1 * 4, 2 * 5, 3 * 6] = [4, 10, 18]

      - fold(start)(function) => a value
        [1,2,3].fold(0)(x + y) = 6

    2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
       fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int

    3. compose(f, g) => x => f(g(x))
       andThen(f, g) => x => g(f(x))
   */

  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) =
    x => y =>  f(x, y)

  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int =
    (x, y) => f(x)(y)


  // FunctionX
  def gcompose[A, B, T](f: A => B, g: T => A): T => B =
    x => f(g(x))

  def gandThen[A, B, C](f: A => B, g: B => C): A => C =
    x => g(f(x))

  def compose(f: Int=>Int, g:Int=>Int): Int => Int =
    x => f(g(x))

  def andThen(f: Int=>Int, g:Int=>Int): Int => Int =
    x => g(f(x))


  def superAdder2: (Int => Int => Int) = toCurry(_ + _)
  def add4 = superAdder2(4)
  println(add4(17)) // 21

  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4, 17)) // 21


  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3
  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)
  println(composed(4)) // 14
  println(ordered(4)) // 18
}

/* Takeaways
Function Programming = working with functions
- pass functions as parameters
- return functions as results

=> Higher Order Functions (HOFs)
`def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = ...`

Currying = functions with multiple parameter lists
`def curriedFormatter(c: String)(x: Double): String = ...`
                      ^ para list  ^ para list
 */
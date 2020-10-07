package lectures.part3fp

object Anonymous extends App {
  // The problem of function types is that
  // instantiating a function is still very much tied to the OO way

  val doublerOld = new Function1[Int, Int] {
    override def apply(x: Int) = x * 2
  }

  // anonymous function (LAMBDA)
  val doublerNew: Int => Int = x => x * 2

  // name lambda comes from the term lambda calculus
  // hwich is the mathematical representation of FP
  // was formalized by Alonzo Church in the 1930s

  // multiple params in a lambda
  val adder: (Int, Int) => Int = (a, b) => a + b

  // no parameter lambda
  val justDoSomething: () => Int = () => 3

  // CAREFUL
  println(justDoSomething) // function itself: lectures.part3fp.Anonymous$$$Lambda$19/0x0000000800bbaa78@7f63425a
  println(justDoSomething()) // call: 3

  // curly braces with lambdas (coding style)
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MORE Syntactic sugar
  // val niceIncrementer: Int => Int = (x: Int) => x + 1
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  // val niceAdder: (Int, Int) => Int = (a, b) => a + b
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b
  // underscore notation is extremely contextual

  /* Excercise
    1. MyList: replace all FunctionX calls with lambdas
    2. Rewrite the "special" adder as an anonymous function

   */

//  /*
//  println(listOfIntegers.map(new Function1[Int, Int] {
//    override def apply(elem: Int): Int = elem * 2
//  }))
//  */
//  // println(listOfIntegers.map(elem => elem * 2))
//  println(listOfIntegers.map(_ * 2))
//
//  /*
//  println(listOfIntegers.filter(new Function1[Int, Boolean]{
//    override def apply(elem: Int): Boolean = elem % 2 == 1
//  }))
//  */
//
//  // println(listOfIntegers.filter(elem => elem % 2 == 1))
//  println(listOfIntegers.filter(_ % 2 == 1))
//
//  // println(listOfIntegers ++ anotherListOfIntegers)
//
//  /*
//  println(listOfIntegers.flatMap(new Function1[Int, MyList[Int]] {
//    override def apply(elem: Int): MyList[Int] =
//      new Cons(elem, new Cons(elem+1, Empty))
//  }))
//  */
//  println(listOfIntegers.flatMap(elem => new Cons(elem, new Cons(elem+1, Empty))))
//  // underscore not work here

  // val superAdd = (x:Int) => (y:Int) => x + y
  val superAdd: Int=>Int=>Int = x => y => x+y
  println(superAdd(3)(4))
}

/* Takeaways
Instead of passing anonymous FunctionX instances everytime
- cumbersome
- still OO
user lambda (x, y) = x + y

- Further sugar: underscore
 */
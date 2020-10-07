package lectures.part3fp

object WhatsAFunction extends App {

  // DREAM: use functions as first class elements
  // problem: oop

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2)) // acts like a function

  // function types = Function1[A,B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }
  // up to 22 parameter
  // Function2[Int, Int, Int]
  // Syntax sugar ((Int, Int) => Int)
  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A,B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS
  // or instances of classes deriving from function1 ~ function23
  // scala is Functional Programming Language
  // based on Java Virtual Machine (OO in mind)
  // which was never originally designed with F.P. in mind
  // through a series of syntactic features we'll get to Scala a truly FP language

  /*
    1. a function with takes 2 strings and concatenates them
    2. transform the MyPredicate and MyTransformer into function type
    3. define a function which takes an int and
    return another function which takes an int and return int
      - what's the type of this function
      - how to do it
   */

  val concatenator: ((String, String) => String) =
    new Function2[String, String, String] {
      override def apply(a: String, b:String): String = a + b
    }
  println(concatenator("hello", "scala"))

  println(stringToIntConverter("3")+4)


  /* Copy from MyListAdvanced
    // Original
    def map[B](transformer: MyTransformer[A, B]): MyList[B]
    def flatMap[B](transformer: MyTransformer[A,MyList[B]]): MyList[B]
    def filter(predicate: MyPredicate[A]): MyList[A]

    // Higher-Order Functions
    // either receive functions as parameters
    // or return other functions as a result
    // critical concept to FP.
    def map[B](transformer: A => B): MyList[B]
    def flatMap[B](transformer: A => MyList[B]): MyList[B]
    def filter(predicate: A => Boolean): MyList[A]
   */

  // Function1[Int, Function1[Int, Int]]
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]]{
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      // x is visible inside
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // curried function (the superAddr)
  // curried function have the property that they can be
  // called with multiple parameter list by their mere definition

}


// How JVM naturally:
trait MyFunction[A,B] {
  def apply(element: A): B
}

/* Takeaways
We want to work with functions
- pass functions as parameters
- use functions are values

Problem: Scala works on top of the JVM
- designed for JAVA
- first-class elements: objects(instances of classes)

Solution: All Scala functions are objects!
- function traits, up to 22 params
- syntactic sugar function types
 */
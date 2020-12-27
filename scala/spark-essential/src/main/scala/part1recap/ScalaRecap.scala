package part1recap

import scala.concurrent.Future
import scala.util.{Failure, Success}

object ScalaRecap extends App {

  // values and variables
  val aBoolean: Boolean = false

  // expressions
  val anIfExpression = if(2 > 3) "bigger" else "smaller"

  // instructions(Imperative, executed 1by1) vs expressions(FP, evaluated, reduce to single value)
  val theUnit = println("Hello, Scala") // Unit = "no meaningful value" = void in other languages

  // functions
  def myFunction(x: Int) = 42

  // OOP
  class Animal
  class Cat extends Animal
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override def eat(animal: Animal): Unit = println("Crunch!")
  }

  // singleton pattern
  object MySingleton

  // companions, class + singleton pattern
  object Carnivore

  // generics (type system)
  trait MyList[A]

  // method notation
  val x = 1 + 2
  val y = 1.+(2)

  // Functional Programming (Functions = Instance of Traits, Func1, Func2 ... Func 22)
  val incrementer: Int => Int = x => x + 1  // lambda function
  // = Function1[Int, Int] = new Function1[Int, Int] {
  //  override def apply(x: Int): Int = x + 1
  val incremented = incrementer(42)

  // map, flatMap, filter, HOF (received function as arguments)
  val processedList = List(1,2,3).map(incrementer)

  // Pattern Matching
  val unknown: Any = 45
  val ordinal = unknown match { // decomposing value, collection ..
    case 1 => "first"
    case 2 => "second"
    case _ => "unknown"
  }

  // try-catch
  try {
    throw new NullPointerException
  } catch {
    case _: NullPointerException => "some returned value"
    case _: Throwable => "something else"
  }

  // for comprehension = sugar for chain map, flatMap, filter

  // -- advance part --

  // Future, are typed with the value that they contain after they have been completed on whatever thread they want
  import scala.concurrent.ExecutionContext.Implicits.global
  val aFuture = Future {
    // some expensive computation, runs on another thread
    42
  }

  aFuture.onComplete {
    case Success(meaningOfLife) => println(s"I've found $meaningOfLife")
    case Failure(ex) => println(s"I have failed: $ex")
  }

  // Partial functions
  // val aPartialFunction = (x: Int) => x match {
  val aPartialFunction: PartialFunction[Int, Int] = {
      case 1 => 43
      case 8 => 56
      case _ => 999
  }

  // Implicits

  // auto-injection by the compiler
  def methodWithImplicitArgument(implicit x: Int) = x + 43
  implicit val implicitInt = 67
  val implicitCall = methodWithImplicitArgument

  // implicit conversions - implicit defs
  // Case Class = lightweight data structure that have a bunch of utility methods already implemented by compiler
  case class Person(name: String) {
    def greet = println(s"Hi, my name is $name")
  }

  implicit def fromStringToPerson(name: String) = Person(name)
  "Bob".greet // fromStringToPerson("Bob").greet

  // implicit conversion - implicit classes
  implicit class Dog(name: String) {
    def bark = println("Bark!")
  }
  "Lassie".bark

  /* Implicit Searching for
    - local scope
    - imported scope
    - companion objects of the types involved in the method call
   */

}

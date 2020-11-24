package lectures.part2oop
import scala.language.postfixOps

object MethodNotations extends App {

  // defined inside to preventing conflict
  class Person(val name: String, favoriteMovie: String, val age: Int=0) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def hangOutWith(person: Person): String = s"${this.name} if hanging out with ${person.name}"
    def &&&(person: Person): String = s"${this.name} if hanging out with ${person.name}"

    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)
    def unary_! : String = s"$name, waht the heck?!"

    def unary_+ : Person = new Person(name, favoriteMovie, age+1)
    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    def apply(n: Int): String = s"$name watched $favoriteMovie $n times"

    def learns(thing: String): String = s"$name is learning $thing"
    def learnsScala = this learns "Scala"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent, so nature language!
  // infix notation = operator notation (Syntactic sugar!)
  // only work with method with only one parameter

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)
  println(mary &&& tom)
  println(mary.&&&(tom))

  println(1 + 2)
  println(1.+(2))

  // ALL OPERATORS ARE METHODS !!
  // Akka actors have ! ?

  // prefix notation, unary operator
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ ! (bang)
  println(!mary)


  // postfix notation
  // function do not receive any parameter
  // Not recommended, ambiguous
  println( mary.isAlive )
  // purpose: more natural language
  // println( mary isAlive ) // should open the flag -language:postfixOps. or failed

  // apply, extreme special in scala
  // called like a function,
  // this breaks that barrier between OOP & FP
  println(mary.apply())
  print(mary()) // equivalent


  /*
    1. Overload the plus operator : (String) -> NickName
      mary + "the rockstar" => new person "Mary (the rockstar)"

    2. Add an age to Person class (=0)
       Add a unary + operator => new person with the age + 1
       +mary => mary the the age incrementer

    3. Add a "learns" method in the Person class => Mary learns Scala
      Add a learnScala method () => calls learns method with "Scala"
      Use it in postfix notation

    4. Overload the apply method
      mary.apply(2) => "Mary watched inception 2 times

   */

  println((mary + "the Rockstar")())
  println((+mary).age)

  println(mary learnsScala)

  println(mary(10))

}

/*
  Takeaway

  1. Infix notation - only used for methods with one parameter
  mary.likes("Inception)
  mary likes "Inception

  2. prefix notation - only allowed for +,-,!,~
  mary.unary_!
  !mary

  3. Postfix notation - only used for method with no parameter
  mary.isAlive
  mary isAlive
  !!! not recommend to use, it cause confusion when reading code
  to solve this, scala force to open flag to use
  import scala.language.postfixOps

  4. apply method is special, allow you to call your object like functions
  mary.apply("Hi there")
  mary("Hi there")
 */

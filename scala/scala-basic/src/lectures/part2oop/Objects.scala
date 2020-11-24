package lectures.part2oop

object Objects extends App {
  // SCALA DOES NOT HAVE CLASS_LEVEL FUNCTIONALITY ("static")
  //>>> COMPANIONS Pattern
  object Person { // type + its only instance
    // "static"/"class" - level functionality
    // singleton object
    val N_EYES = 2
    def canFly: Boolean = false

    // factory pattern
    //def from(mother: Person, father: Person): Person = new Person("Bobbie")
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }
  class Person(val name: String) { // in the same file or Same scope
    // instance-level functionality
  }
  //<<< COMPANIONS Pattern

  /*
  COMPANIONS Pattern 一定要用同樣名字
  it's clearer if you write val bob = Person()
  instead of val bob = PersonFactory().
   */

  // code 會在其中一個
  println(Person.N_EYES)
  println(Person.canFly)
  /* is difference to JAVA
    class Person {
        public static final int N_EYES = 2;
    }
   */

  // Scala object = SINGLETON INSTANCE
  // definition = type + its only instance
  val mary = Person // the only instance of Person type
  val john = Person
  println(mary == john) // true

  val mary2 = new Person("Mary") // the only instance of Person type
  val john2 = new Person("John")
  println(mary2 == john2) // false

  val bobbie = Person(mary2, john2)

  // Scala Applications = Scala object with
  // def main(args: Array[String]): Unit

  /*

  class instances of the class "Person" and
  the "Person" object are not the same type!

  the Person object is actually compiled to
  a different JVM class Person$.

  The compiler will naturally assume you'll pass arguments
  which are instances of the Person class, because it makes
  no sense to pass the Person object to a function - you might
  as well use it directly, because it's a singleton
  .
   */

}

/*
class level functionality:
functionality does not depend on an instance of a class

  Takeaway
  - Scala doesn't have "static" values/ methods
  - Scala objects
  are in their own class
  are the only instance
  singleton pattern in one line

  - Scala Companions
  can access each other's private members
  scala is more OO than Java

  - Scala applications
  def main(args: Array[String]):
  object MyApp extends App

my argument was that Scala is "more object-oriented"
because every usable code belongs to some instance of a class.

In Python or C++, you can write top-level functions and variables
that don't belong to a class or object.

 */
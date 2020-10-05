package lectures.part2oop

object OOBasics extends App {
  val person = new Person("Tony", 29)
  // println(person.age) age is classParameter but not member
  println(person.x)
  println(person.y)

  person.greet("Daniel")
  person.greet()

  val person2 = new Person("Bagel")
  person2.greet()

  // class Person(name: String, age: Int) // constructor
  // class parameters are NOT FIELDS, at least add keyword val

  // constructor
  class Person(name: String, val age: Int) {
    // body
    val x = 2 // fields
    var y = 2 // fields

    println(x + 3) // will be executed

    // method
    def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

    // overloading, same name but different signature (different numbers of para, or different type)
    def greet(): Unit = println(s"Hi, I am ${name}")

    // multiple constructors, overloading constructor
    def this(name: String) = this(name, 0) // in this case, use default constructor
    def this() = this("John Doe")

  }

  /**
   * class organized data and behavior are code
   * instantiation means concrete realization in memory
   */

}




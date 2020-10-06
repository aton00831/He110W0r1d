package lectures.part2oop

object CaseClasses extends App {
  /* Syntax Sugar
  * Quick lightweight data structures with little boilerplate
  * Case classes are meant as lightweight data structures.
  * Their purpose is to be passed around and manipulated in collections,
  * perhaps over the network.
  *
  * Normal classes are usually really big, with lots of code
  * (for example anything with Controller in its name
  *  has a high chance of having lots of code).
  *
  * These are not meant to be instantiated lots of times and passed around -
  * and it would not be the best thing you could do,
  * because passing them around (especially over the network) is very expensive.
  * */

  class NoCasePerson(name: String, age: Int)
  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  // (auto-promoted params to fields)
  var jim = new Person("Jim", 34)
  var ncjim = new NoCasePerson("Jim", 34)
  println(jim.name)

  // 2. sensible toString (easy for debugging)
  // println(instance) = println(instance.toString) // syntactic sugar
  println(jim.toString)
  println(ncjim.toString)

  // 3. equals and hashCode implemented OOTB
  var jim2 = new Person("Jim", 34)
  println(jim == jim2) // true
  println(jim == ncjim) // false

  // 4. cass classes have handy copy method
  val jim3 = jim.copy() // create new instance
  val jim4 = jim.copy(age=45)
  println(jim3)
  println(jim4)
  /* https://stackoverflow.com/questions/52966711/scala-case-class-uses-shallow-copy-or-deep-copy

val p1 = Person("amit", "shah")
val p2 = p1.copy()

hen p2 is a **shallow copy** of p1, so the variables p1.firstname and p2.firstname point to the same value of String type which is "amit".

    */
  // 5. CCs have companion object
  val thePerson = Person
  val marry = Person("Mary", 23) // apply method = constructor

  // 6. CCs are serializable (dealing with distributed system)
  // (send instance of ccs through network and in between JVM
  // Akka framework

  // 7. CCs have extractor patterns = CCs can be used in PATTEN MATCHING

  // also support Case Objects
  //acts like case class except it's an object, except no Companion Object
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  // Exercise: Expand MyList - use case classes and case objects
}

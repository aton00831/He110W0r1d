package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // an anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahaha")
  }

  /* equivalent with

  class AnonymousClasses$$anon$1 extends Animal {
    override def eat: Unit = prinln("hahaha")
  }
  val funnyAnimal: Animal = new AnonymousClasses$$anon$1
   */

  println(funnyAnimal.getClass) // class lectures.part2oop.AnonymousClasses$$anon$1

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of service?")
  }

  /* Takeaways

   */
}
/* Takeaways


Rules
- We can instantiate types and override fields or methods on the spot
```Scala
trait Animal {
  def eat: Unit
}

val predator = new Animal {
  override def eat: Unit = prinln("RAWR!")
}

```

- Rules
  - pass in required constructor arguments if needed
  - implement all abstract fields/ methods

- Works for traits and classes (abstract or not)
 */


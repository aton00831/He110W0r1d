package lectures.part2oop

object AbstractDataTypes extends App {

  // abstract - contains unimplemented or abstract field, methods
  abstract class Animal {
    val creatureTypeNonAbstract: String = "Animal Test"
    val creatureType: String // not define it
    def eat: Unit // unimplemented
    // let subclass to do that for you
  }

  // note: abstract classes can not be instantiated!!

  class Dog extends Animal {
    // override val creatureType: String = "Canine"
    // override is optional
    val creatureType: String = "Canine"

    def eat: Unit = println("crunch crunch")

    // non-abstract must use override
    override val creatureTypeNonAbstract: String = "Dog Test"
  }

  // traits, ultimate abstract data type in scala
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded

  // inheritance both animal and carnivore
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"

    override def eat: Unit = println("nomnomnom")

    def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }
  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // traits vs abstract classes
  // abstract classes can have both abstract and non-abstract member
  // so can traits
  // 1 - traits do not have constructor parameters
  // WRONG: trait Carnivore(name:...)
  // 2 - multiple traits may be inherited by the same class
  // Scala has single class inheritance and multiple trait inheritance
  // 3 - traits = behavior (more subtle choice)
  //     abstract class = things
  // When choose trait? When it describes a type of behavior

  /* Scala's Type Hierarchy
  1. `scala.Any` (mother of all types)

  2. scala.Any <--- `scala.AnyRef` (map to java.lang.Object)
    All classes you'll use will derive from AnyRef
    unless you explicitly say they extend some other class
    e.g. String, List, Set,
         all user derived classes even not explicitly say so
         like class person (actually extends AnyRef)


  3. scala.AnyRef <--- `scala.Null` (basically means no reference)
    so you can replace person with no reference

  4. scala.Any <--- scala.AnyVal e.g. Int, Unit, Boolean, Float
    very early used in practice
    contain primitive type
    (maybe for some memory optimizations)

  5. scala.Any, scala.Null <--- scala.Nothing
    nothing can replace everything!!
    is a subtype of every single thing
    make sense in exceptions and expression returning
   */
}


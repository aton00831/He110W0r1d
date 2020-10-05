package lectures.part2oop

object Inheritance extends App{


  class Animal {
    val creatureType = "wild"
    def eat = println("(Animal_eat) nomnom")
    // v- access only within this class
    private def privateEat = println("(Animal_private_eat) nomnom")
    // v- access only within this class and subclass
    protected def protectedEat = println("(Animal_protected_eat) nomnom")
  }

  class Cat extends Animal {
    def crunch = {
      protectedEat
      println("(Cat_crunch) crunch crunch")
    }
  }

  // inheritance non private member of super class
  // single class inheritance, inheritance 1 class at a time
  val cat = new Cat
  cat.eat
  // cat.privateEat inaccessible
  // cat.protectedEat
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  // JVM: need to call constructor of parent then self
  // wrong: class Adult(name: String, age: Int, idCar:String) extends Person
  class Adult(name: String, age: Int, idCar:String) extends Person(name, age)

  // overriding
  // field 可以直接在 constructor override
  // class Dog(override val creatureType: String) extends Animal {
  // class Dog(dogType: String) extends Animal {
  // override val creatureType = dogType
  class Dog(override val creatureType: String) extends Animal {
    override def eat = {
      super.eat
      println("(Dog_eat) crunch, crunch")
    }
  }
  val dog = new Dog("domestic")
  dog.eat
  println(dog.creatureType)

  // derived class 可以用 override 就用 override (use when it is possible)


  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat // use dog method, even it is animal type
  // a method call will always go to the overridden version whenever possible

  // overRIDING vs overLOADING
  // overRIDING: supply different implementation in derived classes
  // overLOADING : supply multiple method with different signatures
  //               but with the same name  in the same class

  // super: referent a method or field from parent class

  // preventing overrides
  // 1 - use final on member
  // 2 - use final on the entire class (class can't be extended)
  // !! numerical classes and string type are final
  // 3 - sealed the class  = extend classes in THIS FILE,
  //                       prevent extensions in other files
  // (soft restriction)
  // sealed class Anima {
  // often used when you want to be exhaustive in your type hierarchy
  // e.g. the only two possible animals in this world would be cats and dogs




}

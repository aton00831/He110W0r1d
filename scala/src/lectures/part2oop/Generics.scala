package lectures.part2oop

object Generics extends App {
  class MyList[A] {
    // use the type A in class definition
  }

  class MyMap[Key, Value] {
    // Multiple Types
  }

  trait MyTrait[A] {
    // use the type A in class and trait definition
  }

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  // val emptyListOfIntegers = MyList.empty[Int]

  // Objective: Want to create a String Linked-List based on Int Linked-List
  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]


  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal
  // Q: If Cat extends animal, does a list of cat also extend the list of animal?

  // 1. yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ?? POLLUTE the list of animals, #HARD_QUESTION.
  // Adding a dog to a list of cats would actually turn it into something more generic
  // list of cats + new dog -> list of animals

  // 2. NO List[Cat], List[Animal] are two separated things = INVARIANCE
  class InvarianceList[A]
  // WRONG! val invarianceList: InvarianceList[Animal] = new InvarianceList[Cat]

  // 3. Hell, no! CONTRAVARIANCE (Counter=intuitive, opposite relationship)
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]
  // a more meaningful example ...
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // BOUNDED TYPES: allow you to use your generic classes only for certain types
  // that are either subclass of a different type or a superclass of a different type
  // (work with covariant)
  // upper bounded types
  class Cage[A <: Animal](animal: A) // A only accept subtype of Animal
  val cage = new Cage(new Dog)

  // lower bounded types
  // class Cage[A >: Animal](animal: A) // A only accept supertype of Animal

  // class Car
  // val newCage = new Cage(new Car) // CAN NOT RUN
  // generic type need proper bounded type
  // bounded types solve a variance problem which is very ver annoying
  // when we want to write covariant collections

  class MyCovariantList[+A] {
    // def add(element: A): MyCovariantList[A] = ???
    // ^ does not work ?
    // ?! ERROR MSG: Covariant type A occurs in contravariant position in type A of value element
    // #HARD_QUESTION. in answer 1
    def add[B >: A](element: B): MyCovariantList[B] = ???
    /*
      A = Cat
      B = Dog = Animal
     */
    // not stress too much on this hard problem, this is an advanced topic

  }

  // expand MyList to be generic
  abstract class EMyList[+A] {
    def head: A
    def tail: EMyList[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): EMyList[B]
    def printElements: String
    override def toString: String = "[" + printElements + "]"
  }

  class ECons[+A](h : A, t : EMyList[A]) extends EMyList[A] {
    // def head[B >: A]: B= h
    def head: A = h
    def tail: EMyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](element: B): EMyList[B] = new ECons(element, this)
    def printElements: String =
      if (t.isEmpty) "" + h
      else h + " " + t.printElements
  }

  // String
  // Nothing type is a proper substitute for any type
  object Empty extends EMyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: EMyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing](element: B): EMyList[B] = {
      // new ECons(element, this)
      new ECons[B](element, this)
    }
    override def printElements: String = ""
  }

  val eListOfIntegers: EMyList[Int] =
    new ECons(1, new ECons(2, new ECons(3, Empty)))
  val eListOfStrings: EMyList[String] =
    new ECons("Today", new ECons("is", new ECons("sunny", Empty)))

  // println(list.tail.head)
  // println(list.add("hi").head)
  // println(list.isEmpty)

  println(eListOfIntegers.toString)
  println(eListOfStrings.toString)

  // another issue
  class Food

  class Bagel extends Food {
    override def toString: String = "I am Bagel"
  }

  class Donut extends Food {
    override def toString: String = "I am Donut"
  }

  val bagel = new Bagel
  val donut = new Donut

  val listOfFoods: EMyList[Food] =
    new ECons[Food](bagel, new ECons[Food](donut, Empty))

  println(listOfFoods.add(5).toString)
  // [5 I am Bagel I am Donut] ??
  // add method allows adding junk because it's considered as **Any**, which is a supertype of Food. As such, your end list will be a list of Any.
  // You would normally need a type bound, but here a type bound would be in contradiction to the existing [B >: A] which is required for the code to compile.
  // If you want to not use variance at all, you can type the list without the +,
  // like MyList[A] and make the Empty type a class Empty[A]. In this way, you won't need any variance annotations or any type bounds.

  /*
I see. The problem is that this is by design allowing you to pass values of other types and thus "widen" the resulting type.
You can, of course, constrain the type B to be B <: Animal

def add[B >: A <: Animal](element: B): MyList[B]

but that would defeat the purpose, because the add method is now only applicable to Animals regardless of what kind of list you use.

This design comes from a compiler restriction. The natural way of writing this method would be to say
`def add(element: A): MyList[A]`
and just be done with it. However, the compiler forbids that. Here's why. If we have the list covariant, that means I can say
`val animals: MyList[Animal] = new MyList[Dog]`
and now, because animals is a list of Animals, I can safely add an animal to it:
`animals.add(new Cat)`
which breaks the compiler guarantee that the list must hold Dogs only (from the construction phase).
So to please the compiler, we need to allow it to widen the type. In this way, animals.add(new Cat) returns a list of Animals and
the compiler is free from the previous constraint that it must hold dogs only.
As an unwanted adverse effect, we can also do animals.add(5) and the result type is a list of Any because there's nothing preventing the compiler from allowing that.
 */

  // Answer : `def add[B >: A <: Animal](element: B): MyList[B]`
}

/* Takeaways
- Use the same code on many (potentially unrelated) types
```scala
trait List[T] {
  def add(elem: T)
}
```
- Generic methods
```scala
object List {
  def single[A](element: A): List[A] = ???
}
```
- Multiple type parameters

- important problem in type systems:
**Variance: if B extends A, should List[B] extend List[A]?**
- `trait List[+A]` yes, covariant
- `trait List[A]`  no, invariant - default
- `trait List[-A]` hell no, contravariant

- Bounded types
```scala
class Car
class Supercar extends Car
class Garage[T <: Car](car T)
```

- An annoying variance problem
 */


/*
Hi Abhishek, this looks good to me. I wanted to show you something that's closer to how the real implementation is made.
Don't worry, you can also continue with this version and skip the **type system tricks** (B >: A]) I'm adding in the video to make covariance work.


```scala
abstract class MyList[A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add(element: A): MyList[A]
  def printElements: String
  override def toString: String = s"[$printElements]"
}

class Empty[A] extends MyList[A] {
  def head: A = throw new NoSuchElementException
  def tail: MyList[A] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(element: A): MyList[A] = new Cons(element, new Empty[A])
  def printElements: String = ""
}

class Cons[A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add(element: A): MyList[A] = new Cons(element, this)
  def printElements: String =
    if (t.isEmpty) s"${h.toString}"
    else s"$h ${t.printElements}"
}

object ListTest extends App {
  val listOfInts = new Cons(1, new Cons(2, new Cons(3, new Empty[Int])))
  println(listOfInts.tail.tail.head)
  println(listOfInts.add(4).head)
  println(listOfInts)

  val listOfStrings = new Cons("str1", new Cons("str2", new Cons("str3", new Empty[String])))
  println(listOfStrings.tail.tail.head)
  println(listOfStrings.add("str4").head)
  println(listOfStrings)

  val emptyListOfInts = new Empty[Int]
  val emptyListOfStrings = new Empty[String]
  println(emptyListOfInts.add(9))
  println(emptyListOfStrings.add("Pete"))
}
```
 */
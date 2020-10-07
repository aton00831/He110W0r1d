
# 
rock-the-jvm-scala-for-beginners

## 2 Basics
### [Values, Variables and Types](./src/lectures/part1basics/ValuesVariablesTypes.scala)
- prefer vals over vars
- all vals and vars have types
- compiler automatically infer types when omitted
- basic types
  - Boolean
  - Int, Long, Double
  - String

a func with __side effects__ = it does more than just return a value
e.g. modifying a variable is a side effect

**side effect**: println(), while, re-assigning

### [Expressions](./src/lectures/part1basics/Expressions.scala)
- instructions are executed (think JAVA, DO)
- expressions are evaluated (Scala, VALUE)
- `if` instruction (statement) in Scala is an expression 
- in Scala we'll think in terms of expressions
- Codeblock is expression, Value of block is value of last expression
- value inside codeblock does not visible outside
- DO NOT USE WHILE LOOP in Scala Code
- Unit === void, don't return anything meaningful

### [Functions](./src/lectures/part1basics/Funtions.scala)
- `def aFunction(a: paraType=defaultValue, ...): returnType = {}`
- NO LOOP -> Function Instead (Recursive)
- WHEN YOU NEED LOOPS, USE RECURSION.
- Could Not inference return type in recursive
- function can be called without ()

### [Recursion](./src/lectures/part1basics/Funtions.scala)
- WHEN YOU NEED LOOPS, USE _TAIL_ RECURSION.
- Any function could become tail recursive -> trick: accumulate
- annotation `@tailrec` import from `import scala.annotation.tailrec`

> In Scala 2.8, you will also be able to use the new @tailrec annotation to get information about which methods are optimised.
> 
> This annotation lets you mark specific methods that you hope the compiler will optimise.
> 
> You will then get a warning if they are not optimised by the compiler.
>
> In Scala 2.7 or earlier, you will need to rely on manual testing, or inspection of the bytecode, to work out whether a method has been optimised.

### [Call-by-Name and Call-by-Value](./src/lectures/part1basics/CBNvsCBV.scala)
- function pass literally (called by Named)! WITH =>
```scala
def calledByName(x: => Long): Unit = {
  println("by name: " + x) // by name: 292800423632974
  println("by name: " + x) // by name: 292800423660184
}
```
- Call by value: `varName : varType`
    - value is computed before call, 
    - same value used everywhere

- Call by name: `varName :=> varType`
    - expression is passed literally
    - expression is evaluated at every use within
    - extremely useful in lazy streams and file (e.g. try)
- CBN is not pass first-class function, first-class function don't solve the deferred evaluation

### [Default and Named Arguments](./src/lectures/part1basics/DefaultArgs.scala)
- side effect: work with different order

### [Strings' operations](./src/lectures/part1basics/StringOp.scala)
- Scala-specific: String interpolator
    - S-interpolators (similar to python f-string `s"$name"`)
    - F-interpolators (c printf style `f"$speed%2.2f"`)
    - raw-interpolator (no escape `raw"\n"`), not work in second layer
- `+:` prepending, `:+` appending

## 3 Object-Oriented Programming
### [OOP Basics](./src/lectures/part2oop/OOBasics.scala) & [OOP Basics Exercises](./src/lectures/part2oop/OOBasicsExercises.scala)
- Defining classes `class Person(name: String, age: Int)` constructor
- without val, class parameters are NOT FIELDS (just parameters)
```scala
class Novel(name: String) {
    def XXX = println(this.name) # PASS
}
val novel = new Novel("Great Expectations")
novel.name # FAILED

```
- Instantiating `val bob = new Person("bob")`
- Defining method `def greet(): String = {...}`
- Calling method `val bobSayHi = bob.greet`  (Syntax allowed for parameter-less method)
- Overloading constructor `def this(..)`

### [Method Notations](./src/lectures/part2oop/MethodNotations.scala)
- ALL OPERATORS ARE METHODS !!

1. Infix notation - only used for methods with one parameter
    - `mary.likes("Inception)`
    - `mary likes "Inception`
    - so nature language!
    
2. prefix notation - only allowed for +,-,!,~
    - `mary.unary_!`
    - `!mary`

3. Postfix notation - only used for method with no parameter
    - `import scala.language.postfixOps`
    - `mary.isAlive`
    - `mary isAlive`

**!!! not recommend to use, it cause confusion when reading code to solve this, scala force to open flag to use**

4. apply method is special, allow you to call your object like functions
    - `mary.apply("Hi there")`
    - `mary("Hi there")`
    - this breaks that barrier between OOP & FP
    
### [Objects](./src/lectures/part2oop/Objects.scala)
- SCALA DOES NOT HAVE CLASS_LEVEL FUNCTIONALITY ("static" values or methods)
- Scala object = SINGLETON INSTANCE, definition = type + its only instance
    - are in their own class 
    - are the only instance
    - singleton pattern in one line
- Scala Companions
    - can access each other's private member
    - COMPANIONS Pattern (Object, class the same name)    
- Scala Applications = Scala object with `def main(args: Array[String]): Unit`
- every usable code belongs to some instance of a class. 
(In Python or C++, you can write top-level functions and variables
 that don't belong to a class or object.)

#### [Scala Object vs Class](https://stackoverflow.com/questions/1755345/difference-between-object-and-class-in-scala)
- `class C` defines a class, just as in Java or C++.
- `object O` creates a singleton object `O` as instance of some anonymous class; it can be used to hold static members that are not associated with instances of some class.
- `object O extends T` makes the object `O` an instance of `trait T`; you can then pass `O` anywhere, a `T is expected.
- if there is a `class C`, then `object C` is the __companion object__ of class `C`; note that the companion object is not automatically an instance of `C`.
- An object has exactly one instance (you can not call new MyObject). You can have multiple instances of a class.
- In specific definition, object serves the same (and some additional) purposes as the static methods and fields in Java.

### [Inheritance](./src/lectures/part2oop/inheritance.scala)
- overRIDING vs overLOADING
    - overRIDING: supply different implementation in derived classes
    - overLOADING : supply multiple method with different signatures, but with the same name in the same class
    
- super: referent a method or field from parent class
- preventing overrides
    - use final on member
    - use final on the entire class (class can't be extended)
    - `sealed class Anima {` sealed the class  = extend classes in THIS FILE, prevent extensions in other files (soft restriction)
- numerical classes and string type are final

### [Abstract Data Types](./src/lectures/part2oop/AbstractDataTypes.scala)
```scala
abstract class Anima
class Dog extends Animal
trait Carnivore {}
trait ColdBlooded {}
class Crocodile extends Animal with Carnivore with ColdBlooded {}
```
- contains unimplemented or abstract field, methods
- in derived class, non-abstract must use override
- abstract classes can have both abstract and non-abstract member, so can traits
- traits, ultimate abstract data type in scala
    - traits do not have constructor parameters
    - multiple traits may be inherited by the same class
    - traits = behavior (more subtle choice)
    - abstract class = things

- Scala's Type Hierarchy
    - `scala.Any` (mother of all types)

    - scala.Any <--- `scala.AnyRef` (map to java.lang.Object)
        - All classes you'll use will derive from AnyRef
        - unless you explicitly say they extend some other class
        - e.g. String, List, Set,
        - all user derived classes even not explicitly say so like class person (actually extends AnyRef)

    - scala.AnyRef <--- `scala.Null` (basically means no reference) so you can replace person with no reference

    - scala.Any <--- scala.AnyVal e.g. Int, Unit, Boolean, Float
        - very early used in practice
        - contain primitive type
        - (maybe for some memory optimizations)

    - scala.Any, scala.Null <--- scala.Nothing
        - nothing can replace everything!!
        - is a subtype of every single thing
        - make sense in exceptions and expression returning


### [Practice: LinkedList](./src/exercises/MyList.scala)
- focus on `immutable data structure`
- `def add(element: Int): MyList = new Cons(element, Empty)` immutable list
- `val head: Int = ???` return nothing, throw not implemented error
- `val head: Int = throw new NoSuchElementException` Throw expression are expressions which return nothing
- POLYMORPHIC CALL
-  toString is method in AnyRef Class

... keep Going

## 4 Functional Programming

## 5 Pattern Matching

## 6 Practice: A Small File System



## Great Reference 
- [Scala Learning Tips](https://medium.com/javarevisited/why-java-developer-should-learn-scala-programming-in-2020-3ca01a47eb0d)
- [Algorithm in Scala](https://github.com/TheAlgorithms/Scala)
- [awesome-scala](https://github.com/lauris/awesome-scala)
- [awesome-scalability](https://github.com/binhnguyennus/awesome-scalability)
- [is scala dead?](https://www.lihaoyi.com/post/TheDeathofHypeWhatsNextforScala.html)


> Scala lacks in tooling and compatibility and doesn't integrate with Spring and other tools as well as Groovy does.

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
- [OOP Basics](./src/lectures/part2oop/OOBasics.scala)
- [OOP Basics Exercises](./src/lectures/part2oop/OOBasicsExercises.scala)
- [Method Notations](./src/lectures/part2oop/MethodNotations.scala)
- [Objects](./src/lectures/part2oop/Objects.scala)
- [Inheritance](./src/lectures/part2oop/inheritance.scala)

- [Practice: LinkedList](./src/exercises/MyList.scala)

... keep Going

## 4 Functional Programming

## 5 Pattern Matching

## 6 Practice: A Small File System


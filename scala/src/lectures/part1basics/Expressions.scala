package lectures.part1basics

object Expressions extends App {
  // Extends App: make it runnable in intellij

  val x = 1 + 2 // right hand side: EXPRESSION
  println(x)

  println(2 + 3 * 4)
  // + - * /
  // & | ^ (exclusive or)
  // << >> >>> (right shift with zero extension)

  println( 1 == x)
  // == != > >= < <=

  println(!(1 == x))
  // ! && ||

  var aVariable =  2
  aVariable += 3 // also works with -= *= /= ... side effects
  println(aVariable)

  // Instructions (DO) vs *Expressions (VALUE)
  // In Scala, we think in expression
  // IF expression
  val aCondition = true
  val aContionedValue = if(aCondition) 5 else 3 // IF EXPRESSION (not IF Statement)
  println(aContionedValue)
  println(if(aCondition) 5 else 3)
  println(1 + 3)
  // IF in scala is expression !! ?

  var i = 0
  var aWhile = while (i<10) { // side Effect
    println(i)
    i += 1
  }
  // !! NEVER WRITE THIS AGAIN. (no WHILE LOOP)
  // EVERYTHING in Scala is an Expression!
  val aWeirdValue = (aVariable = 3) // Unit === void, don't return anything meaningful
  println(aWeirdValue) // (), side-effect return Unit

  // **side effect: println(), while, re-assigning

  // Code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }
  // Codeblock is expression
  // Value of block is value of last expression

  // val anotherValue = z + 1 // not visible outside


  /* Takeaway:
  - Basic expressions: operators
  - If in scala is an expression
  - Code block in Scala are expressions
  (the value of the block is the value of its last expression)
  - Expressions vs. instructions
  instructions are executed (think JAVA),
  expressions are evaluated (Scala)
  in Scala we'll think in terms of expressions

  !!! DO NOT use while loops in your Scala code
  or I'll haunt you.

   I do not use the term "statement" in the lecture.
   I use the terms "instruction" and "expression" to
   help you make the difference between
   the imperative vs functional thinking.

  My advice is:
  ignore the term "statement" because it's very loaded.
  Instead, think in terms of what Scala has - expressions.

   */

  // 1. difference between "hello world" va println("hello world") ?
  "hello word" // a value of type string, string literal
  println("hello world") // expression, side-effect because return Unit, print to console
  // 2.
  val someValue = {
    2 < 3
  } // False

  val someOtherValue = {
    if (someValue) 239 else 986
    42
  } // Int ?
  println(someValue)
  println(someOtherValue)
}

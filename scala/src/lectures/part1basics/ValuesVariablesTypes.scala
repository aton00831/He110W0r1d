package lectures.part1basics

object ValuesVariablesTypes extends  App {
  /* types */
  val x: Int = 42
  println(x)

  // x = 2 val cannot be reassign
  // VALS ARE IMMUTABLE

  val y = 42
  println(y)

  // Type is optional
  // * COMPILER can infer types

  val aString: String = "hello";
  // string must be double quote
  // semi-column are allowed but not necessary
  // multiple expresseion on same line is bad style

  val aBoolean: Boolean = true // or false
  val aChar: Char = 'a' // single quote
  var aInt: Int = x // 4-bytes
  val aShort: Short = 4613 // 2-bytes
  val aLong: Long = 123123123123123L // 8-bytes, capital L for literal
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.1415161111111111

  /* variables */
  var aVariable: Int = 4
  aVariable += 1
  aVariable = 5 // mutable, side effects

  /* Takeaway
  - prefer vals over vars
  - all vals and vars have types
  - compiler automatically infer types when omitted
  - basic types
    - Boolean
    - Int, Long, Double
    - String

  a func with side effects = it does more than just return a value
  e.g. modifying a variable is a side effect
   */


}

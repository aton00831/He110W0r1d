package lectures.part3fp

object Collections {
  /* Scala offers both mutable and immutable collections:
  - mutable collections can be update in place
  - immutable collections never change

  Scala standard library has type definition
  or type aliases for immutable collections :

```scala
package object scala {
  type List[+A] = immutable.List[A]
}
```

```scala
object Predef {
  type Map[A, +B] = immutable.Map[A, B]
  type Set[A]     = immutable.Set[A]
}
```
  => We're USING THE IMMUTABLE collections by default

   */

  /* Immutable Collections
  Immutable collections are found in
  `scala.collections.immutable` package

  [Traversable] <- [Iterable]
    [Iterable] <- [Set]
      [Set] <- [HashSet]
            <- [SortedSet] // TODO: What's Different?
    [Iterable] <- [Map]
      [Map] <- [HashMap]
            <- [SortedMap]
    [Iterable] <- [Seq]
      [Seq] <- [IndexedSeq]
        [IndexedSeq] <- [Vector]
        [IndexedSeq] <- [String]
        [IndexedSeq] <- [Range]
      [Seq] <- [LinearSeq]
        [LinearSeq] <- [List]
        [LinearSeq] <- [Stream]
        [LinearSeq] <- [Stack]
        [LinearSeq] <- [Queue]

   */

  /* Mutable Collections
    Mutable collections are found in
    `scala.collections.mutable` package

    [Traversable] <- [Iterable]
      [Iterable] <- [Set]
        [Set] <- [HashSet]
              <- [LinkedHashSet] // *
      [Iterable] <- [Map]
        [Map] <- [HashMap]
              <- [MultiMap]
      [Iterable] <- [Seq]
        [Seq] <- [IndexedSeq]
          [IndexedSeq] <- [StringBuffer]
        [Seq] <- [Buffer]
          [IndexedSeq] <-
          [Buffer]     <- [ArrayBuffer]
          [Buffer]     <- [ListBuffer]
        [Seq] <- [LinearSeq]
          [LinearSeq] <- [LinkedList]
          [LinearSeq] <- [MutableList]
     */

  /* Traversable
  Base trait for all collections. Offers a great variety of methods:
  - maps        : map, flatMap, collect
  - conversions : toArray, toList, toSeq
  - size info   : isEmpty, size, nonEmpty
  - tests       : exists, forall
  - folds       : foldLeft, foldRight, reduceLeft, reduceRight
  - retrieval   : head, find, tail
   */

}


package lectures.part2oop

// import the package
import lectures.part2oop.OOBasicsExercises.Writer
// import playground.{Cinderella, PrinceCharming}
// import playground._ // import all
import playground.{Cinderella => Princess, PrinceCharming} // alias solve naming conflicts

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {
  // Packaging: a bunch of definitions grouped under the same name
  // 99% time, match directories structure

  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "EockTheJVM", 2018)

  // name either in the same package or in imported package
  // or fully qualify name
  val novel = new lectures.part2oop.OOBasicsExercises.Novel("Tony", 1234, writer)

  // packages are order hierarchy (dot notation)
  // matching folder structure.

  // Scala specific:
  // PACKAGE OBJECT
  // This originated from the problem that sometimes
  // we may want to write methods or constants outside of basically everything else
  // universal constants or universal methods that reside outside classes
  // RIGHT CLICK on Package folder and choose PACKAGE OBJECT
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val princess = new Princess
  val prince = new PrinceCharming

  // Naming conflict in import
  val d = new Date // Assume the first import
  // Option1, use fully qualify name
  // val date = new Date
  // val sqlDate = new java.sql.Date(2018,5,4)

  // Option2,
  // .(Date => SqlDate)
  val d2 = new SqlDate(2018,5,4)

  // Default imports
  // packages that are automatically imported
  // without any intentional import from your side

  // java.lang - String, Object, Exception ...
  // scala - Int, Nothing, Function, ...
  // scala.Predef - println, ??? ...

}

/*
package = a group of definitions under the same name
To use a definition
- be in the same package
- or import the package
Best practice - mirror the file structure
fully qualified name
package objects hold standalone methods/ constants
- one per package
name aliasing at imports

 */

package lectures.part3fp

object HOFsCurries extends App {

  val superFunction: (Int, (String, (Int =>Boolean))=>Int)=>(Int=>Int) = ???

}

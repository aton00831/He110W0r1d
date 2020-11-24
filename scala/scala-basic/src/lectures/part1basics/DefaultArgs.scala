package lectures.part1basics

object DefaultArgs extends App {

  def trFact(n: Int, acc: Int = 1): Int ={
    if (n <= 1) acc
    else trFact(n-1, n*acc)
  }

  val fact10 = trFact(10)
  val fact11 = trFact(11) // always 1, set default value

  // v function signature
  def savePicture(format: String = "jpeg", width: Int =1920, Height: Int=1080): Unit = println("saving picture")
  // WRONG: savePicture(800, 600)
  savePicture(width = 800, Height = 600)
  /*
    1. pass in every leading argument
    savePicture("jpeg", 800, 600)

    2. named the arguments
    savePicture(width = 800, Height = 600)

    side effect: work with different order
    savePicture(Height = 600, width = 800)
   */
}

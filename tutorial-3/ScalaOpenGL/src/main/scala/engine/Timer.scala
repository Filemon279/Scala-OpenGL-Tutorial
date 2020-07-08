package engine

object Timer {

  var lastLoopTime = .0f
  var lastElapsedTime = .0f

  def init(): Unit = {
    lastLoopTime = time()
  }

  def time(): Float = System.nanoTime / 1000_000_000.0f

  def elapsedTime: Float = {
    val t = time()
    lastElapsedTime = t - lastLoopTime
    lastLoopTime = t
    lastElapsedTime
  }

}

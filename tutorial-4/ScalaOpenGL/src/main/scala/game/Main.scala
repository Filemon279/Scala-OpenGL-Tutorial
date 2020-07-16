package game

import engine.GameLoop

object Main {

  def main(args: Array[String]): Unit = {

    val width: Int = 1280
    val height: Int = 720
    val title: String = "Scala & OpenGL"
    val vSync: Boolean = true

    val gameEngine = new GameLoop(title, width, height, vSync)
    gameEngine.run()
  }

}

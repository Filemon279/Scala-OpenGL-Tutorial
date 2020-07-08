package engine

import engine.window.Window
import game.ScalaGame
import org.lwjgl.glfw.GLFW.glfwWindowShouldClose

class GameLoop(windowTitle: String, width: Int, height: Int, vSync: Boolean) {

  private val FRAME_PER_SEC = 60
  private val UPDATES_PER_SEC = 60

  private val window = new Window(windowTitle, width, height, vSync)
  private val scalaGame = new ScalaGame()

  def run(): Unit = {
    init()
    gameLoop()
  }

  def init(): Unit = {
    Timer.init()
    scalaGame.init(window)
  }

  private def gameLoop(): Unit = {
    var elapsedTime: Float = .0f
    var accumulator: Float = 0f
    val interval = 1f / UPDATES_PER_SEC
    while (!glfwWindowShouldClose(window.windowHandle)) {
      elapsedTime = Timer.elapsedTime
      accumulator += elapsedTime
      input()
      while (accumulator >= interval) {
        update(interval)
        accumulator -= interval
      }
      render()
      if (!window.vSync) sync()
    }
    window.cleanup()
  }

  private def sync(): Unit = {
    val syncStep = 1f / FRAME_PER_SEC
    val endTime = Timer.lastLoopTime + syncStep
    while (Timer.time < endTime) Thread.sleep(1)
  }

  private def input(): Unit = {
    scalaGame.input()
  }

  private def update(interval: Float): Unit = {
    scalaGame.update(interval)
  }

  private def render(): Unit = {
    scalaGame.render(window)
    window.update()
  }
}

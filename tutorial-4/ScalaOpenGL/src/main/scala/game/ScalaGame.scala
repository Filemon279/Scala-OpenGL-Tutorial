package game

import engine.input.Keyboard
import engine.render.Renderer
import engine.window.Window
import org.lwjgl.glfw.GLFW._

class ScalaGame {

  private var offset = 0.0f
  private val renderer = new Renderer()

  def init(window: Window): Unit = {
    renderer.init()
    Keyboard.init(window.windowHandle)
  }

  def input(): Unit = {
    if (Keyboard.isKeyPressed(GLFW_KEY_SPACE)) offset += 0.01f
  }

  def update(interval: Float): Unit = {

  }

  def render(window: Window): Unit = {
    window.setClearColor((0.2f + offset)%1, (0.5f + offset)%1, (0.7f + offset)%1, 0.0f)
    renderer.render(window)
  }
}

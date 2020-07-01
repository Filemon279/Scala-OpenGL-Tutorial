package engine.render

import engine.window.Window
import org.lwjgl.opengl.GL11.{GL_COLOR_BUFFER_BIT, GL_DEPTH_BUFFER_BIT, glClear, glViewport}

class Renderer() {

  def init(): Unit = {

  }

  def render(window: Window) = {
    clear()
    handleResize(window)
  }

  private def clear(): Unit = {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
  }

  private def handleResize(window: Window) = {
    if (window.resized) {
      glViewport(0, 0, window.width, window.height)
      window.resized = false
    }
  }

}
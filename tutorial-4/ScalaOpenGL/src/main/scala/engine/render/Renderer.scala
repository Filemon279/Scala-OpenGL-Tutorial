package engine.render

import engine.meshes.Mesh
import engine.window.Window
import org.lwjgl.opengl.GL11.{GL_COLOR_BUFFER_BIT, GL_DEPTH_BUFFER_BIT, glClear, glViewport}

class Renderer() {

  var triangleMesh: Option[Mesh] = None

  def init(): Unit = {
    val vertices = Array[Float](
      0.5f, 0.5f, 0.0f,
      -0.5f, -0.5f, 0.0f,
      -0.5f, 0.5f, 0.0f,
      0.5f, -0.5f, 0.0f)

    val indices = Array[Int](0, 1, 2, 0, 1, 3)

    triangleMesh = Some(new Mesh(vertices, indices))
  }

  def render(window: Window) = {
    clear()
    handleResize(window)

    triangleMesh.map(_.shaderProgram.bind)
    triangleMesh.map(_.render())
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
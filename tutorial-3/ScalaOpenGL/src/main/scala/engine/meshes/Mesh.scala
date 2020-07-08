package engine.meshes

import engine.ShaderManager
import org.lwjgl.opengl.GL11.{GL_TRIANGLES, glDrawArrays}
import org.lwjgl.opengl.GL30.glBindVertexArray

import scala.io.Source

class Mesh(val positions: Array[Float]) extends RawMesh {

  vaoLoader.loadToVAO(positions, 0)
  vaoId = vaoLoader.vaoId

  val shaderProgram = new ShaderManager()
  shaderProgram.createVertexShader(Source.fromResource("shaders/vertex.vs").mkString)
  shaderProgram.createFragmentShader(Source.fromResource("shaders/fragment.fs").mkString)
  shaderProgram.link

  def initRender() = {
    glBindVertexArray(vaoId)
  }

  def render() = {
    initRender()
    glDrawArrays(GL_TRIANGLES, 0, positions.size)
    endRender()
  }

  def endRender() = {
    glBindVertexArray(0)
  }

  def cleanup(): Unit = {
    vaoLoader.cleanUp()
  }

}

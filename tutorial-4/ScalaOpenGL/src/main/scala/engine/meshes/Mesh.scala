package engine.meshes

import engine.ShaderManager
import org.lwjgl.opengl.GL11.{GL_TRIANGLES, GL_UNSIGNED_INT, glDrawArrays, glDrawElements}
import org.lwjgl.opengl.GL30.glBindVertexArray

import scala.io.Source

class Mesh(val positions: Array[Float], val indices: Array[Int]) extends RawMesh {

  vaoId = vaoLoader.vaoId
  vaoLoader.loadToVAO(positions, 0)
  vaoLoader.loadToVAO(indices)

  val shaderProgram = new ShaderManager()
  shaderProgram.createVertexShader(Source.fromResource("shaders/vertex.vs").mkString)
  shaderProgram.createFragmentShader(Source.fromResource("shaders/fragment.fs").mkString)
  shaderProgram.link

  def initRender() = {
    glBindVertexArray(vaoId)
  }

  def render() = {
    initRender()
    glDrawElements(GL_TRIANGLES, indices.size, GL_UNSIGNED_INT, 0)
    endRender()
  }

  def endRender() = {
    glBindVertexArray(0)
  }

  def cleanup(): Unit = {
    vaoLoader.cleanUp()
  }

}

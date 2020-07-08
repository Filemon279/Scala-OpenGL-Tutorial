package engine

import org.lwjgl.opengl.GL11.GL_FLOAT
import org.lwjgl.opengl.GL15.{GL_ARRAY_BUFFER, GL_STATIC_DRAW, glBindBuffer, glBufferData, glDeleteBuffers, glGenBuffers}
import org.lwjgl.opengl.GL20.{glDisableVertexAttribArray, glEnableVertexAttribArray, glVertexAttribPointer}
import org.lwjgl.opengl.GL30.{glBindVertexArray, glDeleteVertexArrays, glGenVertexArrays}
import org.lwjgl.system.MemoryUtil
import scala.collection.mutable.Set

class VaoLoader {

    var vboIdList: Set[Int] = Set()
    val vaoId = createVAO()

    def loadToVAO(data: Array[Float], locationIndex: Int, size: Int = 3) = {
      storeDataInVBO(data, locationIndex, size)
    }

    private def storeDataInVBO(data: Array[Float], locationIndex: Int, size: Int) = {
      val vboId = glGenBuffers()
      vboIdList.add(vboId)

      val buffer = MemoryUtil.memAllocFloat(data.length)
      buffer.put(data).flip()

      glBindBuffer(GL_ARRAY_BUFFER, vboId)
      glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW)
      glEnableVertexAttribArray(locationIndex)
      glVertexAttribPointer(locationIndex, size, GL_FLOAT, false, 0, 0)
      if (buffer != null) MemoryUtil.memFree(buffer)
    }

    private def createVAO(): Int = {
      val vaoId = glGenVertexArrays()
      glBindVertexArray(vaoId)
      vaoId
    }

    def cleanUp() = {
      glBindBuffer(GL_ARRAY_BUFFER, 0)
      glDisableVertexAttribArray(0)
      vboIdList.map(glDeleteVertexArrays(_))
      glDeleteBuffers(vaoId)
      unbindVAO()
    }

    def unbindVAO() = {
      glBindVertexArray(0)
    }

  }

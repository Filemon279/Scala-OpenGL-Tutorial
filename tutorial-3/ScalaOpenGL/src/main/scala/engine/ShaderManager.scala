package engine

import org.lwjgl.opengl.GL20._

class ShaderManager {

  private var vertexShaderId: Int = 0
  private var fragmentShaderId: Int = 0

  private final val programId = glCreateProgram();
  require(programId != 0, "Shader has not been created")

  def createVertexShader(shaderSourceCode: String) = vertexShaderId = createShader(shaderSourceCode, GL_VERTEX_SHADER)

  def createFragmentShader(shaderSourceCode: String) = fragmentShaderId = createShader(shaderSourceCode, GL_FRAGMENT_SHADER)

  def createGeometryShader(shaderSourceCode: String) = ???

  private def createShader(shaderSourceCode: String, shaderTypeCode: Int): Int = {
    val shaderId = glCreateShader(shaderTypeCode)
    assert(shaderId != 0, s"Error creating $shaderTypeCode shader")
    glShaderSource(shaderId, shaderSourceCode)
    glCompileShader(shaderId)
    assert(glGetShaderi(shaderId, GL_COMPILE_STATUS) != 0, s"Shader compiling error: ${glGetShaderInfoLog(shaderId, 1024)}")
    glAttachShader(programId, shaderId)
    shaderId
  }

  def link() = {
    glLinkProgram(programId)
    if (glGetProgrami(programId, GL_LINK_STATUS) equals 0) throw new Exception(s"Shader linking error: ${glGetProgramInfoLog(programId, 1024)}")
    if (!vertexShaderId.equals(0)) glDetachShader(programId, vertexShaderId)
    if (!fragmentShaderId.equals(0)) glDetachShader(programId, fragmentShaderId)
  }

  def bind() = {
    glUseProgram(programId)
  }

  def unbind() = {
    glUseProgram(0)
  }

  def cleanup() = {
    unbind()
    if (!programId.equals(0)) glDeleteProgram(programId)
  }


}

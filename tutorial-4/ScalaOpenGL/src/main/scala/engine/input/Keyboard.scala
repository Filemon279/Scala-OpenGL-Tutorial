package engine.input

import org.lwjgl.glfw.GLFW.{GLFW_PRESS, GLFW_RELEASE, glfwGetKey}

object Keyboard {

  private var windowHandler: Long = 0

  def init(windowHandle: Long) = windowHandler = windowHandle

  def isKeyPressed(keyCode: Int): Boolean = glfwGetKey(windowHandler, keyCode).equals(GLFW_PRESS)
  def isKeyReleased(keyCode: Int): Boolean = glfwGetKey(windowHandler, keyCode).equals(GLFW_RELEASE)

}

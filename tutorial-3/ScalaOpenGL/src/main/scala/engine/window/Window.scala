package engine.window

import org.lwjgl.glfw.GLFW.{GLFW_KEY_ESCAPE, glfwDestroyWindow, GLFW_RELEASE, glfwCreateWindow, glfwSwapInterval, glfwSetFramebufferSizeCallback, glfwInit, glfwMakeContextCurrent, glfwPollEvents, glfwSetErrorCallback, glfwSetKeyCallback, glfwSetWindowShouldClose, glfwSwapBuffers, glfwTerminate, glfwWindowHint, glfwWindowShouldClose}
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.{glClearColor}
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks

class Window(title: String, var width: Int, var height: Int, val vSync: Boolean) {

  GLFWErrorCallback.createPrint(System.err).set
  glfwInit()

  var resized: Boolean = false

  val windowHandle = glfwCreateWindow(width, height, title, NULL, NULL)

  glfwMakeContextCurrent(windowHandle)
  GL.createCapabilities()

  glfwSetFramebufferSizeCallback(windowHandle, (_, width, height) => {
    this.width = width
    this.height = height
    resized = true
  })

  glfwSetKeyCallback(windowHandle, (window, key, _, action, _) => {
    if (key.equals(GLFW_KEY_ESCAPE) && action.equals(GLFW_RELEASE)) glfwSetWindowShouldClose(window, true)
  })

  if(vSync) glfwSwapInterval(1) else glfwSwapInterval(0)

  glClearColor( 0, 0, 0, 0)

  def update() = {
    glfwSwapBuffers(windowHandle)
    glfwPollEvents();
  }

  def setClearColor(r: Float, g: Float, b: Float, a: Float) = {
    glClearColor(r, g, b, a)
  }

  def cleanup() = {
    glfwFreeCallbacks(windowHandle)
    glfwDestroyWindow(windowHandle)
    glfwSetErrorCallback(null).free();
    glfwTerminate()
  }

}

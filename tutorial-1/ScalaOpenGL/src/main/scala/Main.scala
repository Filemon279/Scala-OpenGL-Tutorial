import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW._
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11._

object Main {

  def main(args: Array[String]): Unit = {

    glfwInit()
    GLFWErrorCallback.createPrint(System.err).set

    val width: Int = 1280
    val height: Int = 720
    val title: String = "Scala & OpenGL"
    glfwWindowHint(GLFW_DECORATED, GL_TRUE)
    glfwWindowHint(GLFW_RESIZABLE, GL_FALSE)

    val windowHandle = glfwCreateWindow(width, height, title, NULL, NULL)

    glfwMakeContextCurrent(windowHandle)
    GL.createCapabilities()

    var offset: Float = 0

    glfwSetKeyCallback(windowHandle, (window, key, _, action, _) => {
      if (key.equals(GLFW_KEY_ESCAPE) && action.equals(GLFW_RELEASE)) glfwSetWindowShouldClose(window, true)
      if (key.equals(GLFW_KEY_SPACE)) offset += 0.01f
    })
    while(!glfwWindowShouldClose(windowHandle)){
        glClearColor( (0.2f + offset)%1, (0.5f + offset)%1, (0.7f + offset)%1, 0.0f)
        glClear(GL_COLOR_BUFFER_BIT)
        glfwSwapBuffers(windowHandle)
        glfwPollEvents();
    }

    glfwFreeCallbacks(windowHandle)
    glfwDestroyWindow(windowHandle)
    glfwSetErrorCallback(null).free();
    glfwTerminate()

  }

}

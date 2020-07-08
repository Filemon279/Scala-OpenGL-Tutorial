name := "ScalaOpenGL"

version := "0.1"

scalaVersion := "2.13.2"

libraryDependencies ++= {
  val version = "3.2.3"
  val os = System.getProperty("os.name").toLowerCase match {
    case win if win.contains("win") => "windows"
    case linux if linux.contains("linux") => "linux"
    case mac if mac.contains("mac") => "macos"
  }


  Seq("lwjgl", "lwjgl-stb", "lwjgl-glfw", "lwjgl-opengl").flatMap(lwjglDependency => {
      Seq(
        "org.lwjgl" % lwjglDependency % version,
        "org.lwjgl" % lwjglDependency % version classifier s"natives-$os"
      )
    })
}

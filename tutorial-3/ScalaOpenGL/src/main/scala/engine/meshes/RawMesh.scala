package engine.meshes

import engine.VaoLoader

trait RawMesh {

  val positions: Array[Float]

  def render()

  def cleanup()

  val vaoLoader = new VaoLoader()

  var vaoId = vaoLoader.vaoId

}

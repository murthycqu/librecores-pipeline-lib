package org.librecores.ci
class Modules implements Serializable {
  def steps
  Modules(steps) {this.steps = steps}
  
  String[] modulesToLoad = []
  
  def load(String[] modules) {
     for (int i = 0; i < modules.size(); ++i) {
        String module = modules[i]
        steps.sh "module load ${module}"
     }
  }
}

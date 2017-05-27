package org.librecores.ci
class Modules implements Serializable {
  def steps
  Modules(steps) {this.steps = steps}
  
  def modulesToLoad = []
  
  def load(modules) {
    if (modules instanceof String) {
        modules = [modules]
    }
    modules.each {
      steps.sh "source /usr/share/modules/init/bash && /usr/bin/modulecmd bash avail ${it} 2> module-avail"
      def output = steps.readFile('module-avail').trim()
      if (output.length() == 0) {
        steps.error "Module ${it} not found on this node"
      }
     }
    steps.echo modulesToLoad.size()
    modulesToLoad += modules
    steps.echo modulesToLoad.size()
  }
  
  def sh(command) {
     String toInvoke = command
     modulesToLoad.each {
        toInvoke = "module load ${it} && ${toInvoke}"
     }
    
     toInvoke = "source /usr/share/modules/init/bash && ${toInvoke}" 
     steps.sh "$toInvoke"
  }
}

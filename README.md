# LibreCores Jenkins Pipeline Library

This library contains common operations and logic for LibreCores CI.

## Status

The library is under development.
All commands may change, the compatibility is not guaranteed.

## Classes

### Modules

This is the class to more conveniently build CI based on Environment Modules (http://modules.sourceforge.net/). First modules are loaded to the environment and calling sh() then executes the command(s) in that environment.

Usage:

```groovy
@Library('librecoresci') import org.librecores.ci.Modules
def lcci = new Modules(steps)

node('librecores-ci-modules') {
  lcci.load(["eda/verilator/3.902"])

  stage('Simulation Build') {
    lcci.sh 'verilator -f verilate.vc'
  }
}
```

## Commands

### sh_with_modules()

Executes the `sh()` command with initializing the specified modules before executing.

Example:

```groovy
node('librecores-ci-modules') {
    sh_with_modules(modules: ["gcc", "fusesoc/1.6"], command: "make all")
}
```

Effectively the example above creates commands like `source ${modulesPath}/init/bash && sh "module load gcc && module load fusesoc/1.6 && make all"`.

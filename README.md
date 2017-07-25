# vodqa-gorilla-demo-load-simulator
basic production load simulator for gorrila demo

Start SBT
---------
```bash
$ sbt
```

Run all simulations
-------------------

```bash
> gatling:test
```

Run a single simulation
-----------------------

```bash
> gatling:testOnly simulators.BasicLoadSimulation
```

List all tasks
--------------------

```bash
> tasks gatling -v
```


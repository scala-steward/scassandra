# Scassandra [![Build Status](https://travis-ci.org/evolution-gaming/scassandra.svg)](https://travis-ci.org/evolution-gaming/scassandra) [![Coverage Status](https://coveralls.io/repos/evolution-gaming/scassandra/badge.svg)](https://coveralls.io/r/evolution-gaming/scassandra) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/7b5aa36e03bf4e84ad0abd019d3d587b)](https://www.codacy.com/app/evolution-gaming/scassandra?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=evolution-gaming/scassandra&amp;utm_campaign=Badge_Grade) [ ![version](https://api.bintray.com/packages/evolutiongaming/maven/scassandra/images/download.svg) ](https://bintray.com/evolutiongaming/maven/scassandra/_latestVersion) [![License: MIT](https://img.shields.io/badge/License-MIT-yellowgreen.svg)](https://opensource.org/licenses/MIT)

### Cassandra client in scala - wrapper over java client

## Example

```scala
import com.evolutiongaming.scassandra._
import com.evolutiongaming.scassandra.CassandraHelper._

val config = CassandraConfig.Default
val cluster = CreateCluster(config)
val name = for {
  session <- cluster.connect()
  resultSet <- session.execute("SELECT name FROM users")
} yield {
  val row = resultSet.one()
  row.decode[String]("name")
}
Await.result(name, 3.seconds)

``` 

## Setup

```scala
resolvers += Resolver.bintrayRepo("evolutiongaming", "maven")

libraryDependencies += "com.evolutiongaming" %% "scassandra" % "0.0.1"
```
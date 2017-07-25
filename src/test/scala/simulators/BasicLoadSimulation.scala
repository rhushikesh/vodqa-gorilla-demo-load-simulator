package simulators

import io.gatling.core.Predef._
import io.gatling.core.feeder.RecordSeqFeederBuilder
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration._

class BasicLoadSimulation extends Simulation {

  val httpConf: HttpProtocolBuilder = http
    .baseURL("http://bloggerbackendapi.rjspbamm3i.ap-south-1.elasticbeanstalk.com/") // Here is the root for all relative URLs

  val csvFeeder: RecordSeqFeederBuilder[String] = csv("blogPostDemoUrls.csv")


  val scn: ScenarioBuilder = scenario("demo")
    .feed(csvFeeder.random.circular)
    .forever(exec(http("blogPostRequests")
      .get("${blogPostsGet}")))


  setUp(scn.inject(
    rampUsers(500) over (10 minutes))
    .throttle(reachRps(100) in (10 seconds), holdFor(30 minutes))).protocols(httpConf)
}

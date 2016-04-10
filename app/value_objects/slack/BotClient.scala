package slack

object BotClient {
  import play.api._
  import play.api.mvc._
  import play.api.libs.ws._
  import play.api.libs.json._
  import scala.concurrent._
  import ExecutionContext.Implicits.global
  import scala.collection.mutable.HashMap
    
  val ApiToken = "xoxb-33367767187-YQs5VUiT65QtrtHEWW2eOHPv"
  val ApiRootUrl = "https://slack.com/api"
  val OAuthToken = "xoxp-33404313793-33392972214-33427046849-a5c5bd6eeb"
  val ChasmChanel = "#chasm"
  val ApiEndpoints = HashMap(
    "rtm" -> HashMap(
      "start" -> "rtm.start",
      "post_message" -> "chat.postMessage"
    )
  )


  def post_to_endpoint(endpoint: String, data: HashMap[String,String]) : scala.concurrent.Future[play.api.libs.ws.Response] =  {
    data("token") = ApiToken
    val json = Json.toJson(data.toMap)

    println(json.toString())

    var request = WS.url(ApiRootUrl + "/" + endpoint).post(json.toString())

    request onSuccess {
      case response => 
        println("success")
        println(response.json.toString())
    }

    return request
  }

  def start_rtm : scala.concurrent.Future[play.api.libs.ws.Response] = {
    val data = HashMap("token" -> ("client:" + ApiToken))
    post_to_endpoint(ApiEndpoints("rtm")("start"), data)
  }
}
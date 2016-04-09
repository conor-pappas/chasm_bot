package controllers

import play.api._
import play.api.mvc._
import play.api.libs.ws._
import play.api.libs.json._
import scala.concurrent._
import ExecutionContext.Implicits.global
import java.util.Calendar


object Application extends Controller {

  def index = Action {

    val data = Json.obj(
      "ok" -> "true"
    )
    val now = Calendar.getInstance().getTime()
    Logger.debug("starting request " + now)
    val request = WS.url("https://slack.com/api/api.test").post(data)


    request onSuccess {
      case response => 
        val finished = Calendar.getInstance().getTime()
        Logger.debug(response.json.toString())
        Logger.debug("finishing request " + finished)
    }


    Ok("Hello World")
  }

}
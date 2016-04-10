package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._

object Application extends Controller {
  def index = Action {
    Ok("Hello World")
  }

  def slack = Action { request =>
    val params = request.body.asFormUrlEncoded
    val teamId = params.get("team_id")(0)
    val text = params.get("text")(0).split(" ")
    val action = text(0).trim
    val data = text.slice(1, text.length).mkString(" ")
    Ok(Json.obj("status" -> "OK",
                "action" -> action,
                "data" -> data
    ))
  }

  def slack_router(action: String): Any = {
    Logger.debug("Slack router for " + action)
  }
}

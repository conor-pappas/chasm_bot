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
    Ok(Json.obj("status" -> "OK", "message" -> teamId))
  }
}

package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._

object Application extends Controller {

  def index = Action {
    Ok("Hello World")
  }

  def slack = Action(parse.multipartFormData) { request =>
    val result = request.toString()
    Ok(Json.obj("status" -> "OK", "message" -> result))
  }
}
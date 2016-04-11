package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import models._

object Application extends Controller {
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def slack = Action { request =>
    val params = request.body.asFormUrlEncoded
    val teamId = params.get("team_id")(0)
    val text = params.get("text")(0).split(" ")
    val actionName = text(0).trim
    val data = text.slice(1, text.length).mkString(" ")
    val responseUrl = params.get("response_url")(0)
    val username = params.get("user_name")(0)

    val responseText = runSlashAction(actionName, data, username)
    if(responseText.isDefined) {
      Ok(responseText.get)
    } else {
      Ok("")
    }
  }

  def runSlashAction(actionName:String, data:String, username:String):Option[String] = {
    val votingSession = VotingSession.findCurrent
    val slashAction = matchSlashAction(actionName)
    slashAction.execute(votingSession, username, data)
  }

  def matchSlashAction(actionName:String):slash_actions.SlashAction = {
    actionName match {
      case "justify" => slash_actions.Justify
      case "quesiton" => slash_actions.Question
      case "results" => slash_actions.Results
      case "revote" => slash_actions.Revote
      case "start" => slash_actions.Start
      case "vote" => slash_actions.Vote
    }
  }

}

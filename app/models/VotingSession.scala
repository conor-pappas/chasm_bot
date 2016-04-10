package models

import java.util.{Date}
import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._
import scala.language.postfixOps
import play.api.libs.json._

case class VotingSession(id: Pk[Long], ticket: String, votes: Map[String, JsValue])

object VotingSession {
  /**
    * Parse a Task from a ResultSet
    * @return VotingSession
    */
  val simple = {
    get[Pk[Long]]("VotingSession.id") ~
      get[String]("VotingSession.ticket") ~
      get[String]("VotingSession.votes") map {
      case id~ticket~votes => {
        val votes_json = Json.parse(votes).as[JsObject]
        val vote_map = votes_json match {
          case JsObject(fields) => fields.toMap
        }
        VotingSession(id, ticket, vote_map)
      }
    }
  }

  def toJson(votingSession:VotingSession):String = {
    s"""(Seq)
    {
      "id": ${votingSession.id},
      "ticket": ${votingSession.ticket},
      "votes": ${Json.toJson(votingSession.votes)}
    }
    """
  }

  /**
    * Fetch the latest voting session from the database
    * @return VotingSession
    */
  def findCurrent = {
    DB.withConnection { implicit connection => SQL("select * from VotingSession ORDER BY id LIMIT 1").as(VotingSession.simple.singleOpt) }
  }
}

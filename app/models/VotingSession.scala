package models

import java.util.{Date}
import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._
import scala.language.postfixOps
import play.api.libs.json._

case class VotingSession(id: Long, ticket: String, votes: Map[String, Int])

object VotingSession {
  /**
    * Parse a Task from a ResultSet
    * @return VotingSession
    */
  val simple = {
    get[Long]("VotingSessions.id") ~
      get[String]("VotingSessions.ticket") ~
      get[String]("VotingSessions.votes") map {
      case id~ticket~votes => {
        val votes_json = Json.parse(votes).as[Map[String, Int]]
        VotingSession(id, ticket, votes_json)
      }
    }
  }

  def votes_to_json(votes:Map[String, Int]):JsObject = {
    JsObject(votes.mapValues(vote => JsNumber(vote)).toSeq)
  }

  def create(ticket: String, votes: Map[String, Int]) = {
    val votes_json = votes_to_json(votes).toString()
    DB.withConnection {
      implicit connection =>
      SQL(s"""
        INSERT INTO VotingSessions (ticket, votes)
        VALUES ('$ticket', '$votes_json');
      """).executeUpdate()
    }
  }

  def setVotes(votingSession:VotingSession, votes: Map[String, Int]) = {
    val votes_json = votes_to_json(votes).toString()
    DB.withConnection {
      implicit connection =>
      SQL(s"""
        UPDATE VotingSessions SET votes='$votes_json'
        WHERE id = ${votingSession.id}
      """).executeUpdate()
    }
  }

  def clearVotes(votingSession:VotingSession) = {
    setVotes(votingSession, Map[String, Int]())
  }

  def addVote(votingSession:VotingSession, user:String, vote:Int) {
    val newVotes = votingSession.votes + (user -> vote)
    setVotes(votingSession, newVotes)
  }


  def destroy(votingSession:VotingSession) = {
    DB.withConnection {
      implicit connection =>
      SQL(s"""
        DELETE FROM VotingSessions
        WHERE id = ${votingSession.id}
      """).executeUpdate()
    }
  }

  def destroyCurrent:Option[VotingSession] = {
    val current = findCurrent
    if (current.isDefined) {
      destroy(current.get)
    } 
    return current
  }
 
  /**
    * Fetch the latest voting session from the database
    * @return VotingSession
    */
  def findCurrent:Option[VotingSession] = {
    DB.withConnection { implicit connection => SQL("SELECT * FROM VotingSessions ORDER BY id LIMIT 1").as(VotingSession.simple.singleOpt) }
  }
}

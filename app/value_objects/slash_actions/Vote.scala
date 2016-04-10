package slash_actions

object Vote extends SlashAction {

  def execute(votingSession:String, username:String, data:String) {
    var vote = data.toInt
    // TODO: Set vote on votingSession
  }

}
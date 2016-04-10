package slash_actions

object Justify extends SlashAction {

  def execute(votingSession:String, username:String, data:String) {
    val justification = data
    val currentVote = 2 //TODO: get this from the voting session 
    val message = justificationMessage(justification, currentVote)
    slack.IncomingWebhookClient.postInChannel(justification)
  }

  def justificationMessage(justification:String, currentVote:Integer):String = {
    s"A user has justified their vote of $currentVote with the following:\n" +
    justification + "\n" + "*Insert cheeky comment here*"
  }

}
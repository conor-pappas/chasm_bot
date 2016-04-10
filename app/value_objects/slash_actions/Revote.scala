package slash_actions

object Revote extends SlashAction {

  def execute(votingSession:String, username:String, data:String) {
    val ticketDescription = "" // TODO get this from the voting session
    // TODO reset votes in voting session
    val message = revoteCalledMessage(ticketDescription)
    slack.IncomingWebhookClient.postInChannel(message)   
  }

  def revoteCalledMessage(ticketDescription:String):String = {
    s"@here Given the discussion above about #ticketDescription, please " +
    "place a new vote using `/chasm vote`. Maybe try coming to a consensus " + 
    "this time so I can take a break."
  }

}
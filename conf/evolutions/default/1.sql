# VotingSessions schema
 
# --- !Ups
 
CREATE TABLE VotingSession (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    ticket varchar(255) NOT NULL,
    votes text NOT NULL,
    PRIMARY KEY (id)
);
 
# --- !Downs
 
DROP TABLE VotingSession;

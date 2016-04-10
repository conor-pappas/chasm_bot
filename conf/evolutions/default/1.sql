# VotingSessions schema
 
# --- !Ups

CREATE TABLE VotingSessions (
  id serial PRIMARY KEY,
  ticket varchar(255) NOT NULL,
  votes text NOT NULL
);

 
# --- !Downs
 
DROP TABLE VotingSessions;

package com.fdilke.music.util

import org.jfugue.pattern.Token.TokenType
import org.jfugue.pattern.{Pattern, Token}
import org.jfugue.theory.Note

import scala.collection.JavaConverters._

object Enrichments {

  implicit class RichNote(val note: Note) {
    def +(delta: Int): Note =
      new Note(
        note.getValue + delta,
        note.getDuration
      )

    def -(delta: Int): Note =
      this + (-delta)
  }

  private def compareTokens(token1: Token, token2: Token) =
  (token1.getType == token2.getType) &&
    (token1.toString == token2.toString)

  implicit class RichToken(val token: Token) {
    override def equals(obj: scala.Any): Boolean =
      obj match {
        case anotherToken: Token =>
          compareTokens(token, anotherToken)
        case richToken: RichToken =>
          compareTokens(token, richToken.token)
        case _ =>
          false
      }
  }

  implicit class RichPattern(pattern: Pattern) {
    private def doFold(
      f: (Pattern, Token) => Pattern
    ) =
      pattern.getTokens.asScala.foldLeft(
        new Pattern
      )(f)

    def reverse: Pattern =
      doFold {
        _.prepend(_)
      }

    def changeNotes(fun: Note => Note): Pattern =
      doFold { (pattern, token) =>
        pattern.add(
          token.getType match {
            case TokenType.NOTE =>
              fun(
                new Note(
                  token.toString
                )
              )
            case _ => token
          }
        )
      }
  }
}
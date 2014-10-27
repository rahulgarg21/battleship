package com.iheart.battleship.game

import com.iheart.battleship.board.Board
import com.iheart.battleship.fleet.{Fleet, FleetSpec}
import com.iheart.battleship.ship.Ship

/**
 * Created by Rajiv.
 */
trait Game {

  def locateShips(board:Board, fleet:Fleet):IndexedSeq[Ship]

  def areValidShips(board:Board, ships:IndexedSeq[Ship]):Boolean

  def areAllShipsPlaced(ships:IndexedSeq[Ship],shipSpec:FleetSpec):Boolean


}

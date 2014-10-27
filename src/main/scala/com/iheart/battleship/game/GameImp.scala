package com.iheart.battleship.game

import com.iheart.battleship.board.Board
import com.iheart.battleship.fleet.{Fleet, FleetSpec}
import com.iheart.battleship.ship.Ship

/**
 * Created by Rajiv.
 */
class GameImp extends Game{

  override def locateShips(board: Board, fleet:Fleet): IndexedSeq[Ship] = {

    val shipIds = board.cells.filterNot(_.isBlank()).map(cell => cell.shipId().get).toSet.toIndexedSeq.sorted

    shipIds.map(id => fleet.getShipById(id))

  }

  override def areValidShips(board:Board, ships: IndexedSeq[Ship]): Boolean = {

    var result:Boolean = true;

    for(ship <- ships) {
       if(ship.isPlacedOnBoard())  result = ship.validateShip(board)
    }

    result;
  }

  override def areAllShipsPlaced(ships: IndexedSeq[Ship], shipSpec: FleetSpec): Boolean = {
    ships.size == shipSpec.size()
  }
}

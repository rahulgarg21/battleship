package com.iheart.battleship.common

import com.iheart.battleship.board.{Board, BoardImp}
import com.iheart.battleship.cell.{Cell, CellImp, CellState}
import com.iheart.battleship.fleet.{Fleet, FleetImp, FleetSpec}
import com.iheart.battleship.game.{Game, GameImp}
import com.iheart.battleship.ship._
import org.scalatest.FunSuite

/**
 * Created by Rajiv.
 */
trait BaseFunSuite extends FunSuite {


  def assertCellStateAndShipId(grid: Board, ship: Ship, requestedIndexes: IndexedSeq[(Int, Int)]) = {

    assert(ship.isPlacedOnBoard() === true)
    for (index <- requestedIndexes) {
      val boardCell = grid.getCell(index._1, index._2)
      assert(boardCell.get.shipId() === Some(ship.id))
      assert(boardCell.get.state() === CellState.OCCUPIED)
    }

  }

}

package com.iheart.battleship.ship

import com.iheart.battleship.board.Board
import com.iheart.battleship.cell.Cell

/**
 * Created by Rajiv.
 */
trait Ship {

  val id:Int

  def size():Int

  def isPlacedOnBoard():Boolean

  def placeShip(board:Board, requestedCells:IndexedSeq[(Int,Int)]):Board

  def validateShip(board:Board):Boolean

}

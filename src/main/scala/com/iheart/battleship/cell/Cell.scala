package com.iheart.battleship.cell

import com.iheart.battleship.board.Board
import com.iheart.battleship.cell.CellState._

/**
 * Created by Rajiv.
 */
trait Cell {

  val rowIndex:Int

  val columnIndex:Int

  def state():State

  def state_$eq(state:State):Unit

  def shipId():Option[Int]

  def shipId_$eq(shipId:Option[Int]):Unit

  def isBlank():Boolean = { if(state() == CellState.BLANK) true else false }


}

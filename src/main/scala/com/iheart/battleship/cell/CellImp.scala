package com.iheart.battleship.cell

import com.iheart.battleship.cell.CellState._

/**
 * Created by Rajiv.
 */
class CellImp(val rowIndex:Int, val columnIndex:Int, var state:State = BLANK, implicit var shipId:Option[Int] = None) extends Cell{

  require(rowIndex > 0 && columnIndex > 0, "row and column index must be greater than zero")


}

object CellImp {

  def apply(rowIndex:Int,columnIndex:Int):CellImp = {
    new CellImp(rowIndex,columnIndex)
  }

}

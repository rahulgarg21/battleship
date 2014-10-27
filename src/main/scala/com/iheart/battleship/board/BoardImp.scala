package com.iheart.battleship.board

import com.iheart.battleship.cell.{CellImp, Cell}

/**
 * Created by Rajiv.
 */
case class BoardImp(val rows:Int, val columns:Int) extends Board{

  require(rows > 0 && columns > 0, "rows and columns must be greater than zero")

  val cells: IndexedSeq[Cell] = for(r <- 1 to rows;c <- 1 to columns ) yield CellImp(r,c)

}

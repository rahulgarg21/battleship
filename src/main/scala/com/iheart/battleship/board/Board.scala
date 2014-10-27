package com.iheart.battleship.board

import com.iheart.battleship.cell.{CellState, Cell}

/**
 * Created by Rajiv.
 */
trait Board {

  val cells:IndexedSeq[Cell]

  val rows:Int

  val columns:Int

  def setShip(rowIndex:Int,columnIndex:Int,shipId:Int): Unit = {
    val boardCell:Cell = getCell(rowIndex,columnIndex).get
    boardCell.shipId_$eq(Some(shipId))
    boardCell.state_$eq(CellState.OCCUPIED)

  }

  def getCell(rowIndex:Int, columnIndex:Int):Option[Cell] = {
    val cell = cells.filter(cell => cell.rowIndex == rowIndex && cell.columnIndex == columnIndex)

    if(cell.isEmpty) None else Some(cell(0))
  }

  def getCellsForShipId(shipId:Int):IndexedSeq[Cell] = cells.filter( cell => !cell.isBlank() && cell.shipId().get == shipId)


}

package com.iheart.battleship.board

import com.iheart.battleship.cell.{CellState, CellImp, Cell}
import com.iheart.battleship.common.BaseFunSuite
import org.scalatest.FunSuite

/**
 * Created by Rajiv.
 */
class BoardImpTest extends BaseFunSuite {


  test("Confirm 5x5 board has total 25 cells") {
    val board:Board = BoardImp(5,5)
    val cells:IndexedSeq[Cell] = board.cells
    assert(cells.size == 25)
  }

  test("Confirm 5x5 board columns and row indexes") {
    val board:Board = BoardImp(5,5)
    val cells = board.cells
    val cell11 = board.getCell(1,1).get
    assert(cell11.rowIndex === 1 && cell11.columnIndex === 1 )
    val cell55  = board.getCell(5,5).get
    assert(cell55.rowIndex === 5 && cell55.columnIndex === 5 )

  }

  test("Creating grid with rows or columns less than 0 fails") {
    intercept[IllegalArgumentException] {
      new BoardImp(0,0)
    }
  }

  test("Can get cell by its row and column index and update it") {
    val testBoard = BoardImp(5,5)
    testBoard.setShip(rowIndex = 1,columnIndex = 1,shipId = 1)
    val boardCell11 = testBoard.getCell(1,1)
    assert(boardCell11.get.shipId() == Some(1))
    assert(boardCell11.get.isBlank() === false)
    assert(boardCell11.get.state() === CellState.OCCUPIED)

  }


}

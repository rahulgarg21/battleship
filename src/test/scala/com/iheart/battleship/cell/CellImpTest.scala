package com.iheart.battleship.cell

import com.iheart.battleship.cell.CellState._
import com.iheart.battleship.common.BaseFunSuite
import org.scalatest.FunSuite

/**
 * Created by Rajiv.
 */
class CellImpTest extends BaseFunSuite {



  test("Creation of Cell with proper indexes and state") {
    val cell11: Cell = CellImp(1, 1)
    assert(cell11.rowIndex === 1)
    assert(cell11.columnIndex === 1)
    assert(cell11.state === BLANK)
  }

  test("Creating cell with indexes less than 1 fails") {
    intercept[IllegalArgumentException] {
      new CellImp(0, 0)
    }
  }

  test("Cell state and shipId can be changed properly") {
    val testCell: Cell = new CellImp(1,1)
    assert(testCell.state === BLANK)
    testCell.state_$eq(OCCUPIED)
    testCell.shipId_$eq(Some(1))
    assert(testCell.state === OCCUPIED)
    assert(testCell.shipId === Some(1))
  }


}

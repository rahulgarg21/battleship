package com.iheart.battleship.ship

import com.iheart.battleship.board.{BoardImp, Board}
import com.iheart.battleship.cell.CellState
import com.iheart.battleship.common.BaseFunSuite
import org.scalatest.FunSuite

/**
 * Created by Rajiv.
 */
class ShipImpTest extends BaseFunSuite {

  test("OneDecker Ship can be created properly") {

    val board1: Board = BoardImp(5, 5)
    val oneDecker: Ship = OneDecker(1)
    val oneDeckerCells: IndexedSeq[(Int, Int)] = IndexedSeq((4, 3))

    val secondOneDecker:Ship = OneDecker(2)
    val secondOneDeckerCells: IndexedSeq[(Int,Int)] = IndexedSeq((3,1))


    assert(oneDecker.id === 1)
    assert(oneDecker.isPlacedOnBoard() === false)

    oneDecker.placeShip(board1,oneDeckerCells)

    assertCellStateAndShipId(board1,oneDecker,oneDeckerCells)

    secondOneDecker.placeShip(board1,secondOneDeckerCells)

    assertCellStateAndShipId(board1,secondOneDecker,secondOneDeckerCells)

  }

  test("TwoDecker Ship can be created properly") {

    val board2: Board = BoardImp(5, 5)
    val twoDecker: Ship = TwoDecker(3)
    val twoDeckerCells: IndexedSeq[(Int, Int)] = IndexedSeq((5, 1), (5, 2))

    assert(twoDecker.id === 3)
    assert(twoDecker.isPlacedOnBoard() === false)

    twoDecker.placeShip(board2,twoDeckerCells)

    assertCellStateAndShipId(board2,twoDecker,twoDeckerCells)

  }

  test("ThreeDecker Ship can be created properly") {

    val board3: Board = BoardImp(5, 5)
    val threeDecker: Ship = ThreeDecker(4)
    val threeDeckerCells: IndexedSeq[(Int, Int)] = IndexedSeq((3, 5), (4, 5), (5, 5))

    assert(threeDecker.id === 4)
    assert(threeDecker.isPlacedOnBoard() === false)

    threeDecker.placeShip(board3,threeDeckerCells)

    assertCellStateAndShipId(board3,threeDecker,threeDeckerCells)

  }

  test("FourDecker Ship can be created properly") {

    val board4: Board = BoardImp(5, 5)
    val fourDecker: Ship = FourDecker(5)
    val fourDeckerCells: IndexedSeq[(Int, Int)] = IndexedSeq((1, 1), (1, 2), (1, 3), (1, 4))

    assert(fourDecker.id === 5)
    assert(fourDecker.isPlacedOnBoard() === false)

    fourDecker.placeShip(board4,fourDeckerCells)

    assertCellStateAndShipId(board4,fourDecker,fourDeckerCells)

  }


  test("Incorrect ship size fails") {

    val testBoard1: Board = BoardImp(5, 5)
    val oneDecker: Ship = OneDecker(1)

    intercept[IllegalArgumentException] {
      oneDecker.placeShip(testBoard1, IndexedSeq((4, 1), (4, 2)))
    }

  }

  test("Ship placement must be either horizontal or vertical") {

    val testBoard2: Board = BoardImp(5, 5)
    val twoDecker: Ship = TwoDecker(3)

    intercept[IllegalArgumentException] {
      twoDecker.placeShip(testBoard2, IndexedSeq((2, 3), (3,1 )))
    }

  }

  test("Cannot assign same cells to more than one ship") {

    val testBoard: Board = BoardImp(5, 5)
    val oneDecker: Ship = OneDecker(10)
    val oneDeckerCells: IndexedSeq[(Int, Int)] = IndexedSeq((4, 3))
    oneDecker.placeShip(testBoard, oneDeckerCells)

    val twoDecker: Ship = TwoDecker(11)
    val invalidTwoDeckerCells: IndexedSeq[(Int, Int)] = IndexedSeq((4, 3), (4, 4))

    intercept[IllegalArgumentException] {
      twoDecker.placeShip(testBoard,invalidTwoDeckerCells )
    }

  }


}

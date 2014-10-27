package com.iheart.battleship.ship


import com.iheart.battleship.board.Board
import com.iheart.battleship.cell.CellState.OCCUPIED
import com.iheart.battleship.cell.{CellState, Cell}

/**
 * Created by Rajiv.
 */
sealed abstract class ShipImp(val size:Int, var isPlacedOnBoard:Boolean = false ) extends Ship{

  override def placeShip(board: Board, indexes: IndexedSeq[(Int,Int)]): Board = {

    //check ship size
    if (indexes.isEmpty || indexes.size != size) {
        throw new IllegalArgumentException("Ship size does not match with cells")
    }

    //check ship placement direction
    if(checkInvalidCellPlacement(indexes)) {
      throw new IllegalArgumentException("Ship placement must be either horizontal or vertical")
    }

    //check if cells on the board are available
   for(index <- indexes) {
     val cellOption:Option[Cell] = board.getCell(index._1,index._2);
     if(cellOption == None || cellOption.get.state() == CellState.OCCUPIED)
       throw new IllegalArgumentException("Cannot assign same cells to more than one ship")
   }


   for( index:(Int,Int) <- indexes) {
     board.setShip(index._1,index._2, id)
   }


    // ship is now placed on the board
    isPlacedOnBoard = true

    board
  }

   protected def checkInvalidCellPlacement(cells:Seq[(Int,Int)]):Boolean = {

      if(cells.size >= 2 &&
        !(
          //all row indexes must match
          cells.map(_._1).toSet.size == 1 ||
          //all column indexes must match
          cells.map(_._2).toSet.size == 1)
      ) {
        true
      }
      else
        false
    }

  def validateShip(board:Board):Boolean = {

    var result = true;

    val cellIndexes = board.getCellsForShipId(id).map(cell => (cell.rowIndex,cell.columnIndex))

    if(isHorizontal(cellIndexes)) {

      val rIndex = cellIndexes(0)._1

      //check left edge
      val leftCell = board.getCell(rIndex,minColumnIndex(cellIndexes)-1)
      if( leftCell != None && leftCell.get.state() != CellState.BLANK) result = false

      //check right edge
      val rightCell = board.getCell(rIndex,maxColumnIndex(cellIndexes)+1)
      if( rightCell != None && rightCell.get.state() != CellState.BLANK) result = false

      //check top cells
      for(topIndex <- getTopIndexes(cellIndexes)) {
        val topCell = board.getCell(topIndex._1,topIndex._2)
        if( topCell != None && topCell.get.state() != CellState.BLANK) result = false
      }

      //check bottom cells
      for(bottomIndex <- getBottomIndexes(cellIndexes)) {
        val bottomCell = board.getCell(bottomIndex._1,bottomIndex._2)
        if( bottomCell != None && bottomCell.get.state() != CellState.BLANK) result = false
      }

    } else {

      val cIndex = cellIndexes(0)._2

      //check top edge
      val topCell = board.getCell(maxRowIndex(cellIndexes)+1,cIndex)
      if( topCell != None && topCell.get.state() != CellState.BLANK) result = false

      //check bottom edge
      val bottomCell = board.getCell(minRowIndex(cellIndexes)-1,cIndex)
      if( bottomCell != None && bottomCell.get.state() != CellState.BLANK) result = false

      //check top cells
      for(leftIndex <- getLeftIndexes(cellIndexes)) {
        val leftCell = board.getCell(leftIndex._1,leftIndex._2)
        if( leftCell != None && leftCell.get.state() != CellState.BLANK) result = false
      }

      //check bottom cells
      for(rightIndex <- getRightIndexes(cellIndexes)) {
        val rightCell = board.getCell(rightIndex._1,rightIndex._2)
        if( rightCell != None && rightCell.get.state() != CellState.BLANK) result = false
      }

    }

    result
  }

  protected def isHorizontal(cellIndexes:IndexedSeq[(Int,Int)]):Boolean = {
    cellIndexes.map(index => index._1).toSet.size == 1
  }

  protected def maxRowIndex(cellIndexes:IndexedSeq[(Int,Int)]):Int = {
    cellIndexes.map(index => index._1).max
  }

  protected def minRowIndex(cellIndexes:IndexedSeq[(Int,Int)]):Int = {
    cellIndexes.map(index => index._1).min
  }

  protected def maxColumnIndex(cellIndexes:IndexedSeq[(Int,Int)]):Int = {
    cellIndexes.map(index => index._2).max
  }

  protected def minColumnIndex(cellIndexes:IndexedSeq[(Int,Int)]):Int = {
    cellIndexes.map(index => index._2).min
  }

  protected def getTopIndexes(cellIndexes:IndexedSeq[(Int,Int)]):IndexedSeq[(Int,Int)] = {
    cellIndexes.map(cellIndex => (cellIndex._1+1,cellIndex._2))
  }

  protected def getBottomIndexes(cellIndexes:IndexedSeq[(Int,Int)]):IndexedSeq[(Int,Int)] = {
    cellIndexes.map(cellIndex => (cellIndex._1-1,cellIndex._2))
  }

  protected def getLeftIndexes(cellIndexes:IndexedSeq[(Int,Int)]):IndexedSeq[(Int,Int)] = {
    cellIndexes.map(cellIndex => (cellIndex._1,cellIndex._2-1))
  }

  protected def getRightIndexes(cellIndexes:IndexedSeq[(Int,Int)]):IndexedSeq[(Int,Int)] = {
    cellIndexes.map(cellIndex => (cellIndex._1,cellIndex._2+1))
  }


}

case class OneDecker(val id:Int) extends ShipImp(1)
case class TwoDecker(val id:Int) extends ShipImp(2)
case class ThreeDecker(val id:Int ) extends ShipImp(3)
case class FourDecker(val id:Int) extends ShipImp(4)




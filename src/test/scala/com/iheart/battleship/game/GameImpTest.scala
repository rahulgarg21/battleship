package com.iheart.battleship.game

import com.iheart.battleship.board.{Board, BoardImp}
import com.iheart.battleship.common.BaseFunSuite
import com.iheart.battleship.fleet.{Fleet, FleetImp, FleetSpec}

/**
 * Created by Rajiv.
 */
class GameImpTest extends BaseFunSuite {

  val game: Game = new GameImp()

  test("locate Ships") {
    val testBoard: Board = BoardImp(5, 5)
    val testFleet: Fleet = FleetImp(FleetSpec(oneDeckers = 2, twoDeckers = 1, threeDeckers = 0, fourDeckers = 0))
    testFleet.getShipById(1).placeShip(testBoard, IndexedSeq((1, 1)));
    testFleet.getShipById(2).placeShip(testBoard, IndexedSeq((5, 5)));
    testFleet.getShipById(3).placeShip(testBoard, IndexedSeq((1, 3), (1, 4)));
    val ships = game.locateShips(testBoard, testFleet)
    assert(ships.size === 3)
  }

  test("Valid Fleet Placement passes Validation") {
    val board: Board = BoardImp(5, 5)
    val defaultFleetSpec: FleetSpec = FleetSpec(oneDeckers = 2, twoDeckers = 1, threeDeckers = 1, fourDeckers = 1)
    val fleet: Fleet = FleetImp(defaultFleetSpec)
    val oneDeckerCells: IndexedSeq[(Int, Int)] = IndexedSeq((4, 3))
    val secondOneDeckerCells: IndexedSeq[(Int, Int)] = IndexedSeq((3, 1))
    val twoDeckerCells: IndexedSeq[(Int, Int)] = IndexedSeq((5, 1), (5, 2))
    val threeDeckerCells: IndexedSeq[(Int, Int)] = IndexedSeq((3, 5), (4, 5), (5, 5))
    val fourDeckerCells: IndexedSeq[(Int, Int)] = IndexedSeq((1, 1), (1, 2), (1, 3), (1, 4))
    fleet.getShipById(1).placeShip(board, oneDeckerCells)
    fleet.getShipById(2).placeShip(board, secondOneDeckerCells)
    fleet.getShipById(3).placeShip(board, twoDeckerCells)
    fleet.getShipById(4).placeShip(board, threeDeckerCells)
    fleet.getShipById(5).placeShip(board, fourDeckerCells)
    val ships = game.locateShips(board, fleet)
    assert(ships.size === 5)

    assert(game.areValidShips(board, fleet.ships) == true)

  }

  test("Invalid Fleet Placement Fails Validation") {
    val board: Board = BoardImp(5, 5)
    val defaultFleetSpec: FleetSpec = FleetSpec(oneDeckers = 2, twoDeckers = 1, threeDeckers = 1, fourDeckers = 1)
    val fleet: Fleet = FleetImp(defaultFleetSpec)
    val oneDeckerCells: IndexedSeq[(Int, Int)] = IndexedSeq((5, 2))
    val secondOneDeckerCells: IndexedSeq[(Int, Int)] = IndexedSeq((2, 1))
    val twoDeckerCells: IndexedSeq[(Int, Int)] = IndexedSeq((3, 5), (4, 5))
    val threeDeckerCells: IndexedSeq[(Int, Int)] = IndexedSeq((4, 1), (4, 2), (4, 3))
    val fourDeckerCells: IndexedSeq[(Int, Int)] = IndexedSeq((1, 1), (1, 2), (1, 3), (1, 4))
    fleet.getShipById(1).placeShip(board, oneDeckerCells)
    fleet.getShipById(2).placeShip(board, secondOneDeckerCells)
    fleet.getShipById(3).placeShip(board, twoDeckerCells)
    fleet.getShipById(4).placeShip(board, threeDeckerCells)
    fleet.getShipById(5).placeShip(board, fourDeckerCells)

    val ships = game.locateShips(board, fleet)
    assert(ships.size === 5)

    assert(game.areValidShips(board, fleet.ships) == false)

  }

  test("If all ships are placed on the board") {
    val testBoard: Board = BoardImp(5, 5)
    val fleetSpec: FleetSpec = FleetSpec(oneDeckers = 2, twoDeckers = 1, threeDeckers = 0, fourDeckers = 0)
    val testFleet: Fleet = FleetImp(fleetSpec)
    testFleet.getShipById(1).placeShip(testBoard, IndexedSeq((1, 1)));
    testFleet.getShipById(2).placeShip(testBoard, IndexedSeq((5, 5)));
    var ships = game.locateShips(testBoard, testFleet)
    assert(ships.size === 2)
    assert(game.areAllShipsPlaced(ships, fleetSpec) == false)
    testFleet.getShipById(3).placeShip(testBoard, IndexedSeq((1, 3), (1, 4)));
    ships = game.locateShips(testBoard, testFleet)
    assert(ships.size === 3)
    assert(game.areAllShipsPlaced(ships, fleetSpec) == true)

  }

}

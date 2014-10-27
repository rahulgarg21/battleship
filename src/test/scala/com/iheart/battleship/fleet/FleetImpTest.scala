package com.iheart.battleship.fleet

import com.iheart.battleship.common.BaseFunSuite
import com.iheart.battleship.ship.{OneDecker, Ship}
import org.scalatest.FunSuite

/**
 * Created by Rajiv.
 */
class FleetImpTest extends BaseFunSuite {

  val defaultFleetSpec: FleetSpec = FleetSpec(oneDeckers = 2, twoDeckers = 1, threeDeckers = 1, fourDeckers = 1)
  val fleet: Fleet = FleetImp(defaultFleetSpec)

  test("Fleet creation for given fleet Spec") {
    assert(fleet.ships.size === 5)

  }

  test("Get ship by Id") {
    val ship:Ship = fleet.getShipById(1)
    assert(ship.id === 1)
    assert(ship.isInstanceOf[OneDecker])
  }

}

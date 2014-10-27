package com.iheart.battleship.fleet

import com.iheart.battleship.ship._

/**
 * Created by Rajiv.
 */
class FleetImp extends Fleet{

  override def addShip(ship: Ship*): Fleet = {
    ships = ships ++ ship
    this
  }

}


object FleetImp {

  def apply(fleetSpec:FleetSpec):Fleet = {

    val fleet:Fleet = new FleetImp()

    var id = 1;

    for(qty <- 1 to fleetSpec.oneDeckers) {
      fleet.addShip(OneDecker(id))
      id = id+1
    }

    for(qty <- 1 to fleetSpec.twoDeckers) {
      fleet.addShip(TwoDecker(id))
      id = id+1
    }

    for(qty <- 1 to fleetSpec.threeDeckers) {
      fleet.addShip(ThreeDecker(id))
      id = id+1
    }

    for(qty <- 1 to fleetSpec.fourDeckers) {
      fleet.addShip(FourDecker(id))
      id = id+1
    }

    return fleet;

  }

}
package com.iheart.battleship.fleet

import com.iheart.battleship.ship.Ship

/**
 * Created by Rajiv.
 */
case class FleetSpec(val oneDeckers:Int,val twoDeckers:Int,val threeDeckers:Int,val fourDeckers:Int) {

  def size():Int = {
    oneDeckers + twoDeckers + threeDeckers + fourDeckers
  }

}

package com.iheart.battleship.fleet

import com.iheart.battleship.ship._

/**
 * Created by Rajiv.
 */
trait Fleet {

  var ships:IndexedSeq[Ship] = IndexedSeq.empty

  def addShip( ship:Ship* ):Fleet

  def getShipById(id:Int):Ship = {

    ships.filter( ship => ship.id == id)(0)

  }


}

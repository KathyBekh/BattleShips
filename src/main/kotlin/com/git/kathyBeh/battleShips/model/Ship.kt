package com.git.kathyBeh.battleShips.model

class Ship(val cells: List<Cell>) {
    private val aliveCells = cells.toMutableList()


    internal fun doesFitInField(width: Int, height: Int): Boolean = cells.all {
        it.x < width && it.y < height
    }

    internal fun canPlaceNear(ship: Ship): Boolean = ship.cells.all {
        canPlaceNear(it)
    }

    private fun canPlaceNear(cell: Cell): Boolean = cells.all {
        it.canPlaceNear(cell)
    }

    internal fun takeAShot(coordinates: Cell): ShotResult {
        if (coordinates in aliveCells) {
            aliveCells.remove(coordinates)
            return if (aliveCells.isEmpty()) ShotResult.Kill else ShotResult.Hit
        }
        return ShotResult.Miss
    }

    internal fun haloShip(): Set<Cell> {
        val haloShip = mutableSetOf<Cell>()
        for (c in cells){
            haloShip.addAll(c.haloCell())
        }
        return haloShip
    }

    override fun toString(): String {
        return cells.joinToString()
    }
}
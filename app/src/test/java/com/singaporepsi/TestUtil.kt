package com.singaporepsi

import com.singaporepsi.model.*
import org.threeten.bp.ZonedDateTime

object TestUtil {

    fun createSuccessResponse() = ApiResponse(
        createApiInfo(),
        createItems(),
        createRegionMetaData()
    )

    private fun createApiInfo(): ApiInfo {
        return ApiInfo("healthy")
    }

    private fun createItems(): List<Item> {
        return listOf(
            createItem()
        )
    }

    private fun createItem(): Item {
        return Item(
            createReadings(),
            ZonedDateTime.now(),
            ZonedDateTime.now()
        )
    }

    private fun createReadings(): Readings {
        return Readings(
            Reading(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)
        )
    }

    private fun createRegionMetaData(): List<RegionMetadata> {
        return listOf(
            RegionMetadata(
                LabelLocation(0.0, 0.0),
                "west"
            )
        )
    }
}
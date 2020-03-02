package com.jay.repository.mapper

import com.jay.remote.model.KinItem
import com.jay.repository.model.Kin

object KinItemMapper : Mapper<Kin, KinItem> {
    override fun map(input: Kin): KinItem =
        KinItem(
            description = input.description,
            link = input.link,
            title = input.title
        )

    override fun reverseMap(output: KinItem): Kin =
        Kin(
            description = output.description,
            link = output.link,
            title = output.title
        )
}
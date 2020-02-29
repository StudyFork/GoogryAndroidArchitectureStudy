package com.jay.repository.mapper

import com.jay.local.model.KinEntity
import com.jay.repository.model.Kin

object KinEntityMapper : Mapper<Kin, KinEntity> {
    override fun map(input: Kin): KinEntity =
        KinEntity(
            description = input.description,
            link = input.link,
            title = input.title
        )

    override fun reverseMap(output: KinEntity): Kin =
        Kin(
            description = output.description,
            link = output.link,
            title = output.title
        )
}
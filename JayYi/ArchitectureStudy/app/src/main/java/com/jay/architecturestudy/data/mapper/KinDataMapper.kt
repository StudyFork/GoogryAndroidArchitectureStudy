package com.jay.architecturestudy.data.mapper

import com.jay.architecturestudy.data.database.entity.KinEntity
import com.jay.architecturestudy.data.model.Kin

object KinDataMapper : Mapper<Kin, KinEntity> {
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
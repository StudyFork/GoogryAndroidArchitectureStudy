package com.jay.architecturestudy.data.mapper

import com.jay.architecturestudy.data.database.entity.KinEntity
import com.jay.architecturestudy.data.model.Kin

object KinDataMapper : Mapper<Kin, KinEntity> {
    override fun map(input: Kin): KinEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reverseMap(output: KinEntity): Kin {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
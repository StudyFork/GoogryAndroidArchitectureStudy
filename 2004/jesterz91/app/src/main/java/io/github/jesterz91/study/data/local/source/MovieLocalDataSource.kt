package io.github.jesterz91.study.data.local.source

import io.github.jesterz91.study.data.local.model.MovieLocal
import io.reactivex.Single

interface MovieLocalDataSource {

    fun selectMovie(): Single<List<MovieLocal>>
}
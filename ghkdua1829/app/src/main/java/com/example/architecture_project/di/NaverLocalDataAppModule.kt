package com.example.architecture_project.di

import com.example.architecture_project.data.datasource.local.NaverLocalDataSource
import com.example.architecture_project.data.datasource.local.NaverLocalDataSourceImpl
import org.koin.dsl.module

val localDataAppModule =
    module { single<NaverLocalDataSource> { NaverLocalDataSourceImpl() } }
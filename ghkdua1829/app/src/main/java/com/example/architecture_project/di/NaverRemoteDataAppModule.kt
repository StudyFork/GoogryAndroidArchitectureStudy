package com.example.architecture_project.di

import com.example.architecture_project.data.datasource.remote.NaverRemoteDataSource
import com.example.architecture_project.data.datasource.remote.NaverRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataAppModule =
    module { single<NaverRemoteDataSource> { NaverRemoteDataSourceImpl() } }
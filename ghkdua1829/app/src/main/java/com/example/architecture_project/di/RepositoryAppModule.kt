package com.example.architecture_project.di

import com.example.architecture_project.data.repository.NaverRepository
import org.koin.dsl.module

val repositoryAppModule= module { single {  NaverRepository(get())  } }
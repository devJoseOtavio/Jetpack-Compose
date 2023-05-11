package com.example.mvvmapp.domain.util

interface DomainMapper <T, DomainModel> {

    fun mapToDomainModel(entity: T): DomainModel

    fun mapToEntity(domainModel: DomainModel): T
}
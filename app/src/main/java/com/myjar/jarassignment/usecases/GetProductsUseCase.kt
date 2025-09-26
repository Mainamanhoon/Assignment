package com.myjar.jarassignment.usecases

import com.myjar.jarassignment.Utils.Resource
import com.myjar.jarassignment.data.model.ComputerItem
import com.myjar.jarassignment.data.repository.JarRepositoryImpl
import com.myjar.jarassignment.impl.JarRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val repo : JarRepository){
    operator fun invoke() = flow<Resource<List<ComputerItem>>> {
        emit(Resource.Loading)
        try{
            val result = repo.fetchResults()
            emit(result)
        }catch (e:Exception){
            Resource.Failure(e)
        }
    }
}
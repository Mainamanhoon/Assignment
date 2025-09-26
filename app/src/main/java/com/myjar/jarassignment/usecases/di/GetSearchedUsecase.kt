package com.myjar.jarassignment.usecases.di

import com.myjar.jarassignment.Utils.Resource
import com.myjar.jarassignment.data.model.ComputerItem
import com.myjar.jarassignment.impl.JarRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchedUsecase  @Inject constructor(private val repo : JarRepository, private val searchWord:String, private val computers:List<ComputerItem>){
    operator fun invoke() = flow<Resource<List<ComputerItem>>> {
        emit(Resource.Loading)
        try{
            val result = repo.searchComputer(searchedWord = searchWord, computers = computers)
            emit(result)
        }catch (e:Exception){
            Resource.Failure(e)
        }
    }
}
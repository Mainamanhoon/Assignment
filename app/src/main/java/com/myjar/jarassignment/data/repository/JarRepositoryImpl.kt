package com.myjar.jarassignment.data.repository


import com.example.data.network.utils.SafeApiRequest
import com.myjar.jarassignment.Utils.Resource
import com.myjar.jarassignment.data.api.ApiService
import com.myjar.jarassignment.data.model.ComputerItem
import com.myjar.jarassignment.impl.JarRepository
import javax.inject.Inject


class JarRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : JarRepository , SafeApiRequest() {
    override suspend fun fetchResults(): Resource<List<ComputerItem>> {
        return try {
            val result =  safeApiRequest { apiService.fetchResults() }
            Resource.Success(result)
        }catch (e:Exception){
            Resource.Failure(e)
        }

    }

    override suspend fun searchComputer(computers :List<ComputerItem>, searchedWord:String): Resource<List<ComputerItem>> {
        return try {
            val filteredList = mutableListOf<ComputerItem>()
            for(computer in computers){
                if(computer.name.contains(searchedWord) ||
                    computer.id.contains(searchedWord)||
                    computer.data?.capacity?.contains(searchedWord) == true  ||
                    computer.data?.color?.contains(searchedWord) ==true ||
                    computer.data?.caseSize?.contains(searchedWord)==true
                    ) filteredList.add(computer)
            }
            Resource.Success(computers as ArrayList)
        }catch (e:Exception){
            Resource.Failure(e)
        }
    }
}
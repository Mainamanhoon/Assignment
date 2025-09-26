package com.myjar.jarassignment.impl

import android.widget.ListAdapter
import com.myjar.jarassignment.Utils.Resource
import com.myjar.jarassignment.data.model.ComputerItem
import kotlinx.coroutines.flow.Flow

interface JarRepository {
    suspend fun fetchResults(): Resource<List<ComputerItem>>
    suspend fun searchComputer(computers :List<ComputerItem>, searchedWord:String) : Resource<List<ComputerItem>>
}

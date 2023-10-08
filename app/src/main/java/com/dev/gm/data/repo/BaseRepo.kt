package com.dev.gm.data.repo

import com.dev.gm.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract  class BaseRepo {

    suspend fun <T> invokeApi(
        apiCall: suspend () -> T
    ) : Resource<T> {

        return  withContext(Dispatchers.IO){
            try {
                Resource.Success(apiCall.invoke())
            }catch (throwable : Throwable){
                Resource.Error(error = throwable)
            }
        }
    }

}
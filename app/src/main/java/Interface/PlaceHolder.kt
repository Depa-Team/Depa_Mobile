package Interface

import Beans.Flats
import Beans.History
import retrofit2.Call
import retrofit2.http.GET
import Beans.Usuarios
import retrofit2.http.Path

interface PlaceHolder {

    @GET("users")
    fun getUsers():Call<List<Usuarios>>

    @GET("users/{id}")
    fun getUser(@Path("id")id:Int):Call<Usuarios>

    @GET("manager/{id}/flats")
    fun getFlatsForManager(@Path("id")id:Int):Call<List<Flats>>

    @GET("manager/{id}/history")
    fun getHistoryForManager(@Path("id")id:Int):Call<List<History>>

    @GET("guest/{id}/flats")
    fun getCaseroForGuest(@Path("id")id:Int):Call<List<Flats>>




}
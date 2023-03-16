package Interface

import Beans.Flats
import retrofit2.Call
import retrofit2.http.GET
import Beans.Usuarios
import retrofit2.http.Path

interface PlaceHolder {

    @GET("users")
    fun getUsers():Call<List<Usuarios>>

    @GET("manager/{id}/flats")
    fun getFlatsForManager(@Path("id")id:Int):Call<List<Flats>>


}
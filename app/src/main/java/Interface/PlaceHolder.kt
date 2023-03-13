package Interface

import retrofit2.Call
import retrofit2.http.GET
import Beans.Usuarios

interface PlaceHolder {

    @GET("users")
    fun getUsers():Call<List<Usuarios>>


}
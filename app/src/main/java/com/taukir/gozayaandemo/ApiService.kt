import com.taukir.gozayaandemo.Property
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/")
    suspend fun getProperties(): Response<List<Property>>
}

package vn.bn.teams.appdemo.api


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import vn.bn.teams.appdemo.data.Constants
import vn.bn.teams.appdemo.data.models.BigListResponse
import vn.bn.teams.appdemo.data.models.LoginRequest
import vn.bn.teams.appdemo.data.models.LoginResponse
import vn.bn.teams.appdemo.data.models.RegisterRequest
import vn.bn.teams.appdemo.data.models.UserResponse


interface ApiInterface {

    @POST(Constants.SIGNUP_URL)
    fun signUp(@Body request: RegisterRequest): Call<LoginResponse>

    @POST(Constants.LOGIN_URL)
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET(Constants.LIST_TOP_PIC_URL)
    fun getListTopPic(@Header("x_authorization") token: String): Call<BigListResponse>

    @GET(Constants.USER_INFO_URL)
    fun getUserInfo(@Header("x_authorization") token: String, @Query("username") userName: String): Call<UserResponse>

}
class RetrofitInstance {
    companion object {
        val BASE_URL: String = Constants.BASE_URL

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
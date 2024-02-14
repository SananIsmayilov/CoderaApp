package sananismayilov.coderaapp.data.dependencyinjection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sananismayilov.coderaapp.data.remote.UserAPI
import sananismayilov.coderaapp.data.repository.UserRepositoryImpl
import sananismayilov.coderaapp.domain.repository.UserRepository
import sananismayilov.coderaapp.util.Constants.BASEURL
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): UserAPI {
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
           // .addCallAdapterFactory()
            .build().create(UserAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepositoryImpl(api: UserAPI): UserRepository {
        return UserRepositoryImpl(api)
    }

}
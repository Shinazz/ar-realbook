package com.shinaz.newapp.di
import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.shinaz.newapp.R
import com.shinaz.newapp.network.ApiService
import com.shinaz.newapp.room.UserDao
import com.shinaz.newapp.room.UserRepository
import com.shinaz.newapp.room.UserRoomDB
import com.shinaz.newapp.ui.home.HomeFragmentFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val context: Application){
        @Provides
        fun provideRequestOptions(): RequestOptions {
            return RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
        }


        @Provides
        fun provideGlideInstance(application: Application, requestOptions: RequestOptions): RequestManager {
            return Glide.with(application)
                .setDefaultRequestOptions(requestOptions)
        }

        @Provides
        fun provideAppDrawable(application: Application): Drawable? {
            return ContextCompat.getDrawable(application, R.drawable.ic_launcher_background)
        }

        @Provides
        fun providesUrl() = "http://192.168.1.9:3000/api/"

        @Provides
        @Singleton
        fun providesApiService(url: String) : ApiService =
            Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)

        @Provides
        @Singleton
        fun providesfragmentFactory() = HomeFragmentFactory()

        /**
         * This method is used to provide application component.
         */
        @Provides
        @Singleton
        fun provideContext(): Context {
            return context
        }

        @Provides
        @Singleton
        fun provideUserDatabase(context: Context): UserRoomDB {
            return UserRoomDB.getDatabase(context)
        }
        @Provides
        @Singleton
        fun provideUserRepository(db: UserRoomDB): UserDao {
            return db.userDao()
        }



}
package org.sopt.santamanitto.user

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import org.sopt.santamanitto.preference.UserPreferenceManager
import org.sopt.santamanitto.user.data.source.UserCachedDataSource
import org.sopt.santamanitto.user.data.source.UserDataSource
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class UserModule {

    @SuppressLint("HardwareIds")
    @Provides
    @Named("serialNumber")
    fun provideSerialNumber(@ApplicationContext context: Context) =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    @Provides
    @Singleton
    @Named("cached")
    fun provideUserDataSource(
        @Named("remote") userRemoteDataSource: UserDataSource,
        userPreferenceManager: UserPreferenceManager
    ) : UserDataSource = UserCachedDataSource(userRemoteDataSource, userPreferenceManager)
}
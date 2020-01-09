package com.example.android.dagger.di

import android.content.Context
import com.example.android.dagger.storage.SharedPreferencesStorage
import com.example.android.dagger.storage.Storage
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class RegistrationStorage

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class LoginStorage

// Tells Dagger this is a Dagger module
// Because of @Binds, StorageModule needs to be an abstract class
@Module
/*abstract*/ class StorageModule { //abstract is only used for the @Bind example

//    // Option 1:
//    // Makes Dagger provide SharedPreferencesStorage when a Storage type is requested
//    @Binds
//    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage

//    // Option 2:
//    // @Provides tell Dagger how to create instances of the type that this function
//    // returns (i.e. Storage).
//    // Function parameters are the dependencies of this type (i.e. Context).
//    @Provides
//    fun provideStorage(context: Context): Storage {
//        // Whenever Dagger needs to provide an instance of type Storage,
//        // this code (the one inside the @Provides method) will be run.
//        return SharedPreferencesStorage(context)
//    }

    // You can use the @Provides annotation in Dagger modules to tell Dagger how to provide:
    //  - Implementations of an interface (although @Binds is recommended because it generates less code and therefore it's more efficient).
    //  - Classes that your project doesn't own (e.g. instances of Retrofit).




    // QUALIFIERS

    // Qualifiers are useful when you want to add different implementations of the same type to
    // the Dagger graph. For example, if we wanted different Storage objects to be provided, we
    // could've differentiated them using qualifiers.
    // Qualifiers are recommended because:
    //  - They can be stripped out from Proguard or R8
    //  - You don't need to keep a shared constant for matching the names
    //  - They can be documented
    // For the sake of using Qualifiers example:
    @RegistrationStorage
    @Provides
    fun provideRegistrationStorage(context: Context): Storage {
        return SharedPreferencesStorage("registration", context)
    }

    @LoginStorage
    @Provides
    fun provideLoginStorage(context: Context): Storage {
        return SharedPreferencesStorage("login", context)
    }

    // Examples of how to retrieve qualifiers as dependencies:
    // 1) In a method
    // class ClassDependingOnStorage(@RegistrationStorage private val storage: Storage) { ... }
    //
    // 2) As an injected field
    //class ClassDependingOnStorage {
    //    @Inject @field:RegistrationStorage lateinit var storage: Storage
    //}

}
package com.example.android.dagger.di

import android.content.Context
import com.example.android.dagger.login.LoginComponent
import com.example.android.dagger.registration.RegistrationComponent
import com.example.android.dagger.splash.SplashActivity
import com.example.android.dagger.user.UserManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// Definition of a Dagger component
@Singleton
@Component(modules = [StorageModule::class, AppSubcomponents::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    // 2) Expose UserManager so that anyone (in this case, MainActivity and SettingsActivity)
    // can access a particular instance of UserComponent
    fun userManager(): UserManager

    // Types that can be retrieved from the graph
    // ***(Everything injected in the same component will be shared the same instance and will live in the same lifecycle)
    fun registrationComponent(): RegistrationComponent.Factory
    fun loginComponent(): LoginComponent.Factory

    // Classes that can be injected by this Component
    // ***(Here everything injected will be shared the same instance)
    fun inject(activity: SplashActivity)
}
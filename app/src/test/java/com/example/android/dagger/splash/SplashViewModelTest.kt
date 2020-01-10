package com.example.android.dagger.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android.dagger.user.UserManager
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when` as whenever

class LoginViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SplashViewModel
    private lateinit var userManager: UserManager

    @Before
    fun setup() {
        userManager = mock(UserManager::class.java)
        viewModel = SplashViewModel(userManager)
    }

    @Test
    fun `Is user logged in`() {
        whenever(userManager.isUserLoggedIn()).thenReturn(true)
        val isUserLoggedIn = viewModel.isUserLoggedIn()
        assertEquals(true, isUserLoggedIn)
    }

    @Test
    fun `Is user registered`() {
        whenever(userManager.isUserRegistered()).thenReturn(false)
        val isUserRegistered = viewModel.isUserRegistered()
        assertEquals(false, isUserRegistered)
    }
}

package br.com.projeto.pets.di.module

import br.com.projeto.pets.di.ActivityScoped
import br.com.projeto.pets.features.main.MainActivity
import br.com.projeto.pets.features.main.MainModule
import br.com.projeto.pets.features.create.CreateActivity
import br.com.projeto.pets.features.create.CreateModule
import br.com.projeto.pets.features.filter.FilterActivity
import br.com.projeto.pets.features.filter.FilterModule
import br.com.projeto.pets.features.profile.ProfileActivity
import br.com.projeto.pets.features.profile.ProfileModule
import br.com.projeto.pets.features.pet.PetActivity
import br.com.projeto.pets.features.pet.PetModule
import br.com.projeto.pets.features.splash.SplashActivity
import br.com.projeto.pets.features.splash.SplashModule
import br.com.projeto.pets.features.init.login.LoginActivity
import br.com.projeto.pets.features.init.signup.SignUpActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(LoginModule::class))
    internal abstract fun loginActivity(): LoginActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(SignUpModule::class))
    internal abstract fun signUpActivity(): SignUpActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(MainModule::class,
            FragmentBuilderModule::class))
    internal abstract fun baseActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(SplashModule::class))
    internal abstract fun splashActivity(): SplashActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(PetModule::class))
    internal abstract fun PetActivity(): PetActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(CreateModule::class))
    internal abstract fun CreateActivity(): CreateActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(FilterModule::class,
            FragmentBuilderModule::class))
    internal abstract fun FilterActivity(): FilterActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(ProfileModule::class))
    internal abstract fun ProfileActivity(): ProfileActivity
}

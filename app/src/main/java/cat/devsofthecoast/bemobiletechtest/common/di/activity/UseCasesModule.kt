package cat.devsofthecoast.bemobiletechtest.common.di.activity

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.usecase.RequestTransactionsToEurUseCase
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.usecase.impl.RequestTransactionsToEurUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCasesModule {

    @Binds
    abstract fun requestTransactionsToEurUseCase(
        requestTransactionsToEurUseCaseImpl: RequestTransactionsToEurUseCaseImpl
    ): RequestTransactionsToEurUseCase

}
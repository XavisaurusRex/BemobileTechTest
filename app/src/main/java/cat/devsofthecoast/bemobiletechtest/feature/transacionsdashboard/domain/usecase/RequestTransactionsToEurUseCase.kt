package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.usecase

import cat.devsofthecoast.bemobiletechtest.common.domain.usecase.BaseUseCase
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw.TransactionDataWrapper

interface RequestTransactionsToEurUseCase : BaseUseCase<List<TransactionDataWrapper>> {
    fun setForceRemoteRequest(forceRemoteRequests: Boolean)
}
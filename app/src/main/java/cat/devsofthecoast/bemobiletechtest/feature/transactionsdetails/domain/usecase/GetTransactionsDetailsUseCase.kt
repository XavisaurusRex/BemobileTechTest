package cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.domain.usecase

import cat.devsofthecoast.bemobiletechtest.common.domain.usecase.BaseUseCase
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.adapter.dw.TransactionDetailsDataWrapper

interface GetTransactionsDetailsUseCase : BaseUseCase<List<TransactionDetailsDataWrapper>> {
    fun setSkuRefCode(skuRef: String)
}
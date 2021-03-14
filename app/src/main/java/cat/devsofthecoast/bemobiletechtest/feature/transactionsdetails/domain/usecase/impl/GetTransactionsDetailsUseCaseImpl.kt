package cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.domain.usecase.impl

import cat.devsofthecoast.bemobiletechtest.common.data.remote.AsyncResult
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.data.repository.TransactionDetailsRepository
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.domain.usecase.GetTransactionsDetailsUseCase
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.adapter.dw.TransactionDetailsDataWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionsDetailsUseCaseImpl @Inject constructor(
    private val repository: TransactionDetailsRepository
) : GetTransactionsDetailsUseCase {

    private var skuRefCode: String? = null

    override fun setSkuRefCode(skuRef: String) {
        skuRefCode = skuRef
    }

    override suspend fun execute(): Flow<AsyncResult<List<TransactionDetailsDataWrapper>>> {
        return repository.getTransactionsDetails(skuRefCode!!)
            .flow()
    }

}
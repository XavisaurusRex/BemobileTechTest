package cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.domain.mapper

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.ModelMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.adapter.dw.TransactionDetailsDataWrapper

interface TransactionDetailsMapper :
    ModelMapper<List<Transaction>, List<TransactionDetailsDataWrapper>>
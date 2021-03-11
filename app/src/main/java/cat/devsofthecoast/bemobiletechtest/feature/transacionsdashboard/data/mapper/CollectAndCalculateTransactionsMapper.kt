package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.model.ApiConversionRate
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails

interface CollectAndCalculateTransactionsMapper :
    ModelMapper<Pair<List<ApiConversionRate>, List<Transaction>>, List<TransactionDetails>>
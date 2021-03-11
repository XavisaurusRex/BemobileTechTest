package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiConversionRate
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiTransaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails

interface CollectAndCalculateTransactionsMapper :
    ModelMapper<Pair<List<ApiConversionRate>, List<ApiTransaction>>, List<TransactionDetails>>
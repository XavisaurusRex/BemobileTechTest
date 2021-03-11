package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.ConversionRates
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw.TransactionDataWrapper

interface CollectAndCalculateTransactionsMapper :
    ModelMapper<Pair<ConversionRates, List<Transaction>>, List<TransactionDataWrapper>>
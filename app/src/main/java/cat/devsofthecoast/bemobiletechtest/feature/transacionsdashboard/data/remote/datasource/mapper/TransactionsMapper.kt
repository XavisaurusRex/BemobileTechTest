package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.model.Transaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiTransaction

interface TransactionListMapper: ModelMapper<List<ApiTransaction>, List<Transaction>>
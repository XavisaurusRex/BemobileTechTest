package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.model.ApiTransaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction

interface RemoteTransactionListMapper : ModelMapper<List<ApiTransaction>, List<Transaction>>
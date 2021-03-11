package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails

interface ModelMapper<FromModel, ToModel> {

    fun mapToBo(from: FromModel): ToModel

}

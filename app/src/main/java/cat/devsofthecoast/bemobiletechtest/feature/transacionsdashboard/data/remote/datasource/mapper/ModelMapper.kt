package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper

interface ModelMapper<FromModel, ToModel> {

    fun mapToBo(from: FromModel): ToModel

}

package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper

interface ModelMapper<FromModel, ToModel> {

    fun mapTo(from: FromModel): ToModel

}

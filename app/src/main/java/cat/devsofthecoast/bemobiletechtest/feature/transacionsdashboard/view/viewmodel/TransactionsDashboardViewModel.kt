package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw.TransactionDataWrapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Currency
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionsDashboardViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _transactions: MutableLiveData<List<TransactionDataWrapper>> =
        savedStateHandle.getLiveData("movements")
    val transactions get() = _transactions

    init {
        requestMovements()
    }

    fun requestMovements() {
        if (transactions.value.isNullOrEmpty()) {
            Log.d("BemobileApp", "movements.value.isNullOrEmpty()")
            _transactions.postValue(
                arrayListOf(
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            12.64,
                            Currency.EUR,
                            1.264
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "T2006",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                    TransactionDataWrapper(
                        TransactionDetails(
                            "TFFFGGD06",
                            10.00,
                            Currency.USD,
                            7.36,
                            Currency.EUR,
                            0.736
                        )
                    ),
                )
            )
        }
    }

}
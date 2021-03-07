package cat.devsofthecoast.bemobiletechtest.common.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class MutableSourceLiveData<Type> : Observer<Type> {

    //region Fields
    private val mediatorLiveData = MediatorLiveData<Type>()
    private var actualSource: LiveData<Type> = MutableLiveData()
    //endregion

    init {
        mediatorLiveData.addSource(actualSource, this)
    }

    //region Public methods
    fun changeSource(source: LiveData<Type>) {
        GlobalScope.launch(Dispatchers.Main) {
            removeSource(actualSource)
            addSource(source)
            actualSource = source
        }
    }

    fun liveData() = mediatorLiveData as LiveData<Type>
    //endregion

    //region Protected methods
    protected open fun removeSource(sourceToRemove: LiveData<Type>) {
        mediatorLiveData.removeSource(sourceToRemove)
    }

    protected open fun addSource(sourceToAdd: LiveData<Type>) {
        mediatorLiveData.addSource(sourceToAdd, this)
    }
    //endregion

    //region override Observer<Type>
    override fun onChanged(value: Type?) {
        mediatorLiveData.postValue(value!!)
    }
    //endregion
}

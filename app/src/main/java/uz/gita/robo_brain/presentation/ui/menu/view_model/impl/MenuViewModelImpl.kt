package uz.gita.robo_brain.presentation.ui.menu.view_model.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.robo_brain.data.pref.impl.SharedPrefImpl
import uz.gita.robo_brain.presentation.ui.menu.view_model.MenuViewModel

class MenuViewModelImpl : MenuViewModel,ViewModel() {

    override val openPuzzle15LiveData: MutableLiveData<Unit> = MutableLiveData()

    override val openQuickMathLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val openTrueFalseLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val openInputMathLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val openSortedMathLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val openTableOfGrowLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val openPuzzle2048LiveData: MutableLiveData<Unit> = MutableLiveData()

    override val openHardMathLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val openSupportLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val openHelpLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val imageLiveData: MutableLiveData<String> =
        MutableLiveData(SharedPrefImpl.getInstance().getImageUri())

    override fun openPuzzle15() {
        openPuzzle15LiveData.postValue(Unit)
    }

    override fun openQuickMath() {
        openQuickMathLiveData.postValue(Unit)
    }

    override fun openTrueFalse() {
        openTrueFalseLiveData.postValue(Unit)
    }

    override fun openInputMath() {
        openInputMathLiveData.postValue(Unit)
    }

    override fun openSortedMath() {
        openSortedMathLiveData.postValue(Unit)
    }

    override fun openTableOfGrow() {
        openTableOfGrowLiveData.postValue(Unit)
    }

    override fun openPuzzle2048() {
        openPuzzle2048LiveData.postValue(Unit)
    }

    override fun openHardMath() {
        openHardMathLiveData.postValue(Unit)
    }

    override fun openSupport() {
        openSupportLiveData.postValue(Unit)
    }

    override fun openHelp() {
       openHelpLiveData.value = Unit
    }
}
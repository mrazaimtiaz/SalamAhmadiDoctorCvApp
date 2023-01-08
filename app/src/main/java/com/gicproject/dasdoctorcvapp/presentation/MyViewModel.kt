package com.gicproject.salamdoctorcvapp.presentation

import android.annotation.SuppressLint
import android.os.Handler
import android.util.Log
import android_serialport_api.SerialPort
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gicproject.dasdoctorcvapp.led.LampsUtil
import com.gicproject.dasdoctorcvapp.led.UartSend
import com.gicproject.salamdoctorcvapp.common.Constants
import com.gicproject.salamdoctorcvapp.common.Constants.Companion.NO_BRANCH_SELECTED
import com.gicproject.salamdoctorcvapp.common.Constants.Companion.NO_COUNTER_SELECTED
import com.gicproject.salamdoctorcvapp.common.Constants.Companion.NO_DEPARTMENT_SELECTED
import com.gicproject.salamdoctorcvapp.common.Resource
import com.gicproject.salamdoctorcvapp.domain.model.Doctor
import com.gicproject.salamdoctorcvapp.domain.model.Ticket
import com.gicproject.salamdoctorcvapp.domain.repository.DataStoreRepository
import com.gicproject.salamdoctorcvapp.domain.use_case.MyUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class MyViewModel @Inject constructor(
    private val surveyUseCases: MyUseCases,
    private val repository: DataStoreRepository
) : ViewModel() {

    private val _selectedCounterName = MutableStateFlow(NO_COUNTER_SELECTED)
    val selectedCounterName: StateFlow<String>
        get() = _selectedCounterName.asStateFlow()

    private val _selectedCounterId = MutableStateFlow("")
    val selectedCounterId: StateFlow<String>
        get() = _selectedCounterId.asStateFlow()

    private val _selectedBranchName = MutableStateFlow(NO_BRANCH_SELECTED)
    val selectedBranchName: StateFlow<String>
        get() = _selectedBranchName.asStateFlow()

    private val _selectedBranchId = MutableStateFlow("")
    val selectedBranchId: StateFlow<String>
        get() = _selectedBranchId.asStateFlow()

    private val _selectedDepartmentName = MutableStateFlow(NO_DEPARTMENT_SELECTED)
    val selectedDepartmentName: StateFlow<String>
        get() = _selectedDepartmentName.asStateFlow()

    private val _selectedDepartmentNameAr = MutableStateFlow(NO_DEPARTMENT_SELECTED)
    val selectedDepartmentNameAr: StateFlow<String>
        get() = _selectedDepartmentNameAr.asStateFlow()

    private val _selectedDepartmentId = MutableStateFlow("")
    val selectedDepartmentId: StateFlow<String>
        get() = _selectedDepartmentId.asStateFlow()

    private val _ticket = mutableStateOf(Ticket(""))
    val ticket: State<Ticket> = _ticket

    private val _doctorDetail = mutableStateOf(Doctor())
    val doctorDetail: State<Doctor> = _doctorDetail

    private val _stateSetting = mutableStateOf(SettingScreenState())
    val stateSetting: State<SettingScreenState> = _stateSetting

    private val _isRefreshingSetting = MutableStateFlow(false)
    val isRefreshingSetting: StateFlow<Boolean>
        get() = _isRefreshingSetting.asStateFlow()

    private val _stateMain = mutableStateOf(MainScreenState())
    val stateMain: State<MainScreenState> = _stateMain

    private val _isDarkTheme = mutableStateOf(false)
    val isDarkTheme: State<Boolean> = _isDarkTheme


    init {
        ///
        var lampUtil : LampsUtil = LampsUtil()
        viewModelScope.launch {
            try{
                greenLamps()

            }catch(e: UnsatisfiedLinkError){
                e.printStackTrace()
            }
        }

        initPreference()
        onEvent(MyEvent.GetTicket)
        onEvent(MyEvent.GetDoctor)
        onEvent(MyEvent.GetSingleDepartment)


    }

    private fun initPreference() {
        viewModelScope.launch {
            initCounterNamePreference()
            initDepartmentNamePreference()
            initBranchNamePreference()
        }
    }
var job: Job? = null
    private fun blinkEffect() {
        job?.cancel()
        job =  viewModelScope.launch {
            val tempTicket = ticket.value.TicketNumber

            val blinkCounter = 4
            var i = 0
            while (i < blinkCounter) {
                i += 1;
                delay(500L)
               // offLamps()
                _stateMain.value = _stateMain.value.copy(ticket = Ticket( "          "))
                delay(500L)
              //  greenLamps()
                _stateMain.value =
                    _stateMain.value.copy(ticket = Ticket( tempTicket))

            }


        }
    }

    private suspend fun initCounterNamePreference() {
        val locationName = repository.getString(Constants.KEY_COUNTER_NAME)
        val locationId = repository.getString(Constants.KEY_COUNTER_ID)

        if (locationId != null) {
            _selectedCounterId.value = locationId
        }
        if (locationName != null) {
            _selectedCounterName.value = locationName
        }
    }

    private suspend fun initBranchNamePreference(setCounter: Boolean = false) {
        val branchName = repository.getString(Constants.KEY_BRANCH_NAME)
        val branchId = repository.getString(Constants.KEY_BRANCH_ID)

        if (branchId != null) {
            _selectedBranchId.value = branchId
        }
        if (branchName != null) {
            _selectedBranchName.value = branchName
        }
        if(setCounter){
            onEvent(MyEvent.GetCounters)
            onEvent(MyEvent.GetDepartments)
        }
    }

    private suspend fun initDepartmentNamePreference() {
        val deptName = repository.getString(Constants.KEY_DEPARTMENT_NAME)
        val deptNameAr = repository.getString(Constants.KEY_DEPARTMENT_NAME_AR)
        val deptId = repository.getString(Constants.KEY_DEPARTMENT_ID)

        if (deptId != null) {
            _selectedDepartmentId.value = deptId
        }
        if (deptName != null) {
            _selectedDepartmentName.value = deptName
        }
        if (deptNameAr != null) {
            _selectedDepartmentNameAr.value = deptNameAr
        }
    }

    fun stateSettingResetAfterBranch(){
       _stateSetting.value = _stateSetting.value.copy(counters = emptyList(), department = emptyList())
    }
    fun saveCounter(counterName: String?, counterId: Int?) {
        viewModelScope.launch {
            if (counterId != null) {
                repository.putString(Constants.KEY_COUNTER_NAME, counterName.toString())
                repository.putString(Constants.KEY_COUNTER_ID, counterId.toString())
                initCounterNamePreference()
            }
        }
    }

    fun saveBranch(branchName: String?, branchId: Int?) {
        viewModelScope.launch {
            if (branchId != null) {
                repository.putString(Constants.KEY_BRANCH_NAME, branchName.toString())
                repository.putString(Constants.KEY_BRANCH_ID, branchId.toString())
                initBranchNamePreference(true)


            }
        }
    }

    fun saveDepartment(departmentName: String?,departmentNameAr: String?, deptId: Int?) {
        viewModelScope.launch {
            if (deptId != null) {
                repository.putString(Constants.KEY_DEPARTMENT_NAME_AR, departmentNameAr.toString())
                repository.putString(Constants.KEY_DEPARTMENT_NAME, departmentName.toString())
                repository.putString(Constants.KEY_DEPARTMENT_ID, deptId.toString())
                initDepartmentNamePreference()
            }
        }
    }


    fun onEvent(event: MyEvent) {
        when (event) {
            is MyEvent.GetBranches -> {
                surveyUseCases.getBranches().onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let {
                                _stateSetting.value = SettingScreenState(branches = it, error = "")
                            }
                        }
                        is Resource.Error -> {
                            _stateSetting.value = SettingScreenState(
                                error = result.message ?: "An unexpected error occurred"
                            )
                        }
                        is Resource.Loading -> {
                            _stateSetting.value = SettingScreenState(isLoading = true)
                        }
                    }
                }.launchIn(viewModelScope)
            }
            is MyEvent.GetDepartments -> {
                if(_selectedBranchId.value.isNotEmpty()){
                    surveyUseCases.getDepartments(selectedBranchId.value).onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                result.data?.let {
                                    _stateSetting.value = _stateSetting.value.copy(department = it, error = "", isLoading = false)
                                }
                            }
                            is Resource.Error -> {
                                _stateSetting.value = _stateSetting.value.copy(
                                    error = result.message ?: "An unexpected error occurred"
                                )
                            }
                            is Resource.Loading -> {
                                _stateSetting.value = _stateSetting.value.copy(isLoading = true)
                            }
                        }
                    }.launchIn(viewModelScope)
                }
            }
            is MyEvent.GetCounters -> {
                if(_selectedBranchId.value.isNotEmpty()){
                    surveyUseCases.getCounters(selectedBranchId.value).onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                result.data?.let {
                                    _stateSetting.value = _stateSetting.value.copy(counters = it, error = "", isLoading = false)
                                }
                            }
                            is Resource.Error -> {
                                _stateSetting.value = _stateSetting.value.copy(
                                    error = result.message ?: "An unexpected error occurred"
                                )
                            }
                            is Resource.Loading -> {
                                _stateSetting.value = _stateSetting.value.copy(isLoading = true)
                            }
                        }
                    }.launchIn(viewModelScope)
                }
            }
            is MyEvent.GetTicket -> {
                Log.d("TAG", "onEvent: called getticket")
                if(_selectedBranchId.value.isNotEmpty() && _selectedCounterId.value.isNotEmpty()){
                    surveyUseCases.getTicket(_selectedBranchId.value,_selectedCounterId.value).onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                result.data?.let {
                                    if(it.Status != null){
                                        if(it.Status == "0"){
                                            it.TicketNumber = ""
                                        }
                                    }
                                    _stateMain.value =  _stateMain.value.copy(ticket = it, error = "")
                                    if(
                                        _ticket.value.TicketNumber != it.TicketNumber){
                                        _ticket.value = it
                                            //  blinkEffect()

                                    }else{
                                        _ticket.value = it
                                    }
                                }
                                delay(1000)
                                onEvent(MyEvent.GetTicket)
                            }
                            is Resource.Error -> {
                                _stateMain.value =  _stateMain.value.copy(
                                    error = result.message ?: "An unexpected error occurred"
                                )
                                delay(500)
                                onEvent(MyEvent.GetTicket)
                            }
                            is Resource.Loading -> {
                            }
                        }
                    }.launchIn(viewModelScope)
                }else{
                    viewModelScope.launch {
                        delay(1000)
                        onEvent(MyEvent.GetTicket)
                    }

                }
            }
            is MyEvent.GetDoctor -> {
                Log.d("TAG", "onEvent: called getticket")
                if(_selectedBranchId.value.isNotEmpty() && _selectedCounterId.value.isNotEmpty()){
                    surveyUseCases.getDoctor(_selectedBranchId.value,_selectedCounterId.value).onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                result.data?.let {
                                    _stateMain.value =  _stateMain.value.copy(doctorDetail = it, error = "")
                                    if(
                                        _doctorDetail.value != it){
                                        _doctorDetail.value = it
                                    }
                                }
                                delay(1000)
                                onEvent(MyEvent.GetDoctor)
                            }
                            is Resource.Error -> {
                                _stateMain.value =  _stateMain.value.copy(
                                    error = result.message ?: "An unexpected error occurred getting doctor details"
                                )
                                delay(500)
                                onEvent(MyEvent.GetDoctor)
                            }
                            is Resource.Loading -> {
                            }
                        }
                    }.launchIn(viewModelScope)
                }else{
                    viewModelScope.launch {
                        delay(1000)
                        onEvent(MyEvent.GetDoctor)
                    }

                }
            }
            is MyEvent.GetSingleDepartment -> {
                if(_selectedBranchId.value.isNotEmpty() && _selectedDepartmentId.value.isNotEmpty()){
                    surveyUseCases.getDepartments(_selectedBranchId.value).onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                result.data?.let {
                                    for(item in it){
                                        try {
                                            if (item.PKID == _selectedDepartmentId.value.toInt()) {
                                                _stateMain.value = _stateMain.value.copy(
                                                    department = item,
                                                    error = ""
                                                )
                                            }
                                        } catch (
                                            e: Exception
                                        ) {
                                            e.printStackTrace()
                                        }
                                    }
                                }
                                delay(3000)
                                onEvent(MyEvent.GetSingleDepartment)
                            }
                            is Resource.Error -> {
                                _stateMain.value =  _stateMain.value.copy(
                                    error = result.message ?: "An unexpected error occurred getting doctor details"
                                )
                                delay(2000)
                                onEvent(MyEvent.GetSingleDepartment)
                            }
                            is Resource.Loading -> {
                            }
                        }
                    }.launchIn(viewModelScope)
                }else{
                    viewModelScope.launch {
                        delay(1000)
                        onEvent(MyEvent.GetSingleDepartment)
                    }

                }
            }
            else -> {

            }
        }
    }

    fun changeTheme() {
        _isDarkTheme.value = !_isDarkTheme.value
    }

    //light
    @Throws(IOException::class)
    fun blueLamps() {

        val runnableLoop: Runnable = object : Runnable {
            @SuppressLint("SuspiciousIndentation")
            override fun run() {
                try {
                    val ttyS1 = SerialPort(File("/dev/ttyS1"), 115200, 0)
                    UartSend.UartAllB(ttyS1, "ttyS1").run()

                } catch (e: SecurityException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        runnableLoop.run()
    }

    @Throws(IOException::class)
    fun greenLamps() {
        val runnableLoop: Runnable = object : Runnable {
            //LOOP thread
            @SuppressLint("SuspiciousIndentation")
            override fun run() {
                try {
                    val ttyS1 = SerialPort(File("/dev/ttyS1"), 115200, 0)
                    UartSend.UartAllG(ttyS1, "ttyS1").run()

                } catch (e: SecurityException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        runnableLoop.run()
    }

    @Throws(IOException::class)
    fun redLamps() {
        val runnableLoop: Runnable = object : Runnable {
            //LOOP thread
            @SuppressLint("SuspiciousIndentation")
            override fun run() {
                try {
                    var  ttyS1 = SerialPort(File("/dev/ttyS1"), 115200, 0)
                    UartSend.UartAllR(ttyS1, "ttyS1").run()

                } catch (e: SecurityException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        runnableLoop.run()
    }

    @Throws(IOException::class)
    fun yellowLamps() {
        val handlerLoop = Handler()
        val runnableLoop: Runnable = object : Runnable {
            @SuppressLint("SuspiciousIndentation")
            override fun run() {
                try {
                    Log.d("TAG", "run: called run")
                    var  ttyS1 = SerialPort(File("/dev/ttyS1"), 115200, 0)
                    UartSend.UartAllG(ttyS1, "ttyS1").run()

                } catch (e: SecurityException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        runnableLoop.run()



    }

    fun offLamps() {
        viewModelScope.launch {
            try {
                var ttyS1 = SerialPort(File("/dev/ttyS1"), 115200, 0)
                UartSend.UartAllOff(ttyS1, "ttyS1").run()
            } catch (e: SecurityException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }


    }

}





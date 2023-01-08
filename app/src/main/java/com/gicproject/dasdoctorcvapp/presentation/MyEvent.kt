package com.gicproject.salamdoctorcvapp.presentation


sealed class MyEvent {
     object GetBranches: MyEvent()
     object GetCounters: MyEvent()
     object GetDepartments: MyEvent()
     object GetTicket: MyEvent()
     object GetDoctor: MyEvent()
     object GetSingleDepartment: MyEvent()
}


package com.gicproject.salamdoctorcvapp.domain.model

import com.gicproject.salamdoctorcvapp.domain.model.Branch
import com.gicproject.salamdoctorcvapp.domain.model.Counter
import com.google.gson.annotations.SerializedName

data class Department(
    var PKID: Int? = null,
    var DepartmentNameEn: String? = null,
    var DepartmentNameAr: String? = null,
    var PointXScreen: Int? = null,
    var PointYScreen: Int? = null,
    var FontSizeScreen: Int? = null,
    var FontColorScreen: String? = null,
     var FontNameScreen: String? = null,
)




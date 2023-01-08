package com.gicproject.salamdoctorcvapp.data.remote.dto

import com.gicproject.salamdoctorcvapp.domain.model.Branch
import com.gicproject.salamdoctorcvapp.domain.model.Counter
import com.gicproject.salamdoctorcvapp.domain.model.Department
import com.gicproject.salamdoctorcvapp.domain.model.Doctor
import com.google.gson.annotations.SerializedName

data class DoctorDto(
    @SerializedName("Doctor_PK_ID") var PKID: Int? = null,
    @SerializedName("Doctor_Name") var DoctorName: String? = null,
    @SerializedName("Doctor_DisplayName_En") var DoctorDisplayNameEn: String? = null,
    @SerializedName("Doctor_DisplayName_Ar") var DoctorDisplayNameAr: String? = null,
    @SerializedName("Doctor_Speciality_En") var DoctorSpecialityEn: String? = null,
    @SerializedName("Doctor_Speciality_Ar") var DoctorSpecialityAr: String? = null,
    @SerializedName("Doctor_Description_En") var DoctorDescriptionEn: String? = null,
    @SerializedName("Doctor_Description_Ar") var DoctorDescriptionAr: String? = null,
    @SerializedName("Doctor_Logo") var DoctorLogo: String? = null,
    @SerializedName("Doctor_DisplayName_En_FontSIze") var DoctorDisplayNameEnFontSIze: Int? = null,
    @SerializedName("Doctor_DisplayName_Ar_FontSize") var DoctorDisplayNameArFontSize: Int? = null,
    @SerializedName("Doctor_DisplayName_En_FontColor") var DoctorDisplayNameEnFontColor: String? = null,
    @SerializedName("Doctor_DisplayName_Ar_FontColor") var DoctorDisplayNameArFontColor: String? = null,
    @SerializedName("Doctor_DisplayName_En_FontName") var DoctorDisplayNameEnFontName: String? = null,
    @SerializedName("Doctor_DisplayName_Ar_FontName") var DoctorDisplayNameArFontName: String? = null,
    @SerializedName("Doctor_Speciality_En_FontColor") var DoctorSpecialityEnFontColor: String? = null,
    @SerializedName("Doctor_Speciality_Ar_FontColor") var DoctorSpecialityArFontColor: String? = null,
    @SerializedName("Doctor_Speciality_En_FontSize") var DoctorSpecialityEnFontSize: Int? = null,
    @SerializedName("Doctor_Speciality_Ar_FontSize") var DoctorSpecialityArFontSize: Int? = null,
    @SerializedName("Doctor_Speciality_En_FontName") var DoctorSpecialityEnFontName: String? = null,
    @SerializedName("Doctor_Speciality_Ar_FonrName") var DoctorSpecialityArFonrName: String? = null,
    @SerializedName("Doctor_Description_En_FontName") var DoctorDescriptionEnFontName: String? = null,
    @SerializedName("Doctor_Description_Ar_FontName") var DoctorDescriptionArFontName: String? = null,
    @SerializedName("Doctor_Description_En_FontSize") var DoctorDescriptionEnFontSize: Int? = null,
    @SerializedName("Doctor_Description_Ar_FontSize") var DoctorDescriptionArFontSize: Int? = null,
    @SerializedName("Doctor_Description_En_FontColor") var DoctorDescriptionEnFontColor: String? = null,
    @SerializedName("Doctor_Description_Ar_FontColor") var DoctorDescriptionArFontColor: String? = null,
){
    fun toDoctor(): Doctor {
        return Doctor(
            PKID = PKID,
            DoctorName = DoctorName,
            DoctorDisplayNameEn = DoctorDisplayNameEn,
            DoctorDisplayNameAr = DoctorDisplayNameAr,
            DoctorSpecialityEn = DoctorSpecialityEn,
            DoctorSpecialityAr = DoctorSpecialityAr,
            DoctorDescriptionEn = DoctorDescriptionEn,
            DoctorDescriptionAr = DoctorDescriptionAr,
            DoctorLogo = DoctorLogo,
            DoctorDisplayNameEnFontSIze = DoctorDisplayNameEnFontSIze,
            DoctorDisplayNameArFontSize = DoctorDisplayNameArFontSize,
            DoctorDisplayNameEnFontColor = DoctorDisplayNameEnFontColor,
            DoctorDisplayNameArFontColor = DoctorDisplayNameArFontColor,
            DoctorDisplayNameEnFontName = DoctorDisplayNameEnFontName,
            DoctorDisplayNameArFontName = DoctorDisplayNameArFontName,
            DoctorSpecialityEnFontColor = DoctorSpecialityEnFontColor,
            DoctorSpecialityArFontColor = DoctorSpecialityArFontColor,
            DoctorSpecialityEnFontSize = DoctorSpecialityEnFontSize,
            DoctorSpecialityArFontSize = DoctorSpecialityArFontSize,
            DoctorSpecialityEnFontName = DoctorSpecialityEnFontName,
            DoctorSpecialityArFonrName = DoctorSpecialityArFonrName,
            DoctorDescriptionEnFontName = DoctorDescriptionEnFontName,
            DoctorDescriptionArFontName = DoctorDescriptionArFontName,
            DoctorDescriptionEnFontSize = DoctorDescriptionEnFontSize,
            DoctorDescriptionArFontSize = DoctorDescriptionArFontSize,
            DoctorDescriptionEnFontColor = DoctorDescriptionEnFontColor,
            DoctorDescriptionArFontColor = DoctorDescriptionArFontColor
        )
    }
}





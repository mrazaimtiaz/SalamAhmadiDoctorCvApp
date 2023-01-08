package com.gicproject.salamdoctorcvapp.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gicproject.salamdoctorcvapp.R
import com.gicproject.salamdoctorcvapp.Screen

@OptIn(ExperimentalTextApi::class)
@Composable
fun MainScreenNew(
    navController: NavController,
    viewModel: MyViewModel,
) {
    val state = viewModel.stateMain.value
    var isNavigateSetting = true

    val branchId by viewModel.selectedBranchId.collectAsState()
    val counterId by viewModel.selectedCounterId.collectAsState()

    val deptNameEn by viewModel.selectedDepartmentName.collectAsState()
    val deptNameAr by viewModel.selectedDepartmentNameAr.collectAsState()



    val fontName ="GE_SS_Two_Bold"

    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val fontFamily = FontFamily(
        androidx.compose.ui.text.googlefonts.Font(googleFont = GoogleFont(fontName),provider))
    val arabicBold = TextStyle(fontFamily = fontFamily)
//FontFamily(Font(R.font.ge_bold))
  //  val arabicBoldUp = TextStyle(fontFamily = FontFamily(Font(R.font.ge_bold_up)))
//    val arabicLight = TextStyle(fontFamily = FontFamily(Font(R.font.ge_light)))
 //   val bitterLight = TextStyle(fontFamily = FontFamily(Font(R.font.bitter_regular)))
  //  val bitterBold = TextStyle(fontFamily = FontFamily(Font(R.font.bitter_bold)))
/*    var DoctorDisplayNameEnFontSIze: TextUnit =  64.sp
    var DoctorDisplayNameArFontSize: TextUnit =  64.sp
    var DoctorSpecialityEnFontSize: TextUnit =  30.sp
    var DoctorSpecialityArFontSize: TextUnit = 30.sp*/

    var DoctorDisplayNameEnFontSIze: TextUnit =  with(LocalDensity.current) {
        state.doctorDetail.DoctorDisplayNameEnFontSIze?.toSp() ?: 60.toSp()
    }
    var DoctorDisplayNameArFontSize: TextUnit =  with(LocalDensity.current) {
        state.doctorDetail.DoctorDisplayNameArFontSize?.toSp() ?: 60.toSp()
    }
    var DoctorSpecialityEnFontSize: TextUnit =  with(LocalDensity.current) {
        state.doctorDetail.DoctorSpecialityEnFontSize?.toSp() ?: 28.toSp()
    }
    var DoctorSpecialityArFontSize: TextUnit =  with(LocalDensity.current) {
        state.doctorDetail.DoctorSpecialityArFontSize?.toSp() ?: 28.toSp()
    }
    var DoctorDescriptionEnFontSize: TextUnit =  with(LocalDensity.current) {
        state.doctorDetail.DoctorDescriptionEnFontSize?.toSp() ?: 20.toSp()
    }
    var DoctorDescriptionArFontSize: TextUnit =  with(LocalDensity.current) {
        state.doctorDetail.DoctorDescriptionArFontSize?.toSp() ?: 20.toSp()
    }



    var DoctorDisplayNameEnFontColor: Color = state.doctorDetail.DoctorDisplayNameEnFontColor?.color ?: "#ffffff".color
    var DoctorDisplayNameArFontColor: Color = state.doctorDetail.DoctorDisplayNameArFontColor?.color ?: "#ffffff".color
    var DoctorSpecialityEnFontColor: Color = state.doctorDetail.DoctorSpecialityEnFontColor?.color ?: "#ffffff".color
    var DoctorSpecialityArFontColor: Color = state.doctorDetail.DoctorSpecialityArFontColor?.color ?: "#ffffff".color
   // var DoctorDescriptionEnFontColor: Color = state.doctorDetail.DoctorDescriptionEnFontColor?.color ?: "#ffffff".color
//    var DoctorDescriptionArFontColor: Color = state.doctorDetail.DoctorDescriptionArFontColor?.color ?: "#ffffff".color


    val DoctorDisplayNameEnFontName =state.doctorDetail.DoctorDisplayNameEnFontName ?: fontName
    lateinit var fontFamilyDoctorDisplayNameEnFontName : FontFamily
    if(DoctorDisplayNameEnFontName != "GE_SS_Two_Bold"){
        fontFamilyDoctorDisplayNameEnFontName =  FontFamily(
            androidx.compose.ui.text.googlefonts.Font(googleFont = GoogleFont(DoctorDisplayNameEnFontName),provider))
    }else{
        fontFamilyDoctorDisplayNameEnFontName=  FontFamily(Font(R.font.ge_bold))
    }


    val DoctorDisplayNameArFontName =state.doctorDetail.DoctorDisplayNameArFontName ?: fontName
    lateinit var fontFamilyDoctorDisplayNameArFontName : FontFamily
    if(DoctorDisplayNameArFontName != "GE_SS_Two_Bold"){
        fontFamilyDoctorDisplayNameArFontName =  FontFamily(
            androidx.compose.ui.text.googlefonts.Font(googleFont = GoogleFont(DoctorDisplayNameArFontName),provider))
    }else{
        fontFamilyDoctorDisplayNameArFontName=  FontFamily(Font(R.font.ge_bold))
    }



    val DoctorSpecialityEnFontName =state.doctorDetail.DoctorSpecialityEnFontName ?: fontName
    lateinit var fontFamilyDoctorSpecialityEnFontName : FontFamily
    if(DoctorDisplayNameArFontName != "GE_SS_Two_Bold"){
        fontFamilyDoctorSpecialityEnFontName =  FontFamily(
            androidx.compose.ui.text.googlefonts.Font(googleFont = GoogleFont(DoctorSpecialityEnFontName),provider))
    }else{
        fontFamilyDoctorSpecialityEnFontName=  FontFamily(Font(R.font.ge_bold))
    }


    val DoctorSpecialityArFonrName =state.doctorDetail.DoctorSpecialityArFonrName ?: fontName
    lateinit var fontFamilyDoctorSpecialityArFonrName : FontFamily
    if(DoctorSpecialityArFonrName != "GE_SS_Two_Bold"){
        fontFamilyDoctorSpecialityArFonrName =  FontFamily(
            androidx.compose.ui.text.googlefonts.Font(googleFont = GoogleFont(DoctorSpecialityArFonrName),provider))
    }else{
        fontFamilyDoctorSpecialityArFonrName=  FontFamily(Font(R.font.ge_bold))
    }


    val DoctorDescriptionEnFontName =state.doctorDetail.DoctorDescriptionEnFontName ?: fontName
    lateinit var fontFamilyDoctorDescriptionEnFontName : FontFamily
    if(DoctorSpecialityArFonrName != "GE_SS_Two_Bold"){
        fontFamilyDoctorDescriptionEnFontName =  FontFamily(
            androidx.compose.ui.text.googlefonts.Font(googleFont = GoogleFont(DoctorDescriptionEnFontName),provider))
    }else{
        fontFamilyDoctorDescriptionEnFontName=  FontFamily(Font(R.font.ge_bold))
    }

    val DoctorDescriptionArFontName =state.doctorDetail.DoctorDescriptionArFontName ?: fontName
    lateinit var fontFamilyDoctorDescriptionArFontName : FontFamily
    if(DoctorSpecialityArFonrName != "GE_SS_Two_Bold"){
        fontFamilyDoctorDescriptionArFontName =  FontFamily(
            androidx.compose.ui.text.googlefonts.Font(googleFont = GoogleFont(DoctorDescriptionArFontName),provider))
    }else{
        fontFamilyDoctorDescriptionArFontName=  FontFamily(Font(R.font.ge_bold))
    }

    val PointXScreen =state.department.PointXScreen ?: 1570
    val PointYScreen =state.department.PointYScreen ?: 100


    val DepartmentFontName =state.department.FontNameScreen ?: fontName
    lateinit var fontFamilyDepartmentFontName : FontFamily
    if(DoctorSpecialityArFonrName != "GE_SS_Two_Bold"){
        fontFamilyDepartmentFontName =  FontFamily(
            androidx.compose.ui.text.googlefonts.Font(googleFont = GoogleFont(DepartmentFontName),provider))
    }else{
        fontFamilyDepartmentFontName=  FontFamily(Font(R.font.ge_bold))
    }

    var DepartmentFontSizeScreen: TextUnit =  with(LocalDensity.current) {
        state.department.FontSizeScreen?.toSp() ?: 30.toSp()
    }

    var DepartmentFontColorScreen: Color = state.department.FontColorScreen?.color ?: MaterialTheme.colors.primary



    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colors.surface,
                )
        ) {
            Modifier.padding(innerPadding)
                Row(
                    modifier = Modifier.fillMaxSize()
                ){
                    Box(  modifier = Modifier
                        .fillMaxSize()
                        .weight(2f)) {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ){
                            Row() {
                                Image(
                                    painter = painterResource(R.drawable.bgpartone),
                                    contentScale = ContentScale.FillBounds,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .weight(1.0f)
                                )
                            }
                        }
                        Column( modifier = Modifier
                            .fillMaxSize()) {
                            Row(horizontalArrangement = Arrangement.End, modifier = Modifier
                                .fillMaxWidth()) {
                                Card(
                                    shape =  RoundedCornerShape(
                                        topStartPercent = 20,
                                        bottomStartPercent = 20,
                                    )
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.logowhite),
                                        contentScale = ContentScale.FillBounds,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .pointerInput(Unit) {
                                                detectDragGestures { change, _ ->
                                                    if (change.position.y > 400) {
                                                        if (isNavigateSetting) {
                                                            isNavigateSetting = false
                                                            navController.navigate(Screen.SettingScreen.route)
                                                        }
                                                    }
                                                    change.consume()
                                                }
                                            }
                                            .size(340.dp, 120.dp)
                                            .padding(10.dp)
                                    )
                                }


                            }

                            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 0.dp)) {
                                Text(
                                    state.doctorDetail.DoctorDisplayNameAr ?: "",
                                    textAlign = TextAlign.Left,
                                    style = TextStyle(fontFamily =fontFamilyDoctorDisplayNameArFontName),
                                    fontSize = DoctorDisplayNameArFontSize,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = DoctorDisplayNameArFontColor
                                )
                                Spacer(modifier = Modifier.height(7.dp))
                                Text(
                                    state.doctorDetail.DoctorDisplayNameEn ?: "",
                                    textAlign = TextAlign.Left,
                                    style = TextStyle(fontFamily =fontFamilyDoctorDisplayNameEnFontName),
                                    fontSize = DoctorDisplayNameEnFontSIze,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = DoctorDisplayNameEnFontColor
                                )
                                Spacer(modifier = Modifier.height(15.dp))
                                Text(
                                    state.doctorDetail.DoctorSpecialityAr ?: "",
                                    textAlign = TextAlign.Left,
                                    style = TextStyle(fontFamily =fontFamilyDoctorSpecialityArFonrName),
                                    fontSize = DoctorSpecialityArFontSize,
                                    fontWeight = FontWeight.Bold,
                                    color = DoctorSpecialityArFontColor
                                )
                                Spacer(modifier = Modifier.height(7.dp))
                                Text(
                                    state.doctorDetail.DoctorSpecialityEn ?: "",
                                    textAlign = TextAlign.Left,
                                    style = TextStyle(fontFamily =fontFamilyDoctorSpecialityEnFontName),
                                    fontSize = DoctorSpecialityEnFontSize,
                                    fontWeight = FontWeight.Bold,
                                    color = DoctorSpecialityEnFontColor
                                )
                                Spacer(modifier = Modifier.height(22.dp))
                                Column(modifier = Modifier
                                    .width(500.dp)
                                    .clip(RoundedCornerShape(15))
                                    .background(color = Color.White)
                                    .padding(horizontal = 40.dp, vertical = 10.dp),) {
                                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){

                                        Text("N.O", fontSize = 50.sp,style = arabicBold, color = MaterialTheme.colors.primary, fontWeight = FontWeight.ExtraBold)
                                        Text("رقم", fontSize = 50.sp,style = arabicBold, color =  MaterialTheme.colors.primary, fontWeight = FontWeight.ExtraBold)
                                    }
                                    Text(
                                        state.ticket.TicketNumber?.trim()  ?: "",
                                        color = MaterialTheme.colors.primary,
                                        fontSize = 178.sp,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.ExtraBold,
                                    )

                                }
                            }
                        }
                    }
                    Column(Modifier
                        .fillMaxSize().padding(start=10.dp)
                        .weight(1f)) {
                        Card(Modifier
                            .fillMaxSize(), shape = RoundedCornerShape(bottomStart = 100.dp,topStart = 100.dp, bottomEnd = 20.dp),) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,) {
                                var bitmap: ImageBitmap? = null
                                if (state.doctorDetail.DoctorLogo != null) {
                                    try {
                                        bitmap = state.doctorDetail.DoctorLogo!!.toBitmap().asImageBitmap()
                                    } catch (e: java.lang.Exception) {
                                        bitmap = null
                                    }
                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    /* modifier = Modifier
                                         .offset(x = with(LocalDensity.current) {
                                             PointXScreen.toDp()
                                         }, y =with(LocalDensity.current) {
                                             PointYScreen.toDp()
                                         })*/
                                ){
                                    //DepartmentFontSizeScreen
                                    //DepartmentFontSizeScreen
                                    //DepartmentFontColorScreen
                                    Text(  "         " + deptNameEn +  "         ", fontSize = DepartmentFontSizeScreen, style = TextStyle(fontFamily =fontFamilyDepartmentFontName), color = DepartmentFontColorScreen , fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                                    Spacer(modifier = Modifier.height(20.dp))
                                    Text(  "         " + deptNameAr + "         ", fontSize = DepartmentFontSizeScreen,style = TextStyle(fontFamily =fontFamilyDepartmentFontName), color =DepartmentFontColorScreen, fontWeight = FontWeight.Bold,textAlign = TextAlign.Center)
                                }

                                bitmap?.let {
                                    Image(
                                        bitmap = it,
                                        contentDescription = "logo",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(400.dp)
                                            .padding(30.dp)
                                        /* .border(
                                             4.dp,
                                             MaterialTheme.colors.primary,
                                             shape = RoundedCornerShape(10.dp)
                                         )*/
                                    )
                                }
                            }
                        }
                    }


                }



            if (state.error.isNotBlank()) {
                ErrorMsg(errorMsg = state.error)
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}





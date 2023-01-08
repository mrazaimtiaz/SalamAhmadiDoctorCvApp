package com.gicproject.salamdoctorcvapp.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gicproject.salamdoctorcvapp.R
import com.gicproject.salamdoctorcvapp.Screen

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MyViewModel,
) {
    val state = viewModel.stateMain.value
    var isNavigateSetting = true

    val branchId by viewModel.selectedBranchId.collectAsState()
    val counterId by viewModel.selectedCounterId.collectAsState()

    val deptNameEn by viewModel.selectedDepartmentName.collectAsState()
    val deptNameAr by viewModel.selectedDepartmentNameAr.collectAsState()

    val arabicBold = TextStyle(fontFamily = FontFamily(Font(R.font.ge_bold)))
    val arabicBoldUp = TextStyle(fontFamily = FontFamily(Font(R.font.ge_bold_up)))
    val arabicLight = TextStyle(fontFamily = FontFamily(Font(R.font.ge_light)))
    val bitterLight = TextStyle(fontFamily = FontFamily(Font(R.font.bitter_regular)))
    val bitterBold = TextStyle(fontFamily = FontFamily(Font(R.font.bitter_bold)))

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colors.surface,
                )
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
            ){
                Row() {
                    Image(
                        painter = painterResource(R.drawable.salambackground),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1.0f)
                    )
                }

            }
            Modifier.padding(innerPadding)
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Column(modifier = Modifier.fillMaxSize()) {

                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)) {
                        Column(modifier = Modifier
                            .clip(RoundedCornerShape(0.dp, 35.dp, 35.dp, 0.dp))
                            .background(color = MaterialTheme.colors.primary),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(20.dp)
                            ){
                                Text(  "         " + deptNameEn +  "         ", fontSize = 39.sp, style = bitterBold, color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(  "         " + deptNameAr + "         ", fontSize = 39.sp,style = arabicBold, color = Color.White, fontWeight = FontWeight.Bold,textAlign = TextAlign.Center)
                            }
                        }
                        Image(
                            painter = painterResource(R.drawable.daslogobg),
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
                                .size(400.dp, 100.dp)
                        )
                    }
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxSize()
                            .weight(4.0f),
                            horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            Column(
                                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1.0f)
                                    .padding(10.dp)) {
                            }
                            Column(
                                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start,
                                modifier = Modifier
                                    .weight(2.0f)
                                    .padding(10.dp)) {
                                Text(state.doctorDetail.DoctorDisplayNameEn.toString(), textAlign = TextAlign.Left,
                                    style = bitterBold,
                                fontSize = 38.sp, fontWeight = FontWeight.Bold )
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(state.doctorDetail.DoctorSpecialityEn.toString(), textAlign = TextAlign.Left,
                                    style = bitterBold,
                                    fontSize = 25.sp, fontWeight = FontWeight.Bold )
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(state.doctorDetail.DoctorDescriptionEn.toString(), textAlign = TextAlign.Left,
                                    style = bitterLight,
                                    fontSize = 19.sp)
                            }
                            Column(
                                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.End,
                                modifier = Modifier
                                    .weight(2.0f)
                                    .padding(10.dp)) {
                                Text(state.doctorDetail.DoctorDisplayNameAr.toString(), textAlign = TextAlign.Right,
                                    style = arabicBold,
                                    fontSize = 38.sp, fontWeight = FontWeight.Bold )
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(state.doctorDetail.DoctorSpecialityAr.toString(), textAlign = TextAlign.Right,
                                    style = arabicBold,
                                    fontSize = 25.sp, fontWeight = FontWeight.Bold )
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(state.doctorDetail.DoctorDescriptionAr.toString(), textAlign = TextAlign.Right,
                                    style = arabicLight,
                                    fontSize = 19.sp)
                            }
                            Column(
                                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                .weight(2.0f)) {
                                var bitmap: ImageBitmap? = null
                                if (state.doctorDetail.DoctorLogo != null) {
                                    try {
                                        bitmap = state.doctorDetail.DoctorLogo!!.toBitmap().asImageBitmap()
                                    } catch (e: java.lang.Exception) {
                                        bitmap = null
                                    }
                                }
                                bitmap?.let {
                                    Image(
                                        bitmap = it,
                                        contentDescription = "logo",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .size(350.dp, 420.dp)
                                            .border(
                                                4.dp,
                                                MaterialTheme.colors.primary,
                                                shape = RoundedCornerShape(10.dp)
                                            )

                                    )
                                }

                            }
                        }
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .weight(5.0f),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically){

                            Column(modifier = Modifier
                                .width(1000.dp)
                                .clip(RoundedCornerShape(25.dp))
                                .background(color = Color(0xFFff7f27)),) {
                                Row(modifier = Modifier
                                    .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically
                                ){
                                    Text("Number", fontSize = 60.sp,style = arabicBold, color = Color.White, fontWeight = FontWeight.Bold)
                                    Text("الرقم", fontSize = 60.sp,style = bitterBold, color = Color.White, fontWeight = FontWeight.Bold)
                                }
                                Row(){
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            state.ticket.TicketNumber.toString(),
                                            color = Color.White,
                                            fontSize = 238.sp,
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier
                                                .border(
                                                    2.dp,
                                                    Color.White,
                                                    shape = RoundedCornerShape(10.dp)
                                                )
                                                .padding(0.dp, 0.dp, 40.dp)
                                                .width(600.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                            }

                        }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.0f),){

                    }


                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)
                ) {
                    if (state.isLoading) {
                        LinearProgressIndicator()
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


@Composable
fun LogoImage(image: Int, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painterResource(image),
            contentDescription = "ghp logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(80.dp, 80.dp)
        )
    }

}




fun String.toBitmap(): Bitmap {
    Base64.decode(this, Base64.DEFAULT).apply {
        return BitmapFactory.decodeByteArray(this, 0, size)
    }
}

val String.color
    get() = Color(android.graphics.Color.parseColor(this))

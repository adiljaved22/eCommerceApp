package com.example.uiproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun GetStarted(
    NavigateToHome:()-> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {


        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            modifier = Modifier
                .width(428.dp)
                .height(680.dp)
                .padding(top = 44.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(15.dp))


        Text(
            text = "Enjoy Your Online\nShopping.",
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp),
            fontFamily = FontFamily(Font(R.font.inter_regular)),
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 30.94.sp,
            letterSpacing = 0.sp,
            color = Color(0xFF1F2937)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Browse through all categories and shop the best furniture for your dream house",
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .width(389.dp),
            fontFamily = FontFamily(Font(R.font.inter_regular)),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 14.sp,
            letterSpacing = 0.sp,
            color = Color(0xFF6B7280)
        )

        Spacer(modifier = Modifier.height(10.dp))


        Button(
            onClick = { NavigateToHome() },
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .width(370.dp)
                .height(54.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2F5D50)
            )
        ) {
            Text(
                text = "Get Started",
                fontFamily = FontFamily(Font(R.font.inter_regular)),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}
package com.example.uiproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun ProductCard(
    item: Product,
    navController: NavController,
) {
    ElevatedCard(
        modifier = Modifier
            .width(193.dp)
            .height(230.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Image(
                painter = painterResource(item.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("Chair1") }
                    .height(120.dp), contentScale = ContentScale.Inside
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Box(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFE65A4E),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 10.dp, vertical = 4.dp),

                    ) {

                    Text(
                        text = "NEW",
                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                        fontSize = 10.sp,
                        lineHeight = 6.sp,
                        letterSpacing = 0.sp,
                        color = Color.White,

                        )
                }


                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "★",
                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                        fontSize = 14.sp,
                        color = Color(0xFFFFC107)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = item.rating,
                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }


            Spacer(modifier = Modifier.height(10.dp))
            Text(
                item.name,
                fontFamily = FontFamily(Font(R.font.inter_regular)),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )




            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    item.price,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF2C4939)
                )
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .background(Color(0xFF416954), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

            }
        }
    }
}
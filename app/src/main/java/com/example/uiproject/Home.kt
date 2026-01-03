package com.example.uiproject

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    onQueryUpdate: (newValue: String) -> Unit
) {
    val categories = listOf(
        "Chair",
        "Table",
        "Sofa",
        "Bed",
        "Lamp",
        "Cupboard",
        "Desk",
        "Shelf"
    )
    val productImages = listOf(
        R.drawable.chair1,
        R.drawable.chair2
    )

    val productNames = listOf(
        "Modern Chair",
        "Minimalist Chair"
    )

    val productPrices = listOf(
        "₹ 12,500",
        "₹ 8,300"
    )

    val productRatings = listOf(
        "4.8",
        "4.0"
    )

    val context = LocalContext.current

    var selectedImage by remember { mutableStateOf<Uri?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    var isSearchExpanded by remember { mutableStateOf(false) }

    val searchBarPadding by animateDpAsState(
        targetValue = if (isSearchExpanded) 0.dp else 20.dp,
        label = "searchPadding"
    )

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        selectedImage = uri
        uri?.let {
            context.contentResolver.takePersistableUriPermission(
                it,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp, vertical = 16.dp)
            .padding(top = 44.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Discover The best\nFurniture",
                fontFamily = FontFamily(Font(R.font.inter_regular)),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp,
                color = Color(0xFF2C4939)
            )

            Image(
                painter = rememberAsyncImagePainter(selectedImage),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0DFDF))
                    .clickable {
                        launcher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }
            )
        }

        SearchBar(
            query = searchQuery,
            onQueryChange = { onQueryUpdate },
            onSearch = { isSearchExpanded = false },
            active = isSearchExpanded,
            onActiveChange = { isSearchExpanded = it },
            leadingIcon = {
                IconButton(onClick = {
                    if (isSearchExpanded) {
                        onQueryUpdate("")
                    }
                    isSearchExpanded = !isSearchExpanded
                }) {
                    val icon = if (isSearchExpanded) Icons.Filled.ArrowBack else Icons.Filled.Search
                    Icon(imageVector = icon, contentDescription = "Search Icon")
                }
            },
            placeholder = { Text(text = "Search furniture") },
            trailingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Filter/Tuning options",
                        tint = Color(0xFF2C4939)
                    )

                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = CircleShape,
            colors = SearchBarDefaults.colors(
                containerColor = Color.White,
                dividerColor = Color.Transparent
            )
        ) {

        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "Categories",
            fontFamily = FontFamily(Font(R.font.inter_regular)),
            fontSize = 20.sp,
            color = Color(0xFF416954)
        )
        Spacer(modifier = Modifier.height(25.dp))
        var selectedCategory by remember { mutableStateOf<String?>(null) }
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {


            items(categories) { category ->
                val isSelected = category == selectedCategory

                Button(
                    onClick = { selectedCategory = category },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected)
                            Color(0xFF416954)
                        else
                            Color.LightGray,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = category)
                }
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(productImages.size) { index ->
                ProductCard(
                    imageRes = productImages[index],
                    name = productNames[index],
                    price = productPrices[index],
                    rating = productRatings[index]
                )
            }
        }
    }
}
@Composable
fun ProductCard(
    imageRes: Int,
    name: String,
    price: String,
    rating: String
) {
    ElevatedCard(
        modifier = Modifier
            .width(193.dp)
            .height(302.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // IMAGE (no Box)
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Inside
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Box(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFE65A4E), // image jaisa coral red
                            shape = RoundedCornerShape(50) // perfect pill
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
                        fontSize = 14.sp,
                        color = Color(0xFFFFC107)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = rating,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }



            Text(name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Text("Armchair", fontSize = 12.sp, color = Color.Gray)



            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    price,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF2C4939)
                )

                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .background(Color(0xFF2C4939), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("+", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}


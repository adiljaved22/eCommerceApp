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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    onQueryUpdate: (newValue: String) -> Unit
) {

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

        // Row: Title + Profile Image
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
                    val icon = if(isSearchExpanded) Icons.Filled.ArrowBack else Icons.Filled.Search
                    Icon(imageVector = icon, contentDescription = "Search Icon")
                }
            },
            placeholder = { Text(text = "Search furniture") },
            trailingIcon = {
                IconButton(onClick ={}) {
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
            // Search results content (empty for now)
        }
    }
}



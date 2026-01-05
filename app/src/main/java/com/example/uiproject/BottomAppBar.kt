import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun Bar(navController: NavController) {

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val items = listOf(
        "Notification" to Icons.Filled.Notifications,
        "Favorite" to Icons.Filled.Favorite,
        "Home" to Icons.Filled.Home,
        "Cart" to Icons.Filled.ShoppingCart,
        "Profile" to Icons.Filled.Person
    )

    BottomAppBar(
        containerColor = Color.White,
        contentColor = Color.Gray,
        modifier = Modifier.fillMaxWidth()
    ) {
        items.forEach { (name, icon) ->
            val isSelected = currentRoute == name

            val animatedColor by animateColorAsState(
                targetValue = if (isSelected) Color(0xFF416954) else Color.Transparent
            )
            val iconTint by animateColorAsState(
                targetValue = if (isSelected) Color.White else Color.Gray
            )
            val scale by animateFloatAsState(targetValue = if (isSelected) 1.2f else 1f)

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(color = animatedColor, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {

                            when (name) {
                                "Home" -> navController.navigate("Home") { launchSingleTop = true }
                                "Notification" -> navController.navigate("Notification") {
                                    launchSingleTop = true
                                }

                                "Favorite" -> navController.navigate("Favorite") {
                                    launchSingleTop = true
                                }

                                "Cart" -> navController.navigate("Cart") { launchSingleTop = true }
                                "Profile" -> navController.navigate("Profile") {
                                    launchSingleTop = true
                                }
                            }
                        },
                        modifier = Modifier.scale(scale)
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = name,
                            tint = iconTint,
                            modifier = Modifier.size(if (name == "Home") 30.dp else 26.dp)
                        )
                    }
                }
                Text(
                    text = name,
                    fontSize = 12.sp,
                    color = if (isSelected) Color(0xFF416954) else Color.Gray
                )
            }
        }
    }
}

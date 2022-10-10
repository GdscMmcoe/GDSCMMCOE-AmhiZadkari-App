package com.gdsc.amhizadkari

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gdsc.amhizadkari.aboutus.AboutUsScreen
import com.gdsc.amhizadkari.home.HomeScreen
import com.gdsc.amhizadkari.pastevents.PastEventScreen
import com.gdsc.amhizadkari.ui.theme.Poppins
import com.gdsc.amhizadkari.upcoming.UpcomingScreen
import kotlinx.coroutines.launch


@Preview
@Composable
fun Navigation() {
    val items = listOf(
        Routes.Donate,
        Routes.Home,
        Routes.AboutUs
    )
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    val navController = rememberNavController()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalDrawer(
            drawerState = drawerState,
            drawerContent = {
                DrawerContent()
            },
        ){
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            elevation = 0.dp,
                            backgroundColor = MaterialTheme.colors.background,
                            contentColor = MaterialTheme.colors.onBackground,
                            title = {
                                Text(
                                    text = "Amhi Zadkari",
                                    fontSize = 26.sp,
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight(600),
                                    color = MaterialTheme.colors.onBackground,
                                    modifier = Modifier.padding(10.dp)
                                )
                            },
                            actions = {
                                IconButton(
                                    enabled = true,
                                    onClick = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    },
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Menu",
                                        modifier = Modifier.size(40.dp)
                                    )
                                }
                            })
                    },
                    bottomBar = {
                        BottomNavigation(
                            backgroundColor = Color(0xff86AF7F),
                            elevation = 20.dp,
                            modifier = Modifier.clip(RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp))
                        ) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            items.forEach { item ->
                                BottomNavigationItem(
                                    icon = { Icon(imageVector = item.icon!!, contentDescription = null) },
                                    label = { Text(text = item.label!!, fontSize = 10.sp) },
                                    selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                                    onClick = {
                                        navController.navigate(item.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = Routes.Home.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(Routes.Home.route) { HomeScreen(navController = navController) }
                        composable(Routes.Donate.route) { Text(text = "TODO") }
                        composable(Routes.PastEvents.route) { PastEventScreen(navController) }
                        composable(Routes.UpcomingEvents.route) { UpcomingScreen(navController = navController) }
                        composable(Routes.AboutUs.route) { AboutUsScreen(navController) }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DrawerContent() {
    val scrollState = rememberScrollState()
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            Surface(
                elevation = 10.dp
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .background(Color(0xffD8F2D4))
                        .fillMaxWidth()
                        .padding(50.dp)
                ) {
                    Text(
                        text = "Amhi Zadkari",
                        fontFamily = Poppins,
                        fontSize = 24.sp,
                        fontWeight = FontWeight(600)
                    )
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, top = 30.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ContactPhone,
                    contentDescription = "Contact Us",
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(30.dp)
                )
                Text(
                    text = "Contact Us",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = Poppins
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, top = 30.dp)

            ) {
                Icon(
                    imageVector = Icons.Default.Event,
                    contentDescription = "Events",
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(30.dp)
                )
                Text(
                    text = "Events",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = Poppins
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, top = 30.dp)

            ) {
                Icon(
                    imageVector = Icons.Default.QuestionAnswer,
                    contentDescription = "FAQ",
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(30.dp)
                )
                Text(
                    text = "FAQ's",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = Poppins
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, top = 30.dp)

            ) {
                Icon(
                    imageVector = Icons.Default.PeopleAlt,
                    contentDescription = "About us",
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(30.dp)
                )
                Text(
                    text = "About Us",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = Poppins
                )

                Spacer(modifier = Modifier.padding(20.dp))
            }
        }
    }
}


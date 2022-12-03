package com.gdsc.amhizadkari

import AboutUsScreen
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContactPhone
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gdsc.amhizadkari.donate.DonateUsScreen
import com.gdsc.amhizadkari.home.HomeScreen
import com.gdsc.amhizadkari.ui.theme.CardColor
import com.gdsc.amhizadkari.ui.theme.Poppins
import com.gdsc.amhizadkari.ui.theme.rowColor
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomNav(navController: NavController) {
    val items = listOf(
        Routes.Donate,
        Routes.Home,
        Routes.AboutUs
    )

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()




    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalDrawer(
            drawerState = drawerState,
            drawerContent = {
                DrawerContent(navController)
            },
        ){
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                val bottomNavController = rememberAnimatedNavController()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            elevation = 0.dp,
                            backgroundColor = MaterialTheme.colors.background,
                            contentColor = MaterialTheme.colors.onBackground,
                            title = {
                                Image(
                                    painter = painterResource(id = R.drawable.tree),
                                    contentDescription = "Logo",
                                    modifier = Modifier.size(25.dp)
                                )
                                Text(
                                    text = stringResource(id = R.string.app_name),
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
                            backgroundColor = MaterialTheme.colors.rowColor,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                        modifier = Modifier.fillMaxWidth()
                            ) {
                                val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination
                                items.forEach { item ->
                                    BottomNavigationItem(
                                        icon = { Icon(imageVector = item.icon!!, contentDescription = null) },
                                        label = { Text(text = item.label!!, fontSize = 10.sp) },
                                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                                        onClick = {
                                            bottomNavController.navigate(item.route) {
                                                popUpTo(bottomNavController.graph.findStartDestination().id) {
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
                    }
                ) { innerPadding ->
                    AnimatedNavHost(
                        bottomNavController,
                        startDestination = Routes.Home.route,
                        Modifier
                            .padding(innerPadding),
                        enterTransition = { fadeIn() },
                        exitTransition = { fadeOut() },
                    ) {
                        composable(Routes.Home.route) {
                            HomeScreen(navController)
                        }
                        composable(Routes.Donate.route) {
                            DonateUsScreen()
                        }
                        composable(Routes.AboutUs.route) {
                            AboutUsScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DrawerContent(navController: NavController) {
    val context = LocalContext.current

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Surface(
                    elevation = 10.dp
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .background(MaterialTheme.colors.CardColor)
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.drawertree),
                            contentDescription = "Tree"
                        )
                        Text(
                            text = stringResource(id = R.string.app_name),
                            fontFamily = Poppins,
                            fontSize = 24.sp,
                            fontWeight = FontWeight(600),
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Routes.ContactUs.route)
                        }
                        .padding(start = 25.dp, top = 15.dp, bottom = 15.dp)

                ) {
                    Icon(
                        imageVector = Icons.Default.ContactPhone,
                        contentDescription = "Contact Us",
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .size(30.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.contact_us_text),
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
                        .clickable { navController.navigate(Routes.FaqScreen.route) }
                        .padding(start = 25.dp, top = 15.dp, bottom = 15.dp)
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


                Spacer(modifier = Modifier.padding(30.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, bottom = 5.dp)
                ) {
                    Text(
                        text = stringResource(R.string.find_us),
                        fontSize = 20.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Medium
                    )
                }


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, bottom = 10.dp)
                        .clickable {
                            val intent = Intent(
                                "android.intent.action.VIEW",
                                Uri.parse("https://www.instagram.com/amhi_zadkari/")
                            )
                            context.startActivity(intent)
                        }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.instagram),
                        contentDescription = "Instagram",
                        modifier = Modifier
                            .size(35.dp)
                    )
                    Text(
                        text = "amhi_zadkari",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        fontFamily = Poppins,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .clickable {
                        val intent = Intent(
                            "android.intent.action.VIEW",
                            Uri.parse("https://gdsc.community.dev/marathwada-mitra-mandals-college-of-engineering-pune/")
                        )
                        context.startActivity(intent)
                    }
            ) {
                Text(
                    text = stringResource(R.string.design_dev_by),
                    fontSize = 10.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}
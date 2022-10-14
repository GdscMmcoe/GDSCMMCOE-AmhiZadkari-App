package com.gdsc.amhizadkari.contactus

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

private val contactList =
    mutableListOf("Kaushal Singh Garud", "Pratima Garud", "Ketki", "Suraj Kakade","Prateek")

private val textStyle = TextStyle(fontSize = 20.sp, color = Color.Black)

@Composable
fun ContactUsScreen(navController: NavController) {
    BackHandler {
        navController.popBackStack()
    }{
        Column(modifier = Modifier
                        .padding(40.dp)) {
                        Text(text = "Contact Us", style = MaterialTheme.typography.h4.copy(
                            fontWeight = FontWeight.Bold) )
                        RecyclerView()
    }
}
@Composable
fun ListItem(name : String){

    val expanded = remember { mutableStateOf(false)}
    val extraPadding by animateDpAsState(
        if (expanded.value) 24.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(color = Color(30,111,17,13),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)){


        Column(modifier = Modifier
            .padding(24.dp)
            .width(280.dp)
            .height(25.dp)
        ) {


            Row{
                Column {
                    Image(painter = painterResource(id = R.drawable.username),
                        contentDescription = "contact",
                        modifier = Modifier.size(80.dp))

                    //Text(text = name, style = MaterialTheme.typography.h4.copy(
                        //fontWeight = FontWeight.ExtraBold))
                }
                Column{
                    ClickableText(
                        text = AnnotatedString(name) ,

                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL)
                            if (name.equals("Kushal Singh Garud"))
                                intent.setData(Uri.parse("9529968388"))
                            if (name.equals("Pratima Garud"))
                                intent.setData(Uri.parse("9665859394"))
                            if (name.equals("Ketki"))
                                intent.setData(Uri.parse("9112686229"))
                            if (name.equals("Suraj Kakade"))
                                intent.setData(Uri.parse("9767257776"))
                            if (name.equals("Prateek"))
                                intent.setData(Uri.parse("860013940"))

                        }, style = textStyle)
                }


            }
        }

    }



}


@Composable
fun RecyclerView(){

    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)){

        items(contactList){ name ->

            ListItem(name = name)

        }

    }

}

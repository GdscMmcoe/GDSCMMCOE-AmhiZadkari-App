import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gdsc.amhizadkari.R
import com.gdsc.amhizadkari.ui.theme.CardColor
import com.gdsc.amhizadkari.ui.theme.Poppins

@Composable
fun AboutUsScreen(navController: NavController?) {
    Box(modifier = Modifier){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
        ) {
            Text(
                "About us",
                fontSize = 40.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
            )
            Card(
                elevation = 10.dp,
                backgroundColor = MaterialTheme.colors.CardColor,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxSize()
            ) {

                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp,end = 20.dp,top = 60.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = LoremIpsum(40).values.joinToString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = Poppins
                        )
                    }
                }
            }
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp, top = 40.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.tree),
            contentDescription = "Tree",
            modifier = Modifier.size(100.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun abtPrev(){

    Scaffold(
        topBar = {
            TopAppBar {

            }
        }
    ) {
        Spacer(modifier = Modifier.padding(it))
        AboutUsScreen(navController = null)
    }

}
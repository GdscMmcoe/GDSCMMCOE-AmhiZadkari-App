import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gdsc.amhizadkari.R
import com.gdsc.amhizadkari.ui.theme.CardColor
import com.gdsc.amhizadkari.ui.theme.Poppins

@Composable
fun FaqScreen(navController: NavController) {
    BackHandler {
        navController.popBackStack()
    }

    val questions = listOf(
        R.string.q1,
        R.string.q2,
        R.string.q3,
        R.string.q4,
    )
    val answers = listOf(
        R.string.a1,
        R.string.a2,
        R.string.a3,
        R.string.a4,
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(start = 5.dp, end = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colors.onBackground
                )
            }
            Text(
                "FAQ",
                fontSize = 40.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(5.dp),
                color = MaterialTheme.colors.onBackground
            )
            Image(
                painter = painterResource(id = R.drawable.drawertree),
                contentDescription = "Tree",
                modifier = Modifier
                    .size(45.dp)
                    .padding(bottom = 5.dp, start = 10.dp)
            )
        }

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(questions.size) {
                Faq(question = questions[it], answer = answers[it])
            }
        }
    }


}

@Composable
fun Faq(@StringRes question: Int, @StringRes answer: Int) {

    var expanded by remember { mutableStateOf(false) }
    Surface(
        elevation = 1.dp,
        color = MaterialTheme.colors.CardColor,
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable {
                        expanded = !expanded
                    }
            ) {
                Text(
                    text = stringResource(id = question),
                    fontFamily = Poppins,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Expand",
                        modifier = if (expanded) Modifier.rotate(180f) else Modifier
                    )
                }
            }

            if (expanded) {
                Surface(
                    color = MaterialTheme.colors.CardColor,
                    modifier = Modifier.clip(RoundedCornerShape(20.dp))
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        Text(
                            text = stringResource(id = answer),
                            fontFamily = Poppins,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}
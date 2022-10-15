import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.gdsc.amhizadkari.R
import com.gdsc.amhizadkari.ui.theme.CardColor
import com.gdsc.amhizadkari.ui.theme.Poppins

@Composable
fun AboutUsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    "About us",
                    fontSize = 40.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(5.dp)
                )
                Card(
                    elevation = 5.dp,
                    backgroundColor = MaterialTheme.colors.CardColor,
                    shape = RoundedCornerShape(40.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 20.dp, end = 20.dp, top = 40.dp, bottom = 30.dp),
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp, top = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tree),
                    contentDescription = "Tree",
                    modifier = Modifier.size(100.dp)
                )
            }
        }
    }

}
package lk.ms.deadlines.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.temporal.ChronoUnit

/**
 * TODO: Change names of priority/risk => MEDIUM to MID
 * TODO: fix Sizing issue in Task BOx so event name does not move when change in number of letters in priority
 * TODO: Add functionality to number of days left in event
 * TODO: Add More Defined shadow to events or change background color of events to help differentiate from background
 * TODO: Add animation to long event names so it has a side scroll animation
 */

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        // Title Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(122.dp)
                .background(
                    color = Color(0xDDD9D9D9),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 40.dp,
                        bottomEnd = 40.dp
                    )
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "DEADLINES",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .padding(0.dp,40.dp,0.dp,0.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            // Events
            EventIndicator(
                eventName = "event1",
                eventRisk = "MEDIUM",
                timeLeft = "30 days left",
                onClick = {/*wde*/ })
            EventIndicator(
                eventName = "event1",
                eventRisk = "LOW",
                timeLeft = "30 days left",
                onClick = {/*wde*/ })
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventIndicator(
    eventName:String,
    eventRisk:String,
   // riskColur:String
    timeLeft:String,// only for now need to be change the time later
    onClick: () -> Unit,
    modifier: Modifier = Modifier

) {
    Button(
        onClick = onClick, // OnClick is executed
        modifier = modifier
            .fillMaxWidth(0.9f)
            .height(90.dp)
            .padding(0.dp, 5.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF97D6FF),
            contentColor = Color.Black
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 20.dp,
            pressedElevation = 12.dp
        )
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RiskLevelIndicater(eventRisk)
                Spacer(modifier = Modifier.width(15.dp)) // Small gap
                Text(
                    text = eventName,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Left,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = timeLeft,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Left,
                    color = Color.Black
                )

            }
            Spacer(modifier = Modifier.height(10.dp)) // Small gap between details and progress bar
            Row(modifier = Modifier.fillMaxWidth()){
                DeadlineProgressBar(LocalDate.of(2025, 6, 11), LocalDate.of(2025, 7, 11))
            }
        }
    }
}

@Composable
fun RiskLevelIndicater(
    eventRisk:String
){
    Box(
        modifier = Modifier
            .background(
                color = getColor(eventRisk),
                shape = RoundedCornerShape(5.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp) // Optional: padding inside the background
    ) {
        Text(
            text = eventRisk,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
        )
    }
}

fun getColor(eventRisk: String): Color {
    return when(eventRisk.lowercase()) {
        "low" -> Color(0xFF2FE843)    // Green
        "medium" -> Color(0xFF2C66C0) // Yellow
        "high" -> Color(0xFFF44336)   // Red
        else -> Color.Gray
    }
}

// Add these imports

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DeadlineProgressBar(startDate: LocalDate, endDate: LocalDate) {
    val currentDate = LocalDate.now()

    // Calculate progress (0.0 to 1.0)
    val totalDays = ChronoUnit.DAYS.between(startDate, endDate).toFloat()
    val daysPassed = ChronoUnit.DAYS.between(startDate, currentDate).toFloat()
    val progress =  0.4f//(daysPassed / totalDays).coerceIn(0f, 1f)

    // Custom progress bar with shadow and rounded corners
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp)
            .shadow(4.dp, shape = RoundedCornerShape(8.dp)),
        color = Color.LightGray,
        shape = RoundedCornerShape(8.dp)
    ) {
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp),
            color = if (progress < 0.7f) Color(0xFF224F85) else Color.Red,
            trackColor = Color.Transparent,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
package lk.ms.deadlines.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lk.ms.deadlines.ui.login.LoginPage
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePage(){
    Spacer(modifier = Modifier.height(10.dp))
    EventIndicator("event1","low","30 days left",{/*wde*/})
    Spacer(modifier = Modifier.height(10.dp))
    EventIndicator("event1","low","30 days left",{/*wde*/})
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
            .height(90.dp),
        shape = RoundedCornerShape(
            topStart = 15.dp,
            topEnd = 15.dp,
            bottomStart = 15.dp,
            bottomEnd = 10.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFE4E4E4),
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
            fontSize = 18.sp,
            textAlign = TextAlign.Left,
            color = Color.Black,
        )
    }
}
fun getColor(eventRisk: String): Color {
    return when(eventRisk.lowercase()) {
        "low" -> Color(0xFF4CAF50)    // Green
        "medium" -> Color(0xFFFFEB3B) // Yellow
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
            color = if (progress < 0.7f) Color(0xFF4CAF50) else Color.Red,
            trackColor = Color.Transparent,
        )
    }
}



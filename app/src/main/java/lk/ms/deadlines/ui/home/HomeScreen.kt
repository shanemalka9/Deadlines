package lk.ms.deadlines.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    Text(
        text = "Welcome to the Home Screen!",
        style = LocalTextStyle.current.copy(fontSize = 24.sp),
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxSize()
    )
}
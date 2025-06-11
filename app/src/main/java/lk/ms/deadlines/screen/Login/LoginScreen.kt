package lk.ms.deadlines.screen.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import lk.shan.deadlines.R

@Composable
fun LoginPage() {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        Spacer(modifier = Modifier.height(200.dp))
        ResponsiveLogo()
        Spacer(modifier = Modifier.height(10.dp))
        TextBox("Email/UserName", username) { username = it }
        TextBox("Password", password) { password = it }
        Spacer(modifier = Modifier.height(10.dp))
        CustomButton("Login")
        Spacer(modifier = Modifier.height(10.dp))
        CustomButton("Continue with Google")
        Spacer(modifier = Modifier.height(10.dp))
        CustomButton("Create Account")

    }
}

@Composable
fun ResponsiveLogo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp) // Fixed height for logo container
    ) {
        Image(
            painter = painterResource(R.drawable.frame_16),
            contentDescription = "App Logo",
            modifier = Modifier
                .align(Alignment.Center)
                .size(180.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextBox(
    text: String,
    status: String,
    onTextChange: (String) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        OutlinedTextField(
            value = status,
            onValueChange = onTextChange,
            label = { Text(text) },
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 25.dp,
                bottomEnd = 25.dp,
                bottomStart = 25.dp
            ),
            colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.Gray,
                disabledBorderColor = Color.LightGray,
                errorBorderColor = Color.Red
            )
        )
    }
}



@Composable
fun CustomButton(buttonText: String) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        val buttonWidth = maxWidth * 0.8f // 80% of screen width

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { /* Handle click */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE4E4E4),
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .width(buttonWidth)
                    .height(60.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 20.dp,
                    pressedElevation = 12.dp
                ),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 25.dp,
                    bottomStart = 25.dp,
                    bottomEnd = 25.dp
                ),
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(buttonText)
                }
            }
        }
    }
}

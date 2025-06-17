package lk.ms.deadlines.ui.popup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lk.ms.deadlines.ui.login.TextBox
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.sp


@Composable
fun EventCreateMenu(
    show: Boolean,
    onDismiss: () -> Unit
) {
    var eventName by rememberSaveable { mutableStateOf("") }
    var priorityLevel by rememberSaveable { mutableStateOf("") }
    var type by  rememberSaveable { mutableStateOf("") }
    var location by  rememberSaveable { mutableStateOf("") }

    var startDate by  rememberSaveable { mutableStateOf("") }
    var endDate by  rememberSaveable { mutableStateOf("") }




    AnimatedVisibility(
        visible = show,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(align = Alignment.Bottom)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(900.dp)
                .background(Color.White),
            //contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(
                            color = Color(0xFFD9D9D9),
                            shape = RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomStart = 40.dp,
                                bottomEnd = 40.dp
                            )
                        ),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "Create New Event",
                        fontSize = 20.sp,
                        color = Color.Black )
                }

                Spacer(modifier = Modifier.height(16.dp))
                InputField(text = "Event name", eventName) { eventName= it }
                Spacer(modifier = Modifier.height(10.dp))
                InputField(text = "Start Date", startDate) { startDate= it }
                Spacer(modifier = Modifier.width(3.dp))
                InputField(text = "End Date", endDate) { endDate= it }
                Spacer(modifier = Modifier.height(10.dp))
                PriorityLevelDropdown(
                    priorityLevel = priorityLevel,
                    onPriorityLevelChange = { newValue -> priorityLevel = newValue }
                )
                Spacer(modifier = Modifier.height(10.dp))
                InputField(text = "Type of the Assessment", type) { type= it }
                Spacer(modifier = Modifier.height(10.dp))
                InputField(text = "Location", location) { location= it }
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF8D35),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
                    Text("Cancel")
                }

            }


        }
    }
}
@Composable
fun InputField(
    text: String,
    value: String,
    onTextChange: (String) -> Unit

) {
    Box(
        modifier = Modifier.fillMaxWidth(1.0f),
        contentAlignment = Alignment.Center
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onTextChange,
            label = { Text(text) },
            singleLine = true,
            shape = RoundedCornerShape(
                topStart = 15.dp,
                topEnd = 15.dp,
                bottomEnd = 15.dp,
                bottomStart = 15.dp
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFF8D35),      // Orange when focused/clicked
                unfocusedBorderColor = Color.Transparent,           // Gray when not focused
                disabledBorderColor = Color.LightGray,
                errorBorderColor = Color.Red,
                focusedContainerColor = Color(0xFFECECEC),   // Light gray background
                unfocusedContainerColor = Color(0xFFECECEC),
                cursorColor = Color(0xFFFF8D35),
                focusedTextColor = Color(0xFF000000),
                unfocusedTextColor = Color(0xFF000000)
            ),

            textStyle = LocalTextStyle.current.copy(fontSize = 18.sp)
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriorityLevelDropdown(
    priorityLevel: String,
    onPriorityLevelChange: (String) -> Unit
) {
    val items = listOf("LOW", "MID", "HIGH")

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth(0.9f)
    ) {
        OutlinedTextField(
            value = priorityLevel,
            onValueChange = {},
            readOnly = true,
            label = { Text("Priority Level") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFF8D35),
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color(0xFFECECEC),
                unfocusedContainerColor = Color(0xFFECECEC),
                cursorColor = Color(0xFFFF8D35),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            shape = RoundedCornerShape(15.dp),
            textStyle = LocalTextStyle.current.copy(fontSize = 18.sp),
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onPriorityLevelChange(item)  // Update external state
                        expanded = false
                    }
                )
            }
        }
    }
}



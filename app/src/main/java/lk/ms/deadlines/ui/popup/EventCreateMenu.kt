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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

/*
pop up menu that use for create a event
thi menu will display when user click add event button on home page
this composable hold multi-pal state var that hold  user data about event and use that data to create event objects

*/
@Composable
fun EventCreateMenu(
    show: Boolean,
    onDismiss: () -> Unit
) {
    var eventName by rememberSaveable { mutableStateOf("") }
    var priorityLevel by rememberSaveable { mutableStateOf("") }
    var type by  rememberSaveable { mutableStateOf("") }
    var location by  rememberSaveable { mutableStateOf("") }
    var notificationOption by rememberSaveable { mutableStateOf("") }
    var startDate by  rememberSaveable { mutableStateOf("") }
    var endDate by  rememberSaveable { mutableStateOf("") }

    // two list that pass for dropdown
    val prioritiesList = listOf("LOW", "MID", "HIGH")
    val notificationOptionList = listOf("Lw", "Mgfb", "HIGdsvH")

    AnimatedVisibility(
        visible = show,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(align = Alignment.Bottom)
    ) {
        // box that act as a white colour background for other component
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(900.dp)
                .background(Color.White),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // second box that act as a gray colour container that place top of the screen
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)// size of the second container
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
                // all other component will place here add anything you want
                Spacer(modifier = Modifier.height(16.dp))
                // input field for event name
                InputField(text = "Event name", eventName) { eventName= it }
                Spacer(modifier = Modifier.height(10.dp))
                // input field for start date (assigned to @shan for improve later)
                InputField(text = "Start Date", startDate) { startDate= it }
                Spacer(modifier = Modifier.width(3.dp))
                // input field for end date (assigned to @shan for improve later)
                InputField(text = "End Date", endDate) { endDate= it }
                Spacer(modifier = Modifier.height(10.dp))
                // priority level dropdown
                Dropdown(
                    value = priorityLevel,
                    itemsList = prioritiesList,
                    label = "Priority Level",
                    onPriorityLevelChange = { priorityLevel = it }
                )
                Spacer(modifier = Modifier.height(10.dp))

                InputField(text = "Type of the Assessment", type) { type= it }
                Spacer(modifier = Modifier.height(10.dp))

                InputField(text = "Location", location) { location= it }
                Spacer(modifier = Modifier.height(10.dp))
                // notification option dropdown
                Dropdown(
                    value = notificationOption,
                    itemsList = notificationOptionList,
                    label = "Notification Type",
                    onPriorityLevelChange = { notificationOption = it }
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // cancel button
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF8D35),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Cancel")
                    }
                    // confirm button
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF8D35),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Confirm")
                    }
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
fun Dropdown(
    value: String,
    itemsList: List<String>,
    label:String,
    onPriorityLevelChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth(0.9f)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
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
            itemsList.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onPriorityLevelChange(item)
                        expanded = false
                    }
                )
            }
        }
    }
}




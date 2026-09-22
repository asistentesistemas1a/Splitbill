package com.kmp.splitbill.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kmp.splitbill.components.MainCard

@Composable
//@Preview
fun HomeView() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Split Bill", fontWeight = FontWeight.Bold)
                }
            )
        }
    ) {
        padding -> ContentHomeView(modifier = Modifier.padding(padding))



    }
}


@Composable
fun ContentHomeView(modifier: Modifier) {
    //var amount by remember { mutableStateOf("") }
    var amount by rememberSaveable { mutableStateOf("") }
    val options = listOf(0, 10, 15, 25, 30)
    var selectedTip by remember { mutableStateOf(10) }

    Column(modifier) {
        MainCard(title = "Total Bill Amount") {
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Enter amount") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

            Row (
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                options.forEach { option ->
                    FilterChip(
                        selected = selectedTip == option,
                        onClick = { selectedTip = option },
                        label = { Text(text = "$option%") }
                    )
                }
            }
            // Text(selectedTip.toString())
            IconButton(onClick = { }) {
//                Icon(imageVector = Icons)
            }
        }
    }


}
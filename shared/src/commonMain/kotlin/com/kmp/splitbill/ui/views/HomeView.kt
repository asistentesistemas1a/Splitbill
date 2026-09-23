package com.kmp.splitbill.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmp.splitbill.components.MainCard
import com.kmp.splitbill.components.MainIcon
import com.kmp.splitbill.components.MainRow

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
    var numberPersons by remember { mutableStateOf(1) }
    var totalTip by remember { mutableStateOf(0.0) }
    var total by remember { mutableStateOf(0.0) }
    var totalByPersons by remember { mutableStateOf(0.0) }

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
            Text("Numero de Personas")
            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp),
//                verticalAlignment = Alignment.CenterVertically
            ){
                MainIcon(desc = "Arriba",
                    icon = Icons.Default.ArrowCircleUp,
                ){
                    numberPersons++
                }
//                IconButton(onClick = { }) {
//                    Icon(
//                        imageVector = Icons.Default.ArrowCircleDown,
//                        contentDescription = "Down - Abajo",
//                        modifier = Modifier.size(50.dp)
//                    )
//                }
                Text(numberPersons.toString(), fontSize = 50.sp)

                MainIcon(desc = "Abajo",
                    icon = Icons.Default.ArrowCircleDown,
                ){
                   if (numberPersons > 1) numberPersons--
                }


//                IconButton(onClick = { }) {
//                    Icon(
//                        imageVector = Icons.Default.ArrowCircleUp,
//                        contentDescription = "Up - Arriba",
//                        modifier = Modifier.size(50.dp)
//                    )
//                }
            }

            Button(onClick = {
                if (amount.isNotEmpty()){
                    totalTip = amount.toDouble() * (selectedTip.toDouble() / 100)
                    total = amount.toDouble() + totalTip
                    totalByPersons = calculate(amount, selectedTip, numberPersons)
                }

            }
            )
            {
                Text("Calculate")
            }

        }

        MainCard(title = "Bill Summary") {
            MainRow(title = "Tip Bill", total = totalTip.roundTwoDecimals())
            MainRow(title = "Total", total = total.roundTwoDecimals())
            Surface(
                color = Color(0xFFE5F9E7),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)

            ){
                Text(
                    "Each Person Pays $${totalByPersons.roundTwoDecimals()}",
                    color = Color(0xFF2E7D32),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)


                )
            }
        }
    }


}

fun calculate (amount: String, tip:Int, persons: Int): Double{
    val tipRes = amount.toDouble() * (tip.toDouble() / 100)
    val totalWithTip = amount.toDouble() + tipRes
    return totalWithTip / persons
}

fun Double.roundTwoDecimals(): Double{
    return kotlin.math.round(this * 100) / 100
}
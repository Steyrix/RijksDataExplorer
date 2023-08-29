package com.example.rijksdataexplorer.main.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.android.material.R

@Composable
fun ErrorView(
    onButtonClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.mtrl_ic_error),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = "Error"
        )
        Button(
            onClick = { onButtonClick.invoke() },
        ) {
            Text(text = "Reload")
        }
    }
}
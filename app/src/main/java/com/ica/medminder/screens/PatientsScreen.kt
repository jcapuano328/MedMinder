package com.ica.medminder.screens

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import com.ica.medminder.viewmodels.PatientsViewModel
import com.ica.medminder.viewmodels.PatientViewState

@Composable
fun PatientsScreen(navController: NavController,
    viewModel: PatientsViewModel = hiltViewModel()
) {
    val patients by viewModel.patients.collectAsState()
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().padding(2.dp)) {
		Text("Patients Screen")
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(patients) { patient ->
                PatientListItem(patient,
                    onCheckChanged = { /* Handle check changes */ },
                    onSelectButtonClick = { /* Handle select button click */ },
                    onDeleteClick = { /* Handle delete click */ }
                )
            }
        }

	}
}

@Composable
fun PatientListItem(patient: PatientViewState,
    onCheckChanged: (Boolean) -> Unit,
    onSelectButtonClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFFF0F0F0)) // Light gray
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = patient.checked,
                onCheckedChange = onCheckChanged,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = patient.patient.name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = patient.patient.dob,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            IconButton(onClick = onSelectButtonClick) {
                // Replace with actual icon later
                Icon(Icons.Default.ArrowForward, contentDescription = "Next")
            }

            IconButton(onClick = onDeleteClick) {
                // Replace with actual icon later
                Icon(Icons.Default.Close, contentDescription = "Delete")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPatientsScreen() {
    PatientsScreen(
        navController = NavController(LocalContext.current)
    )
}


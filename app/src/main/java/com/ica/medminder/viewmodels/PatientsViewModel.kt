package com.ica.medminder.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

import com.ica.medminder.dal.dao.PatientDao
import com.ica.medminder.dal.entities.Patient
import kotlinx.coroutines.flow.map

data class PatientViewState(
    val patient: Patient,
    var checked: Boolean
)

@HiltViewModel
class PatientsViewModel @Inject constructor(
    private val patientDao: PatientDao
) : ViewModel() {
    val patients : StateFlow<List<PatientViewState>> = patientDao
        .getAll()
        .map { patientList ->
            // Transform the List<Patient> to List<PatientViewState>
            patientList.map { patient ->
                PatientViewState(
                    patient = patient,
                    checked = false // Set an initial value for any new properties
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun togglePatientSelection(patient: PatientViewState) {
        viewModelScope.launch {
            patient.checked = !patient.checked
        }

    }
}
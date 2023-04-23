package com.example.rinjin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rinjin.presentation.UsersViewModel
import com.example.rinjin.ui.theme.RinjinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RinjinTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary,
                ) {
                    Rinjin()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Rinjin(
    modifier: Modifier = Modifier,
    viewModel: UsersViewModel = hiltViewModel(),
) {
    val nowLocation by viewModel.locationInputText.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier =
        modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = Color.White, shape = ShapeDefaults.Small),
        contentAlignment = Alignment.TopCenter,
    ) {
        Box(contentAlignment = Alignment.TopCenter) {
            OutlinedTextField(
                modifier = Modifier.padding(top = 16.dp),
                value = nowLocation,
                onValueChange = { viewModel.changeLocation(it) },
                label = { Text(text = "Input Location!") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    viewModel.getUsers("location:$nowLocation")
                    keyboardController?.hide()
                }),
            )
        }

        if (nowLocation.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
            ) {
                items(viewModel.usersState.value.users) {
                    Text(text = "${it.name} ${it.follower_val}", color = Color.Black)
                }
            }
        }
    }
}

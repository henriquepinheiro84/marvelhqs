package com.pinheiro.marvelhqs.presenter.ui.authentication

import android.annotation.SuppressLint
import android.content.res.Resources.Theme
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pinheiro.marvelhqs.R
import com.pinheiro.marvelhqs.presenter.ui.components.LogButton
import com.pinheiro.marvelhqs.presenter.ui.components.PasswordField
import com.pinheiro.marvelhqs.presenter.ui.components.UserField
import com.pinheiro.marvelhqs.presenter.ui.navigation.Logged
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoginScreen(navController: NavController) {
    MaterialTheme {
        var openAlertDialog by remember { mutableStateOf(false) }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()

        ) {
            var snackbarHostState = remember {
                SnackbarHostState()
            }
            val scope = rememberCoroutineScope()
            var credencials by remember {
                mutableStateOf(Credencials())
            }
            val viewModel = koinViewModel<LoginViewModel>()
            val successLogin = viewModel.successLogin.collectAsState(initial = true)
            UserField(
                modifier = Modifier.fillMaxWidth(),
                value = credencials.user,
                onChange = { data ->
                    credencials = credencials.copy(user = data)
                })
            PasswordField(
                modifier = Modifier.fillMaxWidth(),
                value = credencials.password,
                onChange = { data ->
                    credencials = credencials.copy(password = data)
                },
                submit = { })
            Spacer(modifier = Modifier.padding(horizontal = 18.dp))
            LogButton(text = "Login") {
                openAlertDialog = !viewModel.validateUser(credencials.user, credencials.password)
                if (viewModel.validateUser(credencials.user, credencials.password)) {
                    navController.navigate(Logged) {
                        popUpTo(0)
                    }
                }
            }
            Spacer(modifier = Modifier.padding(horizontal = 18.dp))
            LogButton(text = "Cadastrar") {

            }
            if (!successLogin.value) {

//                AlertDialog(onDismissRequest = { openAlertDialog.value }, confirmButton = {
//                    false
//                    TextButton(onClick = {  }) {
//                        Text(text = stringResource(id = R.string.UNDERSTAND))
//                    }
//                })
//                scope.launch {
//                    snackbarHostState.showSnackbar(
//                        message = "Usuário ou senha incorretos",
//                        duration = SnackbarDuration.Short
//                    )
//                }

            }
        }

        if (openAlertDialog) {
            Dialog(onDismissRequest = {
                openAlertDialog = false
            }) {
                Column(
                    modifier = Modifier
                        .widthIn(200.dp, 300.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.USER_LOGIN_FAILED),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = stringResource(R.string.LOGIN_FAILED_MESSAGE))
                    Button(onClick = {
                        openAlertDialog = false
                    }) {
                        Text(text = stringResource(R.string.UNDERSTAND))
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    LoginScreen(navController = rememberNavController())
}

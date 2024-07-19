package com.pinheiro.marvelhqs.presenter.ui.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pinheiro.marvelhqs.presenter.ui.components.LogButton
import com.pinheiro.marvelhqs.presenter.ui.components.PasswordField
import com.pinheiro.marvelhqs.presenter.ui.components.UserField
import com.pinheiro.marvelhqs.presenter.ui.navigation.Logged

@Composable
fun LoginScreen(navController: NavController) {

   Column(
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally,
       modifier = Modifier.fillMaxSize()

   ) {
       UserField(
           modifier = Modifier.fillMaxWidth(),
           value = "Usuario",
           onChange ={})
       PasswordField(
           modifier = Modifier.fillMaxWidth(),
           value = "",
           onChange = {} ,
           submit = { /*TODO*/ })
       Spacer(modifier = Modifier.padding(horizontal = 18.dp))
       LogButton(text = "Login") {
           navController.navigate(Logged) {
               popUpTo(0)
           }
       }
       Spacer(modifier = Modifier.padding(horizontal = 18.dp))
       LogButton(text = "Cadastrar") {

       }
   }

}
@Preview
@Composable
fun LoginPreview() {
    LoginScreen(navController = rememberNavController())
}

package com.example.authmoduleyt.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.authmoduleyt.presentation.components.*
import com.example.authmoduleyt.presentation.viewmodel.LoginViewModel
import com.example.authmoduleyt.ui.theme.*

@Composable
fun LoginScreen(
    onLoginSuccessNavigation: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    NavDestinationHelper(
        shouldNavigate = {
            loginViewModel.loginState.isSuccessfullyLoggedIn
        },
        destination = {
            onLoginSuccessNavigation()
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentAlignment = Alignment.Center
        ){
            HeaderBackground(
                leftColor = orange,
                rightColor = whiteGrayOrange,
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                text = "Login",
                style = MaterialTheme.typography.h4,
                color = white,
                fontWeight = FontWeight.SemiBold
            )
        }
        LoginContainer(
            emailValue = {
                loginViewModel.loginState.emailInput
            },
            passwordValue = {
                loginViewModel.loginState.passwordInput
            },
            buttonEnabled = {
                loginViewModel.loginState.isInputValid
            },
            onEmailChanged = loginViewModel::onEmailInputChange,
            onPasswordChanged = loginViewModel::onPasswordInputChange,
            onLoginButtonClick = loginViewModel::onLoginClick,
            isPasswordShown = {
                loginViewModel.loginState.isPasswordShown
            },
            onTrailingPasswordIconClick = loginViewModel::onToggleVisualTransformation,
            errorHint = {
                loginViewModel.loginState.errorMessageInput
            },
            isLoading = {
                loginViewModel.loginState.isLoading
            },
            modifier = Modifier
                .padding(top = 200.dp)
                .fillMaxWidth(0.9f)
                .shadow(5.dp, RoundedCornerShape(15.dp))
                .background(whiteGray, RoundedCornerShape(15.dp))
                .padding(10.dp,15.dp,10.dp,5.dp)
                .align(Alignment.TopCenter)
        )
        BubbleAnimation(
            bubbleColor1 = whiteGrayOrange,
            bubbleColor2 = orange,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .align(Alignment.BottomCenter)
        )
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                "No account yet?",
                style = MaterialTheme.typography.body2
            )
            Text(
                "Register",
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        onNavigateToRegisterScreen()
                    },
                color = orange,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2
            )
        }
    }

}

@Composable
fun LoginContainer(
    emailValue:() -> String,
    passwordValue:()-> String,
    buttonEnabled:() -> Boolean,
    onEmailChanged:(String) -> Unit,
    onPasswordChanged:(String) -> Unit,
    onLoginButtonClick:()->Unit,
    isPasswordShown:()->Boolean,
    onTrailingPasswordIconClick: () -> Unit,
    errorHint:()->String?,
    isLoading:()->Boolean,
    modifier: Modifier = Modifier
) {
   Column(
       modifier = modifier,
       verticalArrangement = Arrangement.spacedBy(15.dp)
   ){
       TextEntryModule(
           modifier = Modifier
               .fillMaxWidth(),
           description = "Email address",
           hint = "KApps@gmail.com",
           textValue = emailValue(),
           textColor = gray,
           cursorColor = orange,
           onValueChanged = onEmailChanged,
           trailingIcon = null,
           onTrailingIconClick = null,
           leadingIcon = Icons.Default.Email
       )
       TextEntryModule(
           modifier = Modifier
               .fillMaxWidth(),
           description = "Password",
           hint = "Enter password",
           textValue = passwordValue(),
           textColor = gray,
           cursorColor = orange,
           onValueChanged = onPasswordChanged,
           trailingIcon = Icons.Default.RemoveRedEye,
           onTrailingIconClick = {
               onTrailingPasswordIconClick()
           },
           leadingIcon = Icons.Default.VpnKey,
           visualTransformation = if(isPasswordShown()){
               VisualTransformation.None
           }else PasswordVisualTransformation(),
           keyboardType = KeyboardType.Password
       )
       Column(
           modifier = Modifier
               .fillMaxWidth(),
           verticalArrangement = Arrangement.spacedBy(5.dp)
       ){
           AuthButton(
               text = "Login",
               backgroundColor = orange,
               contentColor = white,
               enabled = buttonEnabled(),
               modifier = Modifier
                   .fillMaxWidth()
                   .height(45.dp)
                   .shadow(5.dp, RoundedCornerShape(25.dp)),
               isLoading = isLoading(),
               onButtonClick = onLoginButtonClick
           )
           Text(
               errorHint() ?: "",
               style = MaterialTheme.typography.caption
           )
       }
   }
}

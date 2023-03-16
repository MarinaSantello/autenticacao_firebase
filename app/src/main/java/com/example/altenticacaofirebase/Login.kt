package com.example.altenticacaofirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.altenticacaofirebase.ui.theme.AltenticacaoFirebaseTheme
import com.google.firebase.auth.*

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser // retorna a conta que está autenticada no dispositivo (se não tiver usuario, ele é nulo)

        if (currentUser != null) {
            val intent = Intent(this, HomeActivity::class.java)

            startActivity(intent)
        }
//        Log.i("instancia firebase", auth.currentUser!!.uid)
        setContent {
            AltenticacaoFirebaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting2("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {

    var emailValue by remember {
        mutableStateOf("")
    }

    var senhaVelue by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)) {
        Text(text = "LOGIN", fontSize = 24.sp)
        OutlinedTextField(
            value = emailValue,
            onValueChange = {emailValue = it},
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "E-mail")
            }
        )
        OutlinedTextField(
            value = senhaVelue,
            onValueChange = {senhaVelue = it},
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "senha")
            }
        )
        Button(
            onClick = {
                authenticate(emailValue, senhaVelue)
            }
        ) {
            Text(text = "Criar conta")
        }
    }
}

fun authenticate(email: String, password: String) {

    // obtendo a instancia do firebase
    val auth = FirebaseAuth.getInstance()

    // autenticação
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener {
            Log.i("resposta firebase", it.isSuccessful.toString()) // retorna um booleano com a autenticação
        }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    AltenticacaoFirebaseTheme {
        Greeting2("Android")
    }
}
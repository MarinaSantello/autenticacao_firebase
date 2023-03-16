package com.example.altenticacaofirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.tooling.preview.Preview
import com.example.altenticacaofirebase.ui.theme.AltenticacaoFirebaseTheme
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AltenticacaoFirebaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting3("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String) {
    Column() {
        Text(text = "Hello $name! This is your home, but EXIT NOW!!")

        Button(onClick = {
            val auth = FirebaseAuth.getInstance()

            auth.signOut() // metodo para deslogar o usuario
        }) {
            Text(text = "sair")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    AltenticacaoFirebaseTheme {
        Greeting3("Android")
    }
}
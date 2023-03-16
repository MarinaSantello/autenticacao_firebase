package com.example.altenticacaofirebase

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
import com.example.altenticacaofirebase.ui.theme.AltenticacaoFirebaseTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AltenticacaoFirebaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    
    var emailValue by remember {
        mutableStateOf("")
    }

    var senhaVelue by remember {
        mutableStateOf("")
    }
    
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)) {
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
                accountCreate(emailValue, senhaVelue)
            }
        ) {
            Text(text = "Criar conta")
        }
    }
}

fun accountCreate(email: String, password: String) {

    // obtendo uma instancia do firebase auth
    val auth = FirebaseAuth.getInstance()

    auth.createUserWithEmailAndPassword(email, password)
        .addOnSuccessListener { // retorna o resultado da autenticacao, quando completada com sucesso
            Log.i("resposta firebase", "${it.user!!.uid}") // uid: indentificação do usuario
        }
        .addOnFailureListener { // retorna o resultado da autenticacao, quando ela falha
            try {
                throw it
            }
            catch (error: FirebaseAuthUserCollisionException) { // caso o usuario tente cadastrar um login que já existe
                Log.i("erro firebase", "esse cadastro já existe")
                Log.i("erro firebase", error.message.toString())
            }
            catch (error: FirebaseAuthWeakPasswordException) { // caso o usuario tente cadastrar um login que já existe
                Log.i("erro firebase", "senha fraca, menor do que 6 caracteres")
                Log.i("erro firebase", error.message.toString())
            }
            catch (error: FirebaseAuthInvalidUserException) { // caso o usuario seja invalido
                Log.i("erro firebase", "usuario invalido")
                Log.i("erro firebase", error.message.toString())
            }
            catch (error: FirebaseAuthException) { // erro generico
                Log.i("erro firebase", "ocorreu um erro")
                Log.i("erro firebase", error.message.toString())
            }
            //Log.i("resposta firebase", "${it.message}")
        }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    AltenticacaoFirebaseTheme {
        Greeting("Android")
    }
}
package com.example.loadertestingcompose

import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(function: (arg: String) -> Unit) = Box(
    Modifier
        .fillMaxWidth()
        .fillMaxHeight()
) {

    val scale = remember {
        androidx.compose.animation.core.Animatable(0.0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(800, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
        )
        delay(1000)

        //Realtime database
        val database = Firebase.database
        val myRef = database.getReference("message")
        //myRef.setValue(wordList)

        // Read from the database
        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<String>()

                Log.d("Rohit", "Value is: " + value)

                /*navController.navigate(Screens.Dashboard+"/sampleText") {
                    popUpTo(Screens.Splash) {
                        inclusive = true
                    }
                }*/

                function(value?:"Default")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Rohit", "Failed to read value.", error.toException())
            }

        })
    }

    Image(
        painter = painterResource(id = R.drawable.circle),
        contentDescription = "",
        alignment = Alignment.Center, modifier = Modifier
            .fillMaxSize().padding(40.dp)
            .scale(scale.value)
    )

    Text(
        text = "Version - 1.0.0",
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
        modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)
    )
}
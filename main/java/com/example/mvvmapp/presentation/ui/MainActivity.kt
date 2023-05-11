package com.example.mvvmapp.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvmapp.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var someRandomString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val scroll = rememberScrollState()
            Column(
                modifier = Modifier
                    .verticalScroll(state = scroll, enabled = true)
                    .background(color = Color(0xFFF2F2F2))
                    .fillMaxSize()
            ) {
                Image(
                    painterResource(R.drawable.happy_meal_small),
                    contentDescription = "mc-happy-donalds",
                    modifier = Modifier.height(300.dp),
                    contentScale = ContentScale.Crop
                )

                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Happy meal",
                            style = TextStyle(
                                fontSize = (26.sp)
                            )
                        )
                        Text(text = "$5.99",
                            style = TextStyle(
                                color = Color(0xFF85bb65),
                                fontSize = (26.sp)
                            ),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(text = "800 Calories",
                        style = TextStyle(
                            fontSize = (26.sp)
                        )
                    )
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Button(
                        onClick = {},
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "ORDER NOW")
                    }
                }
            }
        }
    }
}
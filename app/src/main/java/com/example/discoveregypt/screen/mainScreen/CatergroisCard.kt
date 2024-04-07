package com.example.discoveregypt.screen.mainScreen

import android.graphics.RenderEffect
import android.graphics.RuntimeShader
import android.graphics.Shader
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import coil.compose.AsyncImage
import com.example.discoveregypt.R
import com.example.discoveregypt.ui.theme.Juicy
import com.example.discoveregypt.ui.theme.YellowButton
import com.example.discoveregypt.ui.theme.firaSansFamily
import com.example.discoveregypt.ui.theme.notosan
import com.example.discoveregypt.ui.theme.textYellow

data class Places(val name: String, val city: String, val photo: Int?)

val popularplaces = listOf<Places>(
    Places(name = "Vallay of Kings", city = "Luxor", photo = R.drawable.tempeldandara),
    Places(name = "Vallay of Kings", city = "Luxor", photo = R.drawable.camal)
)

@Composable
fun categories() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(popularplaces) {
            Cardcategories(
                city = it.name, place = it.city, modifier = Modifier
                    .fillMaxSize()
                    .width(250.dp)
                    .height(230.dp)
                    .shadow(10.dp, RoundedCornerShape(4.dp))
                    .clip(RoundedCornerShape(6.dp))
            )
        }
    }
}

@Composable
fun Cardcategories(modifier: Modifier = Modifier, place: String, city: String) {

    Card(
        modifier = modifier

    ) {


        Box(


        ) {

            AsyncImage(
                model = "https://www.prosperity.com/application/files/3514/7801/8037/Egypt_header_sm.jpg",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),

                contentDescription = null,
            )


            description(modifier = Modifier.align(Alignment.BottomCenter), place, city)


        }
    }
}


val kirilGlassShader = """
  uniform shader composable;
  uniform float2 rectangle;
  uniform float radius;
  
  float random (float2 st) {
      return fract(sin(dot(st.xy,vec2(12.9898,78.233)))*43758.5453123);
  }
  
  
  float roundRect(float2 position, float2 box, float radius) {
      vec2 q = abs(position) - box + radius;
      return min(max(q.x, q.y), 0.0) + length(max(q, 0.0)) - radius;   
  }
  
  half4 avgColor(float2 coord) {
      half4 color = half4(0.0);
      for(int x = -1; x < 2; x++) {
          for(int y = -1; y < 2; y++) {
              float2 offset = float2(x, y);
              color += composable.eval(coord );
          }
      }
      
      return color / 9.0;
  }
  
  half4 main(float2 coord) {
      float2 rectCenter = float2(rectangle.x, rectangle.y) / 2.0;
      float2 adjustedCoord = coord - rectCenter;
      float distanceFromEdge = roundRect(adjustedCoord, rectCenter, radius);
     
      half4 color = composable.eval(coord);
      if (distanceFromEdge > 0.0) {
          return color;
      }
      
      float2 normCoord = coord / rectangle.xy;
      float rand = random(normCoord);
      half4 black = half4(0.3, 0.3, 0.3, 1.0);
      float4 texture = mix(black, float4(float3(rand), 1.0), 0.4);
      color = mix(color, texture, 0.5);
        
//      color = avgColor(coord);

      
      return color;
  }
""".trimIndent()

@Composable
fun descriptionWithShader(modifier: Modifier = Modifier, place: String, city: String) {
    val shader = remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            RuntimeShader(kirilGlassShader)
        } else {
            TODO("VERSION.SDK_INT < TIRAMISU")
        }
    }
    var width by remember { mutableStateOf(0f) }
    var height by remember { mutableStateOf(0f) }
    val textMeasurer = rememberTextMeasurer()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .graphicsLayer {
                with(shader) {
                    setFloatUniform(
                        "rectangle",
                        width,
                        height
                    )
                    setFloatUniform(
                        "radius",
                        20.dp.toPx()
                    )
                }

                val blur = RenderEffect.createBlurEffect(
                    20f,
                    20f,
                    Shader.TileMode.DECAL
                )

                renderEffect = RenderEffect
                    .createRuntimeShaderEffect(
                        shader,
                        "composable"
                    )
                    .asComposeRenderEffect()
            }

            .onSizeChanged {
                width = it.width.toFloat()
                height = it.height.toFloat()
            }
            .drawWithCache {
                val measuredText =
                    textMeasurer.measure(
                        AnnotatedString("$place /n $city"),
                        constraints = Constraints.fixedWidth((size.width * 2f / 3f).toInt()),
                        style = TextStyle(fontSize = 20.sp, color = YellowButton)
                    )

                onDrawBehind {
//                    drawText(measuredText)
                }
            }

    ) {

        Column() {

            Text(
                text = place,
                color = Juicy,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = notosan,
                fontStyle = FontStyle.Normal,
                modifier = Modifier.padding(start = 8.dp)

            )
            Row() {
                Text(
                    text = city,
                    color = Juicy,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = firaSansFamily, modifier = Modifier.padding(start = 8.dp)

                )

            }
        }
    }
}

@Composable
fun description(modifier: Modifier = Modifier, place: String, city: String) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.radialGradient(
                    listOf(
                        Color(0x9FFFFFFF), Color(0x9FFFFFFF), Color(0x9FFFFFFF)

                    ), radius = 100f, center = Offset.Infinite
                )
            )

            .padding(4.dp)
    ) {
        Column {
            Text(
                text = city,
                color = textYellow,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = notosan, fontStyle = FontStyle.Normal
            )
            Row() {
                Text(
                    text = place,
                    color = textYellow,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = firaSansFamily
                )

            }
        }
    }
}

@Preview(showBackground = true, wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE)
@Composable
fun Cardpreview() {
    // Preview your composable function here
    categories()
}
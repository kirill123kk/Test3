package com.example.test3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test3.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                SCR()
            }
        }
    }
val size:Int =78
var mod :VModel = VModel()
@Preview(showBackground = true)
@Composable
fun SCR() {

    var counter = rememberSaveable{
        mutableStateOf(mod.One)}


    Image(painter = painterResource(id = R.drawable.image_1),
        contentDescription = "img1",
        modifier= Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)

        ) {
        Text(text = if(mod.rem) mod.One else mod.Tou,
            fontSize = 40.sp,
            textAlign= TextAlign.Right ,
        modifier = Modifier
            .height(100.dp)

            .background(color = BlackA)
            .padding(top=45.dp, end = 10.dp)
            .fillMaxWidth(),
            color = Color.White
            )
        println("OUT::: ${mod.One}")
        Row(    horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ButtDelete("",mod)
            ButtRevers("+/-", mod)
            ButtPersent("%", mod)
            ButtAction("/",mod)
        }
        Row(    horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Butt(7,mod)
            Butt(8,mod)
            Butt(9,mod)
            ButtAction("*",mod)
        }
        Row(    horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Butt(4,mod)
            Butt(5,mod)
            Butt(6,mod)
            ButtAction("-",mod)
        }
        Row(    horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Butt(1,mod)
            Butt(2,mod)
            Butt(3,mod)
            ButtAction("+",mod)
        }
        Row(    horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Butt(0,mod)
            ButtAction("=",mod)
        }

    }
}


@Composable
fun Butt(count:Int,mod:VModel){
    var res: CalckResult = CalckResult("","")
    Box(modifier = Modifier
        .requiredSize(size.dp)
        .clip(CircleShape)
        .clickable {
            if (mod.rem) {
                res.One = mod.One + count.toString()
                mod.setOne(res)
            } else {
                res.Tou = mod.Tou + count.toString()
                mod.setTou(res)
            }

        }
        .background(color = BlackA),
        contentAlignment = Alignment.Center
    ) {
        Text(text = count.toString(), color = Color.White)
    }
}
@Composable
fun ButtAction(count:String,mod:VModel){
    var res: CalckResult = CalckResult("","")
    Box(modifier = Modifier
        .requiredSize(size.dp)
        .clip(CircleShape)
        .clickable {
            mod.rem = false
            if (count == "=")
                Logic(mod)
            else
                mod.sign = count

        }
        .background(color = OrangeA),
        contentAlignment = Alignment.Center
    ) {
        Text(text = count, color = Color.White)
    }
}
@Composable
fun ButtPersent(count:String,mod:VModel){
    var res: CalckResult = CalckResult("","")
    Box(modifier = Modifier
        .requiredSize(size.dp)
        .clip(CircleShape)
        .clickable {
            if (mod.rem) {
                res.One = (mod.One.toInt() / 100).toString()
                mod.setOne(res)
            }
        }
        .background(color = GrayA),
        contentAlignment = Alignment.Center
    ) {
        Text(text = count, color = Color.White)
    }
}
@Composable
fun ButtRevers(count:String,mod:VModel){
    var res: CalckResult = CalckResult("","")
    Box(modifier = Modifier
        .requiredSize(size.dp)
        .clip(CircleShape)
        .clickable {
            if (mod.rem) {
                res.One = (mod.One.toInt() * (-1)).toString()
                mod.setOne(res)
            } else {
                res.Tou = (mod.Tou.toInt() * (-1)).toString()
                mod.setTou(res)
            }
        }
        .background(color = GrayA),
        contentAlignment = Alignment.Center
    ) {
        Text(text = count, color = Color.White)
    }
}
@Composable
fun ButtDelete(count:String,mod:VModel){
    var res: CalckResult = CalckResult("","")
    Box(modifier = Modifier
        .requiredSize(size.dp)
        .clip(CircleShape)
        .clickable {
            mod.rem = true
            mod.sign = ""
            mod.One = ""
            mod.Tou = ""
        }
        .background(color = GrayA),
        contentAlignment = Alignment.Center
    ) {
        Text(text = if (mod.One!="") "C" else "AC", color = Color.White)
    }
}
fun Logic (mod:VModel){
    mod.rem =true
    var res: CalckResult = CalckResult("","")
    println("OUT2::: ${mod.One}")
    when (mod.sign){
        "+" -> res.One =(mod.One.toInt()+ mod.Tou.toInt()).toString()
        "-" -> res.One =(mod.One.toInt()- mod.Tou.toInt()).toString()
        "*" -> res.One =(mod.One.toInt()* mod.Tou.toInt()).toString()
        "/" -> res.One =(mod.One.toInt()/ mod.Tou.toInt()).toString()
    }
    mod.setOne(res)
    mod.setTou(res)
}


package com.example.dogadoptapp.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dogadoptapp.R
import com.example.dogadoptapp.ui.theme.DeepBlue
import com.example.dogadoptapp.ui.theme.ICON_SIZE
import com.example.dogadoptapp.ui.theme.SMALL_PADDING
import com.example.dogadoptapp.ui.theme.activeIndicatorColor

@Composable
fun InfoBox(
    icon: Painter,
    iconColor: Color,
    smallText: String,
    textColor: Color
) {
    Row(horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier
                .padding(end = SMALL_PADDING)
                .size(ICON_SIZE),
            painter = icon,
            contentDescription = "Info Icon",
            tint = iconColor
        )
        Spacer(modifier = Modifier.size(SMALL_PADDING))
        Text(
            modifier = Modifier
                .weight(0.7f),
            text = smallText,
            color = textColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
        Surface(
            modifier = Modifier
                .weight(0.2f)
                .padding(all = 5.dp),

            shape = RoundedCornerShape(8.dp),
            color = Color.LightGray
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Male",
//                color = when (dog.gender) {
//                    "male" -> MaterialTheme.colors.activeIndicatorColor
//                    else -> Color.Red
//                },
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun InfoBoxPrev() {
    InfoBox(icon = painterResource(
        id = R.drawable.ic_scale),
        iconColor = DeepBlue, smallText = "5.6Kg", textColor = Color.Blue)
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun InfoBoxDarkPrev() {
    InfoBox(icon = painterResource(
        id = R.drawable.ic_scale),
        iconColor = Color.Blue, smallText = "5.6Kg", textColor = Color.Blue)
}
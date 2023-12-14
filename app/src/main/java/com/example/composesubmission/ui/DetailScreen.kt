package com.example.composesubmission.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.composesubmission.data.MonoCharacterRepository
import com.example.composesubmission.ui.theme.MonoCharAppTheme
import com.example.composesubmission.R

@Composable
fun DetailScreen(
    charId: String,
    viewModel: MonoCharViewModel = viewModel(
        factory = ViewModelFactory(MonoCharacterRepository())
    )
) {
    viewModel.searchByID(charId)
    val detailChar by viewModel.detailChar.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val image = detailChar?.photoUrl
        Image(
            painter = rememberAsyncImagePainter(image.toString()),
            contentDescription = "",
            modifier = Modifier
                .height(500.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )
        detailChar?.let {
            Text(
                text = it.name,
                Modifier
                    .padding(top = 8.dp),
                lineHeight = 40.sp,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        detailChar?.let {
            Text(
                text = it.voice,
                modifier = Modifier
                    .padding(top = 8.dp),
                fontStyle = FontStyle.Italic,
            )
        }
        Text(
            text = stringResource(R.string.description) + "${detailChar?.description}",
            modifier = Modifier
                .padding(top = 8.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    MonoCharAppTheme {
        DetailScreen(charId = "1")
    }
}
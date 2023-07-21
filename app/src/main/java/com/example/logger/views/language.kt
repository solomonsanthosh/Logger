package com.example.logger.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.logger.R
import com.example.logger.views.ui.theme.listBg


@Composable
fun language(){
    Box(modifier = Modifier.background(listBg).fillMaxSize()){
        Text(text = stringResource(R.string.content), color = Color.Black, modifier = Modifier.padding(15.dp))

    }
}
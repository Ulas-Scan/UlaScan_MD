package com.ulascan.app.ui.screens.chat.history

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ehsanmsz.mszprogressindicator.progressindicator.BallPulseProgressIndicator
import com.ulascan.app.R
import com.ulascan.app.data.remote.response.AnalysisData
import com.ulascan.app.data.remote.response.HistoriesItem
import com.ulascan.app.data.remote.response.UserResponse
import com.ulascan.app.data.states.ResultState
import com.ulascan.app.ui.theme.Brand900
import com.ulascan.app.ui.theme.Keyboard
import com.ulascan.app.ui.theme.Neutral900
import com.ulascan.app.ui.theme.UlaScanTheme
import com.ulascan.app.utils.ErrorMessage
import com.ulascan.app.utils.Helper
import com.ulascan.app.utils.LoadingNextPageItem
import io.eyram.iconsax.IconSax
import kotlinx.coroutines.flow.flowOf

@Composable
fun BallPulseLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        BallPulseProgressIndicator(
            color = Brand900,
            animationDuration = 800,
            animationDelay = 200,
            startDelay = 0,
            ballCount = 4
        )
    }
}

@Composable
fun Drawer(
    authState: ResultState<UserResponse>,
    historyState: ResultState<Nothing>,
    history: LazyPagingItems<HistoriesItem>,
    isLoggedIn: Boolean = false,
    onAnalyzeRouteNavigation: (AnalysisData) -> Unit,
    onFetchHistory: (String) -> Unit,
    onLoginDrawerNavigation: () -> Unit,
    onRegisterDrawerNavigation: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var keywords by remember { mutableStateOf(TextFieldValue()) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(fraction = 0.8f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if ( authState is ResultState.Loading || historyState is ResultState.Loading ) {
            BallPulseLoading()
        } else {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                val ( search, divider, logo, histories, auth ) = createRefs()
                
                if (isLoggedIn) {
                    Box(
                        modifier = Modifier
                            .constrainAs(search) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .padding(horizontal = 24.dp, vertical = 24.dp)
                            .shadow(elevation = 3.dp, shape = RoundedCornerShape(36.dp))
                            .clip(RoundedCornerShape(30.dp))
                            .background(MaterialTheme.colorScheme.background)
                            .fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = keywords,
                            onValueChange = {
                                keywords = it
                            },
                            placeholder = {
                                Text(
                                    text = stringResource(id = R.string.search),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Keyboard,
                                )
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            singleLine = true,
                            keyboardActions = KeyboardActions (
                                onDone = {
                                    onFetchHistory(keywords.text)
                                    keyboardController?.hide()
                                }
                            ),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(
                                        IconSax.Linear.SearchNormal),
                                    contentDescription = stringResource(id = R.string.search),
                                    modifier = Modifier.size(24.dp),
                                    tint = Keyboard
                                )
                            },
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = Color.Transparent,
                                focusedTextColor = Keyboard,
                                focusedLabelColor = Keyboard,
                                focusedContainerColor = MaterialTheme.colorScheme.background,
                                unfocusedContainerColor = MaterialTheme.colorScheme.background
                            )
                        )
                    }
                    LazyColumn (
                        modifier = Modifier
                            .constrainAs(histories) {
                                top.linkTo(search.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(divider.top)
                                height = Dimension.fillToConstraints
                            }
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth()
                    ) {
                        items(history.itemCount) { index ->
                            history[index]?.let {
                                Text(
                                    text = it.productName,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = Neutral900,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier
                                        .padding(12.dp)
                                        .clickable {
                                            val analysisData = history[index]?.let { it1 ->
                                                Helper.convertHistoryDataToAnalysisData(
                                                    it1
                                                )
                                            }
                                            if (analysisData != null) {
                                                onAnalyzeRouteNavigation(analysisData)
                                            }
                                        },
                                )
                            }
                        }
                        history.apply { 
                            when {
                                loadState.refresh is LoadState.Loading -> {
                                    item {
                                        LoadingNextPageItem(modifier = Modifier)
                                    }
                                }
                                loadState.refresh is LoadState.Error -> {
                                    val error = history.loadState.refresh as LoadState.Error
                                    item {
                                        ErrorMessage(
                                            modifier = Modifier.fillParentMaxSize(),
                                            message = error.error.localizedMessage!!,
                                            onClickRetry = { retry() }
                                        )
                                    }
                                }
                                loadState.append is LoadState.Loading -> {
                                    item { 
                                        LoadingNextPageItem(modifier = Modifier)
                                    }
                                }
                                loadState.append is LoadState.Error -> {
                                    val error = history.loadState.append as LoadState.Error
                                    item {
                                        ErrorMessage(
                                            modifier = Modifier,
                                            message = error.error.localizedMessage!!,
                                            onClickRetry = { retry() }
                                        )
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .constrainAs(auth) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(divider.top)
                            }
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.sign_in_or_create_an_account),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = stringResource(id = R.string.auth_description),
                            style = MaterialTheme.typography.labelMedium,
                            color = Keyboard
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Column (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                            verticalArrangement = Arrangement.spacedBy(2.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                onClick = { onLoginDrawerNavigation() },
                                modifier =  Modifier
                                    .fillMaxWidth(0.7f),
                                shape = RoundedCornerShape(6.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Brand900,
                                )
                            ) {
                                Text(
                                    text = stringResource(id = R.string.login_hint),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color.White,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                            OutlinedButton(
                                onClick = { onRegisterDrawerNavigation() },
                                shape = RoundedCornerShape(6.dp),
                                modifier =  Modifier
                                    .fillMaxWidth(0.7f),
                                border = BorderStroke(
                                    width = 2.dp,
                                    color = Brand900
                                )
                            ) {
                                Text(
                                    text = stringResource(id = R.string.register_hint),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                    }
                }
                HorizontalDivider(
                    thickness = 0.5.dp, 
                    color = Keyboard,
                    modifier = Modifier
                        .constrainAs(divider) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(logo.top)
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_item_long),
                    contentDescription = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .constrainAs(logo) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                        .size(90.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DrawerPreview() {
    val historyItems = listOf(
        Helper.generateHistoryItem(),
        Helper.generateHistoryItem(),
        Helper.generateHistoryItem(),
        Helper.generateHistoryItem(),
        Helper.generateHistoryItem(),
    )
    
    UlaScanTheme {
        Drawer(
            authState = ResultState.Default,
            historyState = ResultState.Default,
            history = flowOf(PagingData.from(historyItems)).collectAsLazyPagingItems(),
            isLoggedIn = true,
            onFetchHistory = { Log.d("ChatScreen", "Fetch history") },
            onAnalyzeRouteNavigation = { Log.d("ChatScreen", "Navigate to analysis screen") },
            onLoginDrawerNavigation = { Log.d("ChatScreen", "Navigate to login screen") },
            onRegisterDrawerNavigation = { Log.d("ChatScreen", "Navigate to register screen") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
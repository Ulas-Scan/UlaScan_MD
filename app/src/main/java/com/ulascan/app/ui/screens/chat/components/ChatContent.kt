package com.ulascan.app.ui.screens.chat.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ehsanmsz.mszprogressindicator.progressindicator.BallPulseProgressIndicator
import com.ulascan.app.R
import com.ulascan.app.data.remote.response.AnalysisData
import com.ulascan.app.data.remote.response.Chat
import com.ulascan.app.data.states.ResultState
import com.ulascan.app.ui.components.AppTitle
import com.ulascan.app.ui.screens.chat.history.DrawerState
import com.ulascan.app.ui.screens.chat.history.opposite
import com.ulascan.app.ui.theme.Brand900
import com.ulascan.app.ui.theme.Error600
import com.ulascan.app.ui.theme.Keyboard
import com.ulascan.app.ui.theme.UlaScanTheme
import com.ulascan.app.utils.Helper
import com.ulascan.app.utils.isUrl
import com.ulascan.app.utils.toCapitalize
import io.eyram.iconsax.IconSax

@Composable
fun Header(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    isLoggedIn: Boolean = false,
    onLogoutClickListener: () -> Unit,
    onDrawerClick: (DrawerState) -> Unit
) {
  Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.nav_middle),
            contentDescription = stringResource(id = R.string.history),
            modifier = modifier.size(20.dp).clickable { onDrawerClick(drawerState.opposite()) })
        AppTitle(modifier = Modifier)
        Icon(
            painter = painterResource(IconSax.Bold.Logout1),
            contentDescription = "Logout",
            modifier =
                Modifier.size(20.dp)
                    .wrapContentWidth(align = Alignment.End)
                    .clickable(isLoggedIn) { onLogoutClickListener() }
                    .alpha(if (isLoggedIn) 1f else 0f),
            tint = Error600,
        )
      }
}

@Composable
fun Guides(guides: List<String>, modifier: Modifier = Modifier) {
  LazyColumn(
      verticalArrangement = Arrangement.spacedBy(12.dp),
      modifier = modifier.padding(horizontal = 8.dp)) {
        items(guides.size) { index -> Guide(guides[index]) }
      }
}

@Composable
fun ChatPreview(modifier: Modifier = Modifier) {
  val guides = stringArrayResource(id = R.array.guides).toList()

  Column(
      modifier = modifier.padding(horizontal = 32.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Spacer(modifier = Modifier.height(32.dp))
    Image(
        painter = painterResource(id = R.drawable.logo_item),
        contentDescription = stringResource(id = R.string.app_name),
        modifier = modifier.size(100.dp))
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = stringResource(id = R.string.chat_title),
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        fontSize = 32.sp,
        fontWeight = FontWeight.ExtraBold,
    )
    Spacer(modifier = Modifier.height(20.dp))
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
      Guides(guides)
    }
  }
}

@Composable
fun ChatMessages(
    uiState: ResultState<Nothing>,
    messages: List<Chat.Message>,
    modifier: Modifier = Modifier,
    onFetchHistory: (String) -> Unit,
    onAnalyzeRouteNavigation: (AnalysisData) -> Unit
) {
  LaunchedEffect(messages) {
    if (messages.isNotEmpty()) {
      onFetchHistory("")
    }
  }

  LazyColumn(
      modifier = modifier.padding(32.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    items(messages.size) { index ->
      Column(
          verticalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        if (messages[index].isResponse) {
          if (messages[index].isError) {
            Box(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                contentAlignment = Alignment.Center) {
                  Text(
                      text = messages[index].text.toCapitalize(),
                      style = MaterialTheme.typography.labelMedium,
                      color = Error600,
                      fontWeight = FontWeight.Bold,
                  )
                }
          } else {
            ResponseMessage(
                data = messages[index].response as AnalysisData,
                onAnalyzeRouteNavigation = onAnalyzeRouteNavigation)
          }
        } else {
          UserMessage(messages[index].text)
        }
      }
    }

    if (uiState is ResultState.Loading) {
      item {
        Box(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            contentAlignment = Alignment.Center) {
              BallPulseProgressIndicator(
                  color = Brand900,
                  animationDuration = 800,
                  animationDelay = 200,
                  startDelay = 0,
                  ballCount = 4)
            }
      }
    }
  }
}

@Composable
fun ChatField(
    modifier: Modifier = Modifier,
    uiState: ResultState<Nothing>,
    onSendChatClickListener: (Chat.Message) -> Unit,
    onCancelChatClickListener: () -> Unit,
) {
  var link by remember { mutableStateOf(TextFieldValue()) }
  var isValidLink by remember { mutableStateOf(true) }
  val keyboardController = LocalSoftwareKeyboardController.current

  Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = modifier.background(MaterialTheme.colorScheme.background),
      horizontalArrangement = Arrangement.spacedBy(7.dp)) {
        Box(
            modifier =
                modifier
                    .weight(1f)
                    .shadow(elevation = 3.dp, shape = RoundedCornerShape(36.dp))
                    .clip(RoundedCornerShape(30.dp))
                    .border(
                        width = 3.dp,
                        color = if (isValidLink) Color.Transparent else Error600,
                        shape = RoundedCornerShape(30.dp))
                    .background(Color.White)) {
              OutlinedTextField(
                  value = link,
                  onValueChange = { newLink ->
                    link = newLink
                    isValidLink = newLink.text.isUrl()
                  },
                  placeholder = {
                    Text(
                        text = stringResource(id = R.string.chat_placeholder),
                        style = MaterialTheme.typography.labelMedium,
                        color = Keyboard,
                    )
                  },
                  singleLine = true,
                  keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                  modifier =
                      modifier
                          .border(
                              width = 3.dp,
                              color = if (isValidLink) Color.Transparent else Error600,
                              shape = RoundedCornerShape(35.dp))
                          .padding(horizontal = 12.dp, vertical = 2.dp)
                          .widthIn(min = 0.dp, max = 250.dp),
                  colors =
                      OutlinedTextFieldDefaults.colors(
                          unfocusedBorderColor = Color.Transparent,
                          focusedBorderColor = Color.Transparent,
                          focusedTextColor = if (isValidLink) Keyboard else Color.Red,
                          focusedLabelColor = if (isValidLink) Keyboard else Color.Red,
                          focusedContainerColor = Color.White,
                          unfocusedContainerColor = Color.White),
              )
            }
        Box {
          Button(
              onClick = {
                if (uiState is ResultState.Loading) {
                  onCancelChatClickListener()
                } else {
                  onSendChatClickListener(
                      Chat.Message(
                          isResponse = false,
                          text = link.text,
                      ))
                  link = TextFieldValue()
                  keyboardController?.hide()
                }
              },
              enabled = (isValidLink && link.text.isNotEmpty()) || uiState is ResultState.Loading,
              modifier = Modifier.size(56.dp).clip(CircleShape),
              shape = CircleShape,
              contentPadding = PaddingValues(1.dp),
              colors =
                  ButtonDefaults.buttonColors(
                      containerColor =
                          when {
                            uiState is ResultState.Loading -> Error600
                            isValidLink && link.text.isNotEmpty() -> Brand900
                            else -> Color.Gray
                          })) {
                if (uiState is ResultState.Loading) {
                  Icon(
                      painter = painterResource(IconSax.Bold.Pause),
                      contentDescription = "Cancel",
                      modifier = Modifier.size(24.dp),
                      tint = Color.White)
                } else {
                  Icon(
                      painter = painterResource(IconSax.Bold.Send1),
                      contentDescription = "Send",
                      modifier = Modifier.size(24.dp))
                }
              }
        }
      }
}

@Composable
fun ChatContent(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    onDrawerClick: (DrawerState) -> Unit,
    uiState: ResultState<Nothing>,
    chat: Chat,
    isLoggedIn: Boolean,
    onFetchHistory: (String) -> Unit,
    onSendChatClickListener: (Chat.Message) -> Unit,
    onAnalyzeRouteNavigation: (AnalysisData) -> Unit,
    onCancelChatClickListener: () -> Unit,
    onLogoutClickListener: () -> Unit
) {
  ConstraintLayout(
      modifier = modifier.fillMaxHeight(),
  ) {
    val (header, content, chatField) = createRefs()

    Header(
        drawerState = drawerState,
        isLoggedIn = isLoggedIn,
        onDrawerClick = onDrawerClick,
        onLogoutClickListener = onLogoutClickListener,
        modifier =
            Modifier.constrainAs(header) {
                  top.linkTo(parent.top, margin = 32.dp)
                  start.linkTo(parent.start)
                  end.linkTo(parent.end)
                }
                .padding(horizontal = 16.dp))

    if (chat.messages.isEmpty()) {
      ChatPreview(
          modifier =
              Modifier.constrainAs(content) {
                top.linkTo(header.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(chatField.top)
              })
    } else {
      ChatMessages(
          uiState = uiState,
          messages = chat.messages,
          modifier =
              Modifier.constrainAs(content) {
                top.linkTo(header.bottom)
                start.linkTo(parent.start)
                bottom.linkTo(chatField.top)
                height = Dimension.fillToConstraints
              },
          onFetchHistory = onFetchHistory,
          onAnalyzeRouteNavigation = onAnalyzeRouteNavigation)
    }

    ChatField(
        modifier =
            Modifier.constrainAs(chatField) {
              bottom.linkTo(parent.bottom, margin = 32.dp)
              start.linkTo(parent.start)
              end.linkTo(parent.end)
            },
        uiState = uiState,
        onSendChatClickListener = onSendChatClickListener,
        onCancelChatClickListener = onCancelChatClickListener)
  }
}

@Composable
@Preview(showBackground = true)
fun ChatContentPreview() {
  UlaScanTheme {
    ChatContent(
        drawerState = DrawerState.Closed,
        onDrawerClick = {},
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        uiState = ResultState.Default,
        chat =
            Chat(
                messages = emptyList(),
            ),
        isLoggedIn = true,
        onFetchHistory = { Log.d("ChatScreen", "Fetch history") },
        onSendChatClickListener = { Log.d("ChatScreen", "Message sent") },
        onCancelChatClickListener = { Log.d("ChatScreen", "Request cancelled") },
        onAnalyzeRouteNavigation = { Log.d("ChatScreen", "Navigate to analysis screen") },
        onLogoutClickListener = { Log.d("ChatScreen", "Logout") })
  }
}

@Composable
@Preview(showBackground = true)
fun ChatContentWithMessagePreview() {
  val instance = Helper.generateAnalysisData()

  val messages =
      mutableListOf(
          Chat.Message(isResponse = false, text = "Message number 1"),
          Chat.Message(isResponse = true, response = instance, text = "Message number 2"),
          Chat.Message(isResponse = false, text = "Message number 3"),
          Chat.Message(isResponse = true, response = instance, text = "Message number 4"),
          Chat.Message(isResponse = false, text = "Message number 5"),
      )

  UlaScanTheme {
    ChatContent(
        drawerState = DrawerState.Closed,
        onDrawerClick = {},
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        uiState = ResultState.Default,
        chat =
            Chat(
                messages = messages,
            ),
        isLoggedIn = true,
        onFetchHistory = { Log.d("ChatScreen", "Fetch history") },
        onSendChatClickListener = { Log.d("ChatScreen", "Message sent") },
        onCancelChatClickListener = { Log.d("ChatScreen", "Request cancelled") },
        onAnalyzeRouteNavigation = { Log.d("ChatScreen", "Navigate to analysis screen") },
        onLogoutClickListener = { Log.d("ChatScreen", "Logout") })
  }
}

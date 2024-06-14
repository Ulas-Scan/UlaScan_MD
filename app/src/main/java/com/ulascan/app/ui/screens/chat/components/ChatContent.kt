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
import com.ehsanmsz.mszprogressindicator.progressindicator.BallScaleRippleMultipleProgressIndicator
import com.ulascan.app.R
import com.ulascan.app.data.remote.response.AnalysisData
import com.ulascan.app.data.remote.response.Chat
import com.ulascan.app.data.remote.response.ResultState
import com.ulascan.app.ui.components.AppTitle
import com.ulascan.app.ui.screens.chat.history.DrawerState
import com.ulascan.app.ui.screens.chat.history.opposite
import com.ulascan.app.ui.theme.Brand600
import com.ulascan.app.ui.theme.Brand900
import com.ulascan.app.ui.theme.Error600
import com.ulascan.app.ui.theme.Keyboard
import com.ulascan.app.ui.theme.UlaScanTheme
import com.ulascan.app.utils.isUrl
import com.ulascan.app.utils.toCapitalize
import io.eyram.iconsax.IconSax

@Composable
fun Header(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    onDrawerClick: (DrawerState) -> Unit
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.nav_middle),
            contentDescription = stringResource(id = R.string.history),
            modifier = modifier
                .size(20.dp)
                .clickable { onDrawerClick(drawerState.opposite()) }
        )
        AppTitle(
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun Guides(guides: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        items(guides.size) { index ->
            Guide(guides[index])
        }

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
            modifier = modifier
                .size(100.dp)
        )
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
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Guides(guides)
        }
    }
}

@Composable
fun ChatMessages(uiState: ResultState<Nothing>, messages: List<Chat.Message>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(messages.size) { index ->
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                if(messages[index].isResponse) {
                    ResponseMessage(messages[index].response as AnalysisData)
                } else {
                    UserMessage(messages[index].text)
                }
            }
        }

        if (uiState is ResultState.Loading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
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
        } else if (uiState is ResultState.Error) {
            // Temporary error state
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.error.toCapitalize(),
                        style = MaterialTheme.typography.labelMedium,
                        color = Error600,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Composable
fun ChatField(modifier: Modifier = Modifier, uiState: ResultState<Nothing>, onSendChatClickListener: (Chat.Message) -> Unit, onCancelChatClickListener: () -> Unit) {
    var link by remember { mutableStateOf(TextFieldValue()) }
    var isValidLink by remember { mutableStateOf(true) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Box(
            modifier = modifier
                .weight(1f)
                .shadow(elevation = 3.dp, shape = RoundedCornerShape(36.dp))
                .clip(RoundedCornerShape(30.dp))
                .border(
                    width = 3.dp,
                    color = if (isValidLink) Color.Transparent else Error600,
                    shape = RoundedCornerShape(30.dp)
                )
                .background(Color.White)
        ) {
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
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = modifier
                    .border(
                        width = 3.dp,
                        color = if (isValidLink) Color.Transparent else Error600,
                        shape = RoundedCornerShape(35.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 2.dp)
                    .widthIn(min = 0.dp, max = 250.dp)
                    ,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    focusedTextColor = if (isValidLink) Keyboard else Color.Red,
                    focusedLabelColor = if (isValidLink) Keyboard else Color.Red,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
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
                            )
                        )
                        link = TextFieldValue()
                        keyboardController?.hide()
                    }
                },
                enabled = (isValidLink && link.text.isNotEmpty()) || uiState is ResultState.Loading,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape),
                shape = CircleShape,
                contentPadding = PaddingValues(1.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = when {
                        uiState is ResultState.Loading -> Error600
                        isValidLink && link.text.isNotEmpty() -> Brand900
                        else -> Color.Gray
                    }
                )
            ) {
                if(uiState is ResultState.Loading) {
                    Icon(
                        painter = painterResource(IconSax.Bold.Pause),
                        contentDescription = "Cancel",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                } else {
                    Icon(
                        painter = painterResource(IconSax.Bold.Send1),
                        contentDescription = "Send",
                        modifier = Modifier.size(24.dp)
                    )
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
    onSendChatClickListener: (Chat.Message) -> Unit,
    onCancelChatClickListener: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxHeight()
    ) {
        val (header, content, chatField) = createRefs()
        
        Header(
            drawerState = drawerState,
            onDrawerClick = onDrawerClick,
            modifier = Modifier
                .constrainAs(header) {
                    top.linkTo(parent.top, margin = 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = 16.dp)
        )
        
        if(chat.messages.isEmpty()) {
            ChatPreview(
                modifier = Modifier.constrainAs(content) {
                    top.linkTo(header.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(chatField.top)
                }
            )
        } else {
            ChatMessages(
                uiState = uiState,
                messages = chat.messages,
                modifier = Modifier.constrainAs(content) {
                    top.linkTo(header.bottom)
                    start.linkTo(parent.start)
                    bottom.linkTo(chatField.top)
                    height = Dimension.fillToConstraints
                }
            )
        }
        
        ChatField(
            modifier = Modifier.constrainAs(chatField) {
                bottom.linkTo(parent.bottom, margin = 32.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            uiState = uiState,
            onSendChatClickListener = onSendChatClickListener,
            onCancelChatClickListener = onCancelChatClickListener
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ChatContentPreview() {
    UlaScanTheme {
        ChatContent(
            drawerState = DrawerState.Closed,
            onDrawerClick = {},
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
            uiState = ResultState.Default,
            chat = Chat(
                messages = emptyList(),
            ),
            onSendChatClickListener = { Log.d("ChatScreen", "Message sent") },
            onCancelChatClickListener = { Log.d("ChatScreen", "Request cancelled") }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ChatContentWithMessagePreview() {
    val instance =
        AnalysisData(
            productName = "Tropicana Slim Cafe Latte (10 sch) - Kopi Bebas Gula",
            productDescription = "Tropicana Slim Cafe Latte adalah paduan kopi susu yang sempurna dengan rasa manis yang bisa dinikmati tanpa rasa khawatir karena diformulasikan tanpa penambahan gula pasir sehingga aman untuk diabetesi dan cocok untuk diet. \\nSemangati harimu dengan secangkir Tropicana Slim Cafe Latte!\\n\\nMengapa Tropicana Slim Cafe Latte?\\n- Rasa kopi susu yang nikmat\\n- Tanpa penambahan gula pasir \\n- Aman untuk diabetesi dan cocok untuk diet\\n- Lebih rendah kafein, aman untuk ibu hamil dan menyusui.\\n\\nCara Penyajian dan Petunjuk Konsumsi :\\nLarutkan 1 sachet Tropicana Slim Cafe Latte dalam 200 ml air hangat.  Aduk merata, dan secangkir kopi hangat yang nikmat lezat pun siap Anda nikmati.\\n\\nNutrimart merekomendasikan Tropicana Slim Cafe Latte untuk kamu yang:\\n - Mencari KOPI NIKMAT ala CAFE, TANPA GULA PASIR\\n - Memulai POLA HIDUP SEHAT dengan membatasi asupan gula\\n - MenJAGA KADAR GULA DARAH\\n - Memiliki DIABETES\\n - Menjalankan DIET\\n - Mementingkan KREDIBILITAS produk dengan pengalaman hampir 50 TAHUN",
            rating = 100,
            ulasan = 93,
            bintang = 4.91,
            imageUrls =
            listOf(
                "https://images.tokopedia.net/img/cache/700/VqbcmM/2023/7/26/e799c724-ed29-43dc-b723-2854421553f3.jpg",
                "https://images.tokopedia.net/img/cache/700/VqbcmM/2022/6/24/aef9ea0c-0e01-45f3-bc0f-8bc5b03dc581.jpg",
                "https://images.tokopedia.net/img/cache/700/VqbcmM/2022/6/24/26e86f2d-373b-46cb-bda9-1ab4bd427907.jpg",
                "https://images.tokopedia.net/img/cache/700/VqbcmM/2022/6/24/002e056c-cd07-4fbc-aefe-1f9a1d9ea739.jpg",
                "https://images.tokopedia.net/img/cache/700/VqbcmM/2022/6/24/07280c5d-b3de-4818-87be-440355233f6c.jpg"),
            shopName = "NutriMart",
            countNegative = 48,
            countPositive = 45,
            packaging = 84.62,
            delivery = 90,
            adminResponse = 100,
            productCondition = 92.59,
            summary =
            "Secara keseluruhan, produk Tropicana Slim Cafe Latte mendapatkan ulasan yang positif. Pengguna menyukai rasanya yang enak, meskipun beberapa menganggapnya terlalu manis. Pengiriman cepat dan pengemasan aman diapresiasi oleh banyak pengguna. Produk ini juga dipuji karena rendah kalori dan bebas gula, menjadikannya pilihan yang lebih sehat. Beberapa pengguna menyebutkan bahwa mereka telah menjadi pelanggan tetap dan akan membeli lagi.")
    
    val messages = mutableListOf(
        Chat.Message(
            isResponse = false,
            text = "Message number 1"
        ),
        Chat.Message(
            isResponse = true,
            response = instance,
            text = "Message number 2"
        ),
        Chat.Message(
            isResponse = false,
            text = "Message number 3"
        ),
        Chat.Message(
            isResponse = true,
            response = instance,
            text = "Message number 4"
        ),
        Chat.Message(
            isResponse = false,
            text = "Message number 5"
        ),
    )
    
    UlaScanTheme {
        ChatContent(
            drawerState = DrawerState.Closed,
            onDrawerClick = {},
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
            uiState = ResultState.Default,
            chat = Chat(
                messages = messages,
            ),
            onSendChatClickListener = { Log.d("ChatScreen", "Message sent") },
            onCancelChatClickListener = { Log.d("ChatScreen", "Request cancelled") }
        )
    }
}
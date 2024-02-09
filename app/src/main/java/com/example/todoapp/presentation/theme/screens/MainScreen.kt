package com.example.todoapp.presentation.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.text.isDigitsOnly
import com.example.todoapp.data.ItemStructure
import com.example.todoapp.presentation.theme.BackgroundColor
import com.example.todoapp.presentation.theme.BadgeColor
import com.example.todoapp.presentation.theme.PrimaryRed
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen() {


            ConstraintLayout(

            ) {


                        val (titleRef, totalRef, lazyRowRef, fabItem) = createRefs()
                        val itemList = remember {
                                                mutableStateListOf<ItemStructure>()
                        }



                         fun updateItemList( amount:Int, name:String){
                                     itemList.add(ItemStructure(price = amount, name = name))
                        }
                        val toplam = itemList.sumOf { it.price }

                        val coroutineScope = rememberCoroutineScope()
                        val sheetState =
                                    rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)


                        Text(text = "carihesaptakibi",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 22.sp,
                                    color = Color.Gray,
                                    modifier = Modifier
                                                .constrainAs(titleRef) {
                                                            top.linkTo(parent.top)
                                                            bottom.linkTo(titleRef.top)
                                                }
                                                .padding(horizontal = 16.dp)
                        )
                        Text(text = "$toplam TL",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                                .constrainAs(totalRef) {
                                                            top.linkTo(
                                                                        titleRef.bottom,
                                                                        margin = 10.dp
                                                            )
                                                            bottom.linkTo(lazyRowRef.top)
                                                }
                                                .padding(horizontal = 16.dp)
                        )
                        LazyColumn(
                                    modifier = Modifier
                                                .constrainAs(lazyRowRef) {
                                                            top.linkTo(
                                                                        totalRef.bottom,
                                                                        margin = 5.dp
                                                            )
//                                                bottom.linkTo(fabItem.top)
                                                }
                                                .padding(horizontal = 16.dp)
                        ) {
                                    items(itemList) { item ->
                                                SingleBudgetItem(item)

                                    }
                        }
                        Row(modifier = Modifier
                                    .constrainAs(fabItem) {
                                                bottom.linkTo(parent.bottom)
//                                                top.linkTo(lazyRowRef.bottom)
                                    }
                                    .padding(horizontal = 16.dp)
                                    .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                        ) {
                                    FloatingActionButton(
                                                onClick = {
                                                            coroutineScope.launch {
                                                                        sheetState.show()
                                                            }
                                                },
                                                modifier = Modifier.clip(CircleShape),
                                                backgroundColor = PrimaryRed

                                    )
                                    {
                                                Icon(
                                                            imageVector = Icons.Filled.Add,
                                                            contentDescription = "Add an item",
                                                            tint = Color.White,
                                                            modifier = Modifier
                                                                        .size(30.dp)
                                                )
                                    }
                        }


//                        BottomSheet(sheetState)
                        var name by remember {
                                    mutableStateOf("")
                        }
                        var amount by remember {
                                    mutableStateOf("")
                        }

                        ModalBottomSheetLayout(
                                    sheetState = sheetState,
                                    sheetContent = {
                                                Column(
                                                            modifier = Modifier
                                                                        .padding(10.dp)
                                                                        .wrapContentHeight()

                                                ) {
                                                            Spacer(modifier = Modifier.height(10.dp))

                                                            OutlinedTextField(
                                                                        value = name,
                                                                        onValueChange = {
                                                                                    name = it
                                                                        },
                                                                        label = { Text(text = "Harcama girin") },
                                                                        modifier = Modifier
                                                                                    .fillMaxWidth()
                                                            ,
                                                                        shape = MaterialTheme.shapes.medium
                                                            )
                                                            Spacer(modifier = Modifier.height(10.dp))

                                                            OutlinedTextField(
                                                                        value = amount,
                                                                        onValueChange = {
                                                                                    amount = it
                                                                        },
                                                                        label = { Text(text = "Miktar girin ") },
                                                                        modifier = Modifier
                                                                                    .fillMaxWidth(),
                                                                        keyboardOptions = KeyboardOptions(
                                                                                    keyboardType = KeyboardType.Number
                                                                        ),
                                                                        shape = MaterialTheme.shapes.medium

                                                            )
                                                            Spacer(modifier = Modifier.height(10.dp))
                                                            Button(onClick = {
                                                                        updateItemList(amount = amount.toInt(),  name = name)
                                                                        amount=""
                                                                        name=""
                                                                        coroutineScope.launch {
                                                                                    sheetState.hide()
                                                                        }

                                                            },
//                                                            modifier = Modifier,
                                                                    shape = MaterialTheme.shapes.medium

                                                            ) {
                                                                        Text(text = "Ekle",
                                                                        modifier = Modifier
                                                                                    .fillMaxWidth()
                                                                                    .padding(vertical = 5.dp)
                                                                                    ,
                                                                                    textAlign = TextAlign.Center,
                                                                                    fontSize = 17.sp,
                                                                                    fontWeight = FontWeight.Bold
                                                                                    )

                                                            }

                                                }
                                    },
                        ) {

                        }
            }
}




@Composable
fun SingleBudgetItem(item: ItemStructure) {
            Row(
                        modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
            ) {
                        Row(
                                    modifier = Modifier
                                                .clip(CircleShape)
                                                .background(
                                                            color = BadgeColor
                                                )
                                                .padding(vertical = 5.dp, horizontal = 8.dp)

                        ) {
                                    Text(
                                                text = "${item.price} TL",
                                                fontSize = 16.sp,
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold,

                                                )
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                                    text = item.name,
                                    fontSize = 17.sp,
                                    color = Color.White,
                        )
            }
}
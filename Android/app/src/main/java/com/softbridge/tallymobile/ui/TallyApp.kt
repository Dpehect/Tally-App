package com.softbridge.tallymobile.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.softbridge.tallymobile.model.*
import com.softbridge.tallymobile.ui.theme.*

private enum class Tab(val label: String) { Home("Home"), Builder("Create"), Pricing("Pricing") }

@Composable fun TallyApp(vm: BuilderViewModel = viewModel()) {
    var tab by remember { mutableStateOf(Tab.Home) }
    Scaffold(bottomBar = {
        NavigationBar(containerColor = Color.White) { Tab.entries.forEach { item ->
            NavigationBarItem(selected = tab == item, onClick = { tab = item }, icon = { Icon(when(item){Tab.Home->Icons.Outlined.Home;Tab.Builder->Icons.Outlined.AddBox;Tab.Pricing->Icons.Outlined.WorkspacePremium}, null) }, label = { Text(item.label) })
        } }
    }) { padding -> Box(Modifier.padding(padding)) {
        when(tab) { Tab.Home -> HomeScreen { tab = Tab.Builder }; Tab.Builder -> BuilderScreen(vm); Tab.Pricing -> PricingScreen { tab = Tab.Builder } }
    } }
}

@Composable private fun HomeScreen(onCreate: () -> Unit) = LazyColumn(contentPadding = PaddingValues(bottom = 40.dp)) {
    item {
        Column(Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) { Logo(); Spacer(Modifier.weight(1f)); TextButton(onClick = onCreate) { Text("Create form") } }
            Spacer(Modifier.height(56.dp)); Text("The simplest way to\ncreate forms", fontSize = 44.sp, lineHeight = 47.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Spacer(Modifier.height(18.dp)); Text("Say goodbye to boring forms. Meet a free, intuitive form builder that works like a document.", color = Color(0xFF5F5E5A), fontSize = 18.sp, lineHeight = 27.sp, textAlign = TextAlign.Center)
            Spacer(Modifier.height(26.dp)); Button(onClick = onCreate, shape = RoundedCornerShape(9.dp)) { Text("Create a free form  →", Modifier.padding(5.dp)) }; Text("No signup required", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.padding(10.dp))
            Spacer(Modifier.height(36.dp)); ProductMock()
        }
    }
    item { SectionTitle("A form builder like no other", "No code needed — just type your questions like you would in a doc.") }
    item { FeatureCard("∞", "Unlimited forms", "Create as many forms and collect as many fair-use submissions as you need.", Color(0xFFFFEFFD)) }
    item { FeatureCard("⌘", "Just start typing", "Build with familiar, Notion-style content blocks.", Color(0xFFEEF6FF)) }
    item { FeatureCard("⌁", "Privacy-friendly", "Designed with European privacy and GDPR principles in mind.", Color(0xFFEFFAF3)) }
    item { SectionTitle("Simple but powerful", "Contact details, files, choices and more — all in one calm interface.") }
    items(listOf("Contact info" to "Names, emails and links", "Multiple choice" to "Ratings, scales and options", "File uploads" to "Documents, photos and media", "Smart forms" to "Conditional, tailored journeys")) { FeatureRow(it.first, it.second) }
    item { Card(Modifier.padding(24.dp).fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Ink), shape = RoundedCornerShape(20.dp)) { Column(Modifier.padding(28.dp)) { Text("Ready to create your first form?", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold); Text("Unlimited drafts. No credit card required.", color = Color.LightGray, modifier = Modifier.padding(vertical = 12.dp)); Button(onClick = onCreate) { Text("Start building") } } } }
}

@Composable private fun Logo() = Row(verticalAlignment = Alignment.CenterVertically) { Box(Modifier.size(32.dp).clip(RoundedCornerShape(8.dp)).background(Ink), contentAlignment = Alignment.Center) { Text("T", color = Color.White, fontWeight = FontWeight.Bold) }; Text("  tally", fontWeight = FontWeight.Bold, fontSize = 21.sp) }
@Composable private fun ProductMock() = Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), elevation = CardDefaults.cardElevation(7.dp)) { Column(Modifier.padding(22.dp)) { Row { repeat(3) { Box(Modifier.padding(end=5.dp).size(8.dp).background(Color.LightGray, CircleShape)) } }; Spacer(Modifier.height(30.dp)); Text("✨", fontSize=38.sp, modifier=Modifier.align(Alignment.CenterHorizontally)); Text("Just start typing", fontWeight=FontWeight.Bold, fontSize=20.sp, modifier=Modifier.align(Alignment.CenterHorizontally).padding(top=12.dp)); Text("Build forms like a document", color=Color.Gray, modifier=Modifier.align(Alignment.CenterHorizontally).padding(top=5.dp,bottom=22.dp)) } }
@Composable private fun SectionTitle(title:String, subtitle:String)=Column(Modifier.padding(48.dp,42.dp,24.dp,18.dp)){Text(title,fontSize=31.sp,lineHeight=36.sp,fontWeight=FontWeight.Bold);Text(subtitle,color=Color.Gray,lineHeight=22.sp,modifier=Modifier.padding(top=9.dp))}
@Composable private fun FeatureCard(icon:String,title:String,body:String,color:Color)=Card(Modifier.padding(horizontal=24.dp,vertical=7.dp).fillMaxWidth(),colors=CardDefaults.cardColors(containerColor=color),shape=RoundedCornerShape(18.dp)){Column(Modifier.padding(23.dp)){Text(icon,fontSize=27.sp,color=TallyPink);Text(title,fontSize=20.sp,fontWeight=FontWeight.Bold,modifier=Modifier.padding(top=18.dp,bottom=7.dp));Text(body,color=Color.DarkGray,lineHeight=21.sp)}}
@Composable private fun FeatureRow(title:String,body:String)=Row(Modifier.padding(horizontal=28.dp,vertical=13.dp)){Box(Modifier.size(8.dp).background(TallyPink,CircleShape));Column(Modifier.padding(start=15.dp)){Text(title,fontWeight=FontWeight.Bold);Text(body,color=Color.Gray,fontSize=14.sp)}}

@Composable private fun BuilderScreen(vm: BuilderViewModel) {
    val state by vm.state.collectAsState(); var picker by remember { mutableStateOf(false) }; var message by remember { mutableStateOf<String?>(null) }
    Scaffold(topBar={TopAppBar(title={Logo()},actions={TextButton(onClick={vm.save();message="Draft saved on this device"}){Text("Save")};Button(onClick={vm.save();message="Form published (demo)"},modifier=Modifier.padding(end=10.dp)){Text("Publish")}})},floatingActionButton={ExtendedFloatingActionButton(onClick={picker=!picker},icon={Icon(Icons.Outlined.Add,null)},text={Text("Add block")})}){pad->
        Box(Modifier.padding(pad)){LazyColumn(Modifier.fillMaxSize(),contentPadding=PaddingValues(20.dp,14.dp,20.dp,110.dp)){item{OutlinedTextField(state.title,vm::setTitle,modifier=Modifier.fillMaxWidth(),textStyle=LocalTextStyle.current.copy(fontSize=29.sp,fontWeight=FontWeight.Bold),label={Text("Form title")});message?.let{Text(it,color=Color(0xFF268542),fontSize=13.sp,modifier=Modifier.padding(top=9.dp))};Spacer(Modifier.height(16.dp))};items(state.blocks,key={it.id}){block->BlockEditor(block,{vm.setPrompt(block.id,it)},{vm.remove(block.id)},{vm.move(block.id,-1)},{vm.move(block.id,1)})}}
        AnimatedVisibility(picker,Modifier.align(Alignment.BottomCenter)){Card(Modifier.padding(14.dp).fillMaxWidth(),shape=RoundedCornerShape(18.dp),elevation=CardDefaults.cardElevation(10.dp)){Column(Modifier.padding(12.dp)){Text("Add a block",fontWeight=FontWeight.Bold,modifier=Modifier.padding(8.dp));BlockType.entries.chunked(3).forEach{row->Row(Modifier.fillMaxWidth()){row.forEach{type->TextButton(onClick={vm.add(type);picker=false},modifier=Modifier.weight(1f)){Column(horizontalAlignment=Alignment.CenterHorizontally){Text(type.symbol,fontSize=21.sp);Text(type.label,fontSize=11.sp,textAlign=TextAlign.Center)}}}}}}}}
    }
}
}

@Composable private fun BlockEditor(block:FormBlock,onPrompt:(String)->Unit,onDelete:()->Unit,onUp:()->Unit,onDown:()->Unit)=Card(Modifier.fillMaxWidth().padding(vertical=6.dp),shape=RoundedCornerShape(14.dp),colors=CardDefaults.cardColors(containerColor=Color.White)){Column(Modifier.padding(15.dp)){Row(verticalAlignment=Alignment.CenterVertically){Surface(color=Color(0xFFF1F1EF),shape=RoundedCornerShape(7.dp)){Text(block.type.symbol,fontWeight=FontWeight.Bold,modifier=Modifier.padding(horizontal=10.dp,vertical=7.dp))};Text(block.type.label,color=Color.Gray,fontSize=12.sp,modifier=Modifier.padding(start=9.dp).weight(1f));IconButton(onUp){Icon(Icons.Outlined.KeyboardArrowUp,"Move up")};IconButton(onDown){Icon(Icons.Outlined.KeyboardArrowDown,"Move down")};IconButton(onDelete){Icon(Icons.Outlined.Delete,"Delete",tint=Color(0xFFB43B42))}};OutlinedTextField(block.prompt,onPrompt,Modifier.fillMaxWidth(),singleLine=block.type!=BlockType.Text&&block.type!=BlockType.Long);when(block.type){BlockType.Choice->Row(Modifier.padding(top=12.dp)){listOf("Option 1","Option 2").forEach{Text("○ $it",color=Color.Gray,modifier=Modifier.padding(end=20.dp))}};BlockType.File->OutlinedButton({},Modifier.padding(top=10.dp)){Text("Choose file")};else->Unit}}}

@Composable private fun PricingScreen(onCreate:()->Unit)=LazyColumn(contentPadding=PaddingValues(24.dp,32.dp,24.dp,60.dp),horizontalAlignment=Alignment.CenterHorizontally){item{Logo();Spacer(Modifier.height(42.dp));Text("Simple, transparent pricing",fontSize=36.sp,lineHeight=40.sp,fontWeight=FontWeight.Bold,textAlign=TextAlign.Center);Text("Start free. Upgrade when you need more power.",color=Color.Gray,textAlign=TextAlign.Center,modifier=Modifier.padding(12.dp,10.dp,12.dp,30.dp))};item{PlanCard("Free","€0","forever",listOf("Unlimited forms","Unlimited submissions (fair use)","All input blocks","Conditional logic","GDPR friendly"),false,onCreate)};item{Spacer(Modifier.height(18.dp));PlanCard("Pro","€29","per month",listOf("Everything in Free","Remove branding","Custom domains","Team collaboration","Priority support"),true,onCreate)}}
@Composable private fun PlanCard(name:String,price:String,period:String,features:List<String>,pro:Boolean,onCreate:()->Unit)=Card(Modifier.fillMaxWidth().then(if(pro)Modifier.border(2.dp,TallyPink,RoundedCornerShape(18.dp))else Modifier),shape=RoundedCornerShape(18.dp),colors=CardDefaults.cardColors(containerColor=Color.White)){Column(Modifier.padding(26.dp)){Text(name,fontSize=24.sp,fontWeight=FontWeight.Bold);Row(verticalAlignment=Alignment.Bottom,modifier=Modifier.padding(vertical=16.dp)){Text(price,fontSize=42.sp,fontWeight=FontWeight.Bold);Text(" / $period",color=Color.Gray,modifier=Modifier.padding(bottom=7.dp))};features.forEach{Text("✓  $it",modifier=Modifier.padding(vertical=6.dp))};Button(onClick=onCreate,Modifier.fillMaxWidth().padding(top=20.dp),colors=ButtonDefaults.buttonColors(containerColor=if(pro)Ink else TallyBlue)){Text(if(pro)"Try Pro" else "Get started free",modifier=Modifier.padding(4.dp))}}}

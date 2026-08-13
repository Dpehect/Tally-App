package com.softbridge.tallymobile.model

import java.util.UUID

enum class BlockType(val label: String, val symbol: String) {
    Text("Text", "T"), Short("Short answer", "Aa"), Long("Long answer", "≡"), Choice("Multiple choice", "◉"), Email("Email", "@"), File("File upload", "↑")
}

data class FormBlock(val id: String = UUID.randomUUID().toString(), val type: BlockType, val prompt: String)

data class BuilderState(val title: String = "Untitled form", val blocks: List<FormBlock> = listOf(
    FormBlock(type = BlockType.Short, prompt = "What is your name?"),
    FormBlock(type = BlockType.Email, prompt = "Email address")
))

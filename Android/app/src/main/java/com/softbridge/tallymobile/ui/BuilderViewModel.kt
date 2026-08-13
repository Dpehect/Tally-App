package com.softbridge.tallymobile.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.softbridge.tallymobile.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.json.JSONArray
import org.json.JSONObject

class BuilderViewModel(application: Application) : AndroidViewModel(application) {
    private val prefs = application.getSharedPreferences("tally_draft", 0)
    private val _state = MutableStateFlow(load())
    val state = _state.asStateFlow()

    fun setTitle(value: String) = _state.update { it.copy(title = value) }
    fun setPrompt(id: String, value: String) = _state.update { s -> s.copy(blocks = s.blocks.map { if (it.id == id) it.copy(prompt = value) else it }) }
    fun add(type: BlockType) = _state.update { it.copy(blocks = it.blocks + FormBlock(type = type, prompt = defaultPrompt(type))) }
    fun remove(id: String) = _state.update { it.copy(blocks = it.blocks.filterNot { block -> block.id == id }) }
    fun move(id: String, delta: Int) = _state.update { state ->
        val list = state.blocks.toMutableList(); val from = list.indexOfFirst { it.id == id }; val to = (from + delta).coerceIn(0, list.lastIndex)
        if (from >= 0 && from != to) list.add(to, list.removeAt(from)); state.copy(blocks = list)
    }
    fun save() { prefs.edit().putString("draft", encode(_state.value).toString()).apply() }
    fun reset() { _state.value = BuilderState(); prefs.edit().clear().apply() }

    private fun defaultPrompt(type: BlockType) = when(type) { BlockType.Text -> "Add some text here…"; BlockType.Short -> "New question"; BlockType.Long -> "Tell us more"; BlockType.Choice -> "Choose one option"; BlockType.Email -> "Email address"; BlockType.File -> "Upload a file" }
    private fun encode(s: BuilderState) = JSONObject().put("title", s.title).put("blocks", JSONArray().apply { s.blocks.forEach { put(JSONObject().put("id", it.id).put("type", it.type.name).put("prompt", it.prompt)) } })
    private fun load(): BuilderState = runCatching {
        val root = JSONObject(prefs.getString("draft", null) ?: return BuilderState()); val items = root.getJSONArray("blocks")
        BuilderState(root.getString("title"), (0 until items.length()).map { i -> items.getJSONObject(i).let { FormBlock(it.getString("id"), BlockType.valueOf(it.getString("type")), it.getString("prompt")) } })
    }.getOrDefault(BuilderState())
}

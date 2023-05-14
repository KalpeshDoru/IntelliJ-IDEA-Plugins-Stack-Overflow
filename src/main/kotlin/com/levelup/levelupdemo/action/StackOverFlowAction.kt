package com.levelup.levelupdemo.action

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.lang.Language
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.CaretModel
import com.intellij.openapi.editor.Editor
import java.util.*


class StackOverFlowAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val caretModel = editor.caretModel
        var languageTag = ""
        val file = e.getData(CommonDataKeys.PSI_FILE)
        if (file != null) {
            val lang: Language = e.getData(CommonDataKeys.PSI_FILE)!!.language
            languageTag = "+[" + lang.displayName.lowercase(Locale.getDefault()) + "]"
        }
        if (caretModel.currentCaret.hasSelection()) {
            val query = caretModel.currentCaret.selectedText!!.replace(' ', '+') + languageTag
            BrowserUtil.browse("https://stackoverflow.com/search?q=$query")
        }
    }

    override fun update(e: AnActionEvent) {
        val editor: Editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val caretModel: CaretModel = editor.caretModel
        e.presentation.isEnabledAndVisible = caretModel.currentCaret.hasSelection()
    }
}

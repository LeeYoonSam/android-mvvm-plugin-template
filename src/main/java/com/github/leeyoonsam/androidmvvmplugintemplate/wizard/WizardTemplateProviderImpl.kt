package com.github.leeyoonsam.androidmvvmplugintemplate.wizard

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider

class WizardTemplateProviderImpl  : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
        mvvmActivityTemplate,
        mvvmFragmentTemplate
    )
}
package com.github.leeyoonsam.androidmvvmplugintemplate.wizard

import com.android.tools.idea.wizard.template.*
import java.util.Locale

private const val MIN_SDK = 23

val mvvmFragmentTemplate
    get() = template {
        name = "Android MVVM Fragment Creator"
        description = "Creates a new Fragment/ViewModel with layout file."
        minApi = MIN_SDK
        category = Category.Other // Check other categories
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
            WizardUiContext.NewProject, WizardUiContext.NewModule)

        val packageNameParam = defaultPackageNameParameter
        val pathNameParam = pathNameParameter
        val entityName = stringParameter {
            name = "Entity Name"
            default = ""
            help = "The name of the entity class to create and use in Fragment"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = ""
            help = "The name of the layout to create for the fragment"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { fragmentToLayout(entityName.value.lowercase(Locale.getDefault())) }
        }

        widgets(
            TextFieldWidget(packageNameParam),
            TextFieldWidget(entityName),
            TextFieldWidget(layoutName),
            PackageNameWidget(pathNameParam)
        )

        recipe = { data: TemplateData ->
            mvvmSetup(
                data as ModuleTemplateData,
                packageNameParam.value,
                pathNameParam.value,
                entityName.value,
                layoutName.value
            )
        }
    }

val defaultPackageNameParameter get() = stringParameter {
    name = "Default Package"
    visible = { !isNewModule }
    default = "com.mycompany.myapp"
    constraints = listOf(Constraint.PACKAGE)
    suggest = { packageName }
}

val pathNameParameter get() = stringParameter {
    name = "New File Location"
    visible = { !isNewModule }
    default = "com.mycompany.myapp.presentation"
    constraints = listOf(Constraint.PACKAGE)
    suggest = { packageName }
}
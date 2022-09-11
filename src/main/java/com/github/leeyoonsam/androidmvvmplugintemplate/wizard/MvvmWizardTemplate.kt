package com.github.leeyoonsam.androidmvvmplugintemplate.wizard

import com.android.tools.idea.wizard.template.Category
import com.android.tools.idea.wizard.template.Constraint
import com.android.tools.idea.wizard.template.FormFactor
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.PackageNameWidget
import com.android.tools.idea.wizard.template.TemplateData
import com.android.tools.idea.wizard.template.TextFieldWidget
import com.android.tools.idea.wizard.template.WizardUiContext
import com.android.tools.idea.wizard.template.activityToLayout
import com.android.tools.idea.wizard.template.fragmentToLayout
import com.android.tools.idea.wizard.template.stringParameter
import com.android.tools.idea.wizard.template.template

private const val MIN_SDK = 21

val mvvmActivityTemplate
    get() = template {
        name = "Android MVVM Activity Creator"
        description = "Creates a new Activity/ViewModel with layout file."
        minApi = MIN_SDK
        category = Category.Other // Check other categories
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry,
            WizardUiContext.NewProject, WizardUiContext.NewModule)

        val packageNameParam = defaultPackageNameParameter
        val pathNameParam = pathNameParameter
        val entityName = stringParameter {
            name = "Entity Name"
            default = ""
            help = "The name of the entity class to create and use in Activity"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = ""
            help = "The name of the layout to create for the Activity"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { activityToLayout(entityName.value.toCamelToSnakeCase()) }
        }

        widgets(
            TextFieldWidget(packageNameParam),
            TextFieldWidget(entityName),
            TextFieldWidget(layoutName),
            PackageNameWidget(pathNameParam)
        )

        recipe = { data: TemplateData ->
            mvvmSetupForActivity(
                data as ModuleTemplateData,
                packageNameParam.value,
                pathNameParam.value,
                entityName.value,
                layoutName.value
            )
        }
    }

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
            suggest = { fragmentToLayout(entityName.value.toCamelToSnakeCase()) }
        }

        widgets(
            TextFieldWidget(packageNameParam),
            TextFieldWidget(entityName),
            TextFieldWidget(layoutName),
            PackageNameWidget(pathNameParam)
        )

        recipe = { data: TemplateData ->
            mvvmSetupForFragment(
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
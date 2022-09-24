package com.github.leeyoonsam.androidmvvmplugintemplate.wizard

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

fun RecipeExecutor.mvvmSetupForActivity(
    moduleData: ModuleTemplateData,
    defaultPackage: String,
    newFilePackage: String,
    entityName: String,
    layoutName: String
) {
    val (projectData, srcOut, resOut) = moduleData

    val format = SimpleDateFormat("yyyy/MM/dd")
    val date = format.format(Date())

    val path = srcOut.absolutePath
    val srcKotlinDir = File(path)

    val viewPath = "view"
    val viewModelPath = "viewmodel"

    save(
        source = defaultActivity(
            packageName = newFilePackage,
            entityName = entityName,
            projectData = projectData
        ),
        to = srcKotlinDir.resolve("${viewPath}/${entityName}Activity.kt")
    )
    save(
        source = defaultViewModel(
            date = date,
            defaultPackage = defaultPackage,
            newFilePackage = newFilePackage,
            entityName = entityName,
            layoutName = layoutName,
            projectData = projectData
        ),
        to = srcKotlinDir.resolve("${viewModelPath}/${entityName}ViewModel.kt")
    )
    save(
        source = defaultLayoutWithViewModel(
            packageName = "${newFilePackage}.${viewModelPath}",
            entityName = entityName
        ),
        to = resOut.resolve("layout/$layoutName.xml")
    )
}

fun RecipeExecutor.mvvmSetupForFragment(
    moduleData: ModuleTemplateData,
    defaultPackage: String,
    newFilePackage: String,
    entityName: String,
    layoutName: String
) {
    val (projectData, srcOut, resOut) = moduleData

    val format = SimpleDateFormat("yyyy/MM/dd")
    val date = format.format(Date())

    val path = srcOut.absolutePath
    val srcKotlinDir = File(path)

    val viewPath = "view"
    val viewModelPath = "viewmodel"

    save(
        source = defaultFragment(
            packageName = newFilePackage,
            entityName = entityName,
            projectData = projectData
        ),
        to = srcKotlinDir.resolve("${viewPath}/${entityName}Fragment.kt")
    )
    save(
        source = defaultViewModel(
            date = date,
            defaultPackage = defaultPackage,
            newFilePackage = newFilePackage,
            entityName = entityName,
            layoutName = layoutName,
            projectData = projectData
        ),
        to = srcKotlinDir.resolve("${viewModelPath}/${entityName}ViewModel.kt")
    )
    save(
        source = defaultLayoutWithViewModel(
            packageName = "${newFilePackage}.${viewModelPath}",
            entityName = entityName
        ),
        to = resOut.resolve("layout/$layoutName.xml")
    )
}
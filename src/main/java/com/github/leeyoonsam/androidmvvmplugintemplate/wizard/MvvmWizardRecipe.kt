package com.github.leeyoonsam.androidmvvmplugintemplate.wizard

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
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

    addAllKotlinDependencies(moduleData)
    val format = SimpleDateFormat("yyyy/MM/dd")
    val date = format.format(Date())

    val path = srcOut.absolutePath
    val srcKotlinDir = File(path)

    val viewPath = "view"
    val viewModelPath = "viewmodel"

    save(
        source = someActivity(
            packageName = defaultPackage,
            newFilePackage = newFilePackage,
            entityName = entityName,
            layoutName = layoutName,
            projectData = projectData
        ),
        to = srcKotlinDir.resolve("${viewPath}/${entityName}Activity.kt")
    )
    save(
        source = someViewModel(
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
        source = someLayoutWithViewModel(
            packageName = defaultPackage,
            newFilePackage = "${newFilePackage}.${viewModelPath}",
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

    addAllKotlinDependencies(moduleData)
    val format = SimpleDateFormat("yyyy/MM/dd")
    val date = format.format(Date())

    val path = srcOut.absolutePath
    val srcKotlinDir = File(path)

    val viewPath = "view"
    val viewModelPath = "viewmodel"

    save(
        source = someFragment(
            date = date,
            defaultPackage = defaultPackage,
            newFilePackage = newFilePackage,
            entityName = entityName,
            layoutName = layoutName,
            projectData = projectData
        ),
        to = srcKotlinDir.resolve("${viewPath}/${entityName}Fragment.kt")
    )
    save(
        source = someViewModel(
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
        source = someLayoutWithViewModel(
            packageName = defaultPackage,
            newFilePackage = "${newFilePackage}.${viewModelPath}",
            entityName = entityName
        ),
        to = resOut.resolve("layout/$layoutName.xml")
    )
}
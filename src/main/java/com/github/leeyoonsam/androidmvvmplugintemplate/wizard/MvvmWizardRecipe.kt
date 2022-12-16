package com.github.leeyoonsam.androidmvvmplugintemplate.wizard

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import java.io.File

fun RecipeExecutor.mvvmSetupForActivity(
    moduleData: ModuleTemplateData,
    newFilePackage: String,
    entityName: String,
    layoutName: String
) {
    val (projectData, srcOut, resOut) = moduleData

    val path = srcOut.absolutePath
    val srcKotlinDir = File(path)

    val viewPath = "view"
    val viewModelPath = "viewmodel"
    val modelPath = "model"

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
            newFilePackage = newFilePackage,
            entityName = entityName,
            projectData = projectData
        ),
        to = srcKotlinDir.resolve("${viewModelPath}/${entityName}ViewModel.kt")
    )
    save(
        source = defaultViewState(
            newFilePackage = newFilePackage,
            entityName = entityName,
        ),
        to = srcKotlinDir.resolve("${viewModelPath}/${entityName}ViewState.kt")
    )
    save(
        source = defaultStringProvider(
            newFilePackage = newFilePackage,
            entityName = entityName,
            projectData = projectData
        ),
        to = srcKotlinDir.resolve("${viewModelPath}/${entityName}StringProvider.kt")
    )
    save(
        source = defaultActionEntity(
            newFilePackage = newFilePackage,
            entityName = entityName,
            projectData = projectData
        ),
        to = srcKotlinDir.resolve("${modelPath}/${entityName}ActionEntity.kt")
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
    newFilePackage: String,
    entityName: String,
    layoutName: String
) {
    val (projectData, srcOut, resOut) = moduleData

    val path = srcOut.absolutePath
    val srcKotlinDir = File(path)

    val viewPath = "view"
    val viewModelPath = "viewmodel"
    val modelPath = "model"

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
            newFilePackage = newFilePackage,
            entityName = entityName,
            projectData = projectData
        ),
        to = srcKotlinDir.resolve("${viewModelPath}/${entityName}ViewModel.kt")
    )
    save(
        source = defaultViewState(
            newFilePackage = newFilePackage,
            entityName = entityName,
        ),
        to = srcKotlinDir.resolve("${viewModelPath}/${entityName}ViewState.kt")
    )
    save(
        source = defaultStringProvider(
            newFilePackage = newFilePackage,
            entityName = entityName,
            projectData = projectData
        ),
        to = srcKotlinDir.resolve("${viewModelPath}/${entityName}StringProvider.kt")
    )
    save(
        source = defaultActionEntity(
            newFilePackage = newFilePackage,
            entityName = entityName,
            projectData = projectData
        ),
        to = srcKotlinDir.resolve("${modelPath}/${entityName}ActionEntity.kt")
    )
    save(
        source = defaultLayoutWithViewModel(
            packageName = "${newFilePackage}.${viewModelPath}",
            entityName = entityName
        ),
        to = resOut.resolve("layout/$layoutName.xml")
    )
}
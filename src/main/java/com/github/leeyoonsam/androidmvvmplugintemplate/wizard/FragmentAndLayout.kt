package com.github.leeyoonsam.androidmvvmplugintemplate.wizard

import java.util.Locale
import android.databinding.tool.ext.toCamelCase
import com.android.tools.idea.wizard.template.ProjectTemplateData

fun someActivity(
    packageName: String,
    entityName: String,
    layoutName: String,
    projectData: ProjectTemplateData
) : String {
    val dataBindingName = "Fragment${entityName.lowercase(Locale.getDefault()).toCamelCase()}Binding"

    return """
        package $packageName
        import android.os.Bundle
        import androidx.activity.viewModels
        import dagger.hilt.android.AndroidEntryPoint
        import ${projectData.applicationPackage}.R;
        import ${projectData.applicationPackage}.databinding.${dataBindingName}
        import ${projectData.applicationPackage}.presentation.base.ui.BaseActivity
        import ${projectData.applicationPackage}.domain.entity.ActionEntity
        import ${projectData.applicationPackage}.domain.entity.ClickEntity
        
        class ${entityName}Activity : BaseActivity<${dataBindingName}>(R.layout.${layoutName.lowercase(Locale.getDefault())}) {
            private val viewModel by viewModels<${entityName}ViewModel>()
            
            override fun setBind() {}

            override fun initObserve() {}
        
            override fun initData() {}
        
            override fun handleSelectEvent(entity: ClickEntity) {}
        
            override fun handleActionEvent(entity: ActionEntity) {}
        }
    """.trimIndent()
}

fun someActivityLayout(
    packageName: String,
    newFilePackage: String,
    entityName: String
) = """
    <?xml version="1.0" encoding="utf-8"?>
    <layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto">

        <data>
            <variable
                name="viewModel"
                type="${newFilePackage}.${entityName}ViewModel" />
        </data>

        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:padding="16dp">

        </androidx.constraintlayout.widget.ConstraintLayout>
    </layout>
    """.trimIndent()

fun someFragment(
    date: String,
    defaultPackage: String,
    newFilePackage: String,
    entityName: String,
    layoutName: String,
    projectData: ProjectTemplateData
): String {
    val dataBindingName = "Fragment${entityName.lowercase(Locale.getDefault()).toCamelCase()}Binding"
    return """
        package $newFilePackage
        
        import android.os.Bundle
        import android.view.View
        import androidx.fragment.app.viewModels
        import dagger.hilt.android.AndroidEntryPoint
        import ${projectData.applicationPackage}.presentation.base.ui.BaseFragment
        import ${projectData.applicationPackage}.databinding.${dataBindingName}
        import ${projectData.applicationPackage}.R
        import ${projectData.applicationPackage}.domain.entity.ActionEntity
        import ${projectData.applicationPackage}.domain.entity.ClickEntity
        
        @AndroidEntryPoint
        class ${entityName}Fragment : BaseFragment<${dataBindingName}>(R.layout.${layoutName.lowercase(Locale.getDefault())}) {
            private val viewModel by viewModels<${entityName}ViewModel>()
            override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                binding.viewModel = viewModel
                super.onViewCreated(view, savedInstanceState)
            }
            
            override fun setBind() {}

            override fun initObserve() {}
        
            override fun initData() {}
        
            override fun handleSelectEvent(entity: ClickEntity) {}
        
            override fun handleActionEvent(entity: ActionEntity) {}
        }
    """.trimIndent()
}
fun someViewModel(
    date: String,
    defaultPackage: String,
    newFilePackage: String,
    entityName: String,
    layoutName: String,
    projectData: ProjectTemplateData
) = """
    package $newFilePackage
    
    import ${projectData.applicationPackage}.presentation.base.ui.BaseViewModel
    import dagger.hilt.android.lifecycle.HiltViewModel
    import javax.inject.Inject

    @HiltViewModel
    class TestViewModel @Inject constructor(

    ) : BaseViewModel() {

    }
""".trimIndent()

fun someFragmentLayout(
    defaultPackage: String,
    newFilePackage: String,
    entityName: String
) = """
    <?xml version="1.0" encoding="utf-8"?>
    <layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto">

        <data>
            <variable
                name="viewModel"
                type="${newFilePackage}.${entityName}ViewModel" />
        </data>

        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:padding="16dp">

        </androidx.constraintlayout.widget.ConstraintLayout>
    </layout>
    """.trimIndent()
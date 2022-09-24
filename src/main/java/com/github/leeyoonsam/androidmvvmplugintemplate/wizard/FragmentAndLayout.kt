package com.github.leeyoonsam.androidmvvmplugintemplate.wizard

import com.android.tools.idea.wizard.template.ProjectTemplateData

fun defaultActivity(
    packageName: String,
    entityName: String,
    projectData: ProjectTemplateData
) : String {
    val activityName = "${entityName}Activity"
    val dataBindingName = "Activity${entityName}Binding"
    val viewDirectory = "view"

    return """
        package $packageName.$viewDirectory

        import android.os.Bundle
        import android.os.PersistableBundle
        import androidx.activity.viewModels
        import androidx.appcompat.app.AppCompatActivity
        import ${projectData.applicationPackage}.databinding.${dataBindingName}
        import ${packageName}.viewmodel.${entityName}ViewModel
        import dagger.hilt.android.AndroidEntryPoint
        
        @AndroidEntryPoint
        class $activityName : AppCompatActivity() {
        
            private lateinit var binding: $dataBindingName
        
            private val viewModel by viewModels<${entityName}ViewModel>()
        
            override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
                super.onCreate(savedInstanceState, persistentState)
        
                binding = $dataBindingName.inflate(layoutInflater).apply {
                    lifecycleOwner = this@$activityName
                    viewModel = this@$activityName.viewModel
                }
        
                setContentView(binding.root)
            }
        }
    """.trimIndent()

}

fun defaultFragment(
    packageName: String,
    entityName: String,
    projectData: ProjectTemplateData
): String {
    val fragmentName = "${entityName}Fragment"
    val dataBindingName = "Fragment${entityName}Binding"
    val viewDirectory = "view"

    return """
        package $packageName.$viewDirectory

        import android.os.Bundle
        import android.view.LayoutInflater
        import android.view.View
        import android.view.ViewGroup
        import androidx.fragment.app.Fragment
        import androidx.fragment.app.viewModels
        import ${projectData.applicationPackage}.databinding.${dataBindingName}
        import ${packageName}.viewmodel.${entityName}ViewModel
        import dagger.hilt.android.AndroidEntryPoint
        
        @AndroidEntryPoint
        class $fragmentName : Fragment() {

        	private lateinit var binding: $dataBindingName

        	private val viewModel by viewModels<${entityName}ViewModel>()

        	override fun onCreateView(
        		inflater: LayoutInflater,
        		container: ViewGroup?,
        		savedInstanceState: Bundle?
        	): View {

        		binding = $dataBindingName.inflate(layoutInflater).apply {
        			lifecycleOwner = viewLifecycleOwner
        			viewModel = this@$fragmentName.viewModel
        		}

        		return binding.root
        	}
        }
    """.trimIndent()
}

fun defaultViewModel(
    date: String,
    defaultPackage: String,
    newFilePackage: String,
    entityName: String,
    layoutName: String,
    projectData: ProjectTemplateData
) = """
    package $newFilePackage.viewmodel
    
    import ${projectData.applicationPackage}.presentation.base.viewmodel.BaseViewModel
    import dagger.hilt.android.lifecycle.HiltViewModel
    import javax.inject.Inject

    @HiltViewModel
    class ${entityName}ViewModel @Inject constructor(

    ) : BaseViewModel()
""".trimIndent()

fun defaultLayoutWithViewModel(
    packageName: String,
    entityName: String
) = """
    <?xml version="1.0" encoding="utf-8"?>
    <layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto">

        <data>
            <variable
                name="viewModel"
                type="${packageName}.${entityName}ViewModel" />
        </data>

        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:padding="16dp">

        </androidx.constraintlayout.widget.ConstraintLayout>
    </layout>
    """.trimIndent()
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

        import android.content.Context
        import android.content.Intent
        import android.os.Bundle
        import androidx.activity.viewModels
        import androidx.appcompat.app.AppCompatActivity
        import ${packageName}.viewmodel.${entityName}ViewModel
        import dagger.hilt.android.AndroidEntryPoint
        import ${projectData.applicationPackage}.databinding.${dataBindingName}
        import ${projectData.applicationPackage}.domain.entity.ActionEntity
        import ${projectData.applicationPackage}.domain.entity.ClickEntity
        import ${projectData.applicationPackage}.utils.extensions.observeHandledEvent

        @AndroidEntryPoint
        class $activityName : AppCompatActivity() {
        
            private lateinit var binding: $dataBindingName
        
            private val viewModel by viewModels<${entityName}ViewModel>()
        
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
        
                binding = $dataBindingName.inflate(layoutInflater).apply {
                    lifecycleOwner = this@$activityName
                    viewModel = this@$activityName.viewModel
                }
        
                setContentView(binding.root)
                
                observeEventNotifier()
            }
        
            private fun observeEventNotifier() {
                observeHandledEvent(viewModel.event.click) {
                    handleSelectEvent(it)
                }
                
                observeHandledEvent(viewModel.event.action) {
                    handleActionEvent(it)
                }
                
                observeHandledEvent(viewModel.event.throwable) {
                    
                }
            }
        
            private fun handleActionEvent(entity: ActionEntity) {
                when (entity) {
                }
            }
        
            private fun handleSelectEvent(entity: ClickEntity) {
                when (entity) {
                }
            }
            
            companion object {
                fun start(context: Context) {
                    context.startActivity(
                        Intent(context, $activityName::class.java)
                    )
                }
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
        import ${packageName}.viewmodel.${entityName}ViewModel
        import dagger.hilt.android.AndroidEntryPoint
        import ${projectData.applicationPackage}.databinding.${dataBindingName}
        import ${projectData.applicationPackage}.domain.entity.ActionEntity
        import ${projectData.applicationPackage}.domain.entity.ClickEntity
        import ${projectData.applicationPackage}.utils.extensions.observeHandledEvent

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

                observeEventNotifier()

        		return binding.root
        	}
        
            private fun observeEventNotifier() {
                observeHandledEvent(viewModel.event.click) {
                    handleSelectEvent(it)
                }
                observeHandledEvent(viewModel.event.action) {
                    handleActionEvent(it)
                }
                observeHandledEvent(viewModel.event.throwable) {
                    
                }
            }
        
            private fun handleActionEvent(entity: ActionEntity) {
                when (entity) {
                }
            }
        
            private fun handleSelectEvent(entity: ClickEntity) {
                when (entity) {
                }
            }
        }
    """.trimIndent()
}

fun defaultViewModel(
    newFilePackage: String,
    entityName: String,
    projectData: ProjectTemplateData
) = """
    package $newFilePackage.viewmodel
    
    import ${projectData.applicationPackage}.presentation.base.viewmodel.BaseViewModel
    import dagger.hilt.android.lifecycle.HiltViewModel
    import javax.inject.Inject

    @HiltViewModel
    class ${entityName}ViewModel @Inject constructor(
        stringProvider: ${entityName}StringProvider
    ) : BaseViewModel() {
    
        val viewState = ${entityName}ViewState()
    }
""".trimIndent()

fun defaultViewState(
    newFilePackage: String,
    entityName: String,
) = """
    package $newFilePackage.viewmodel
    
    class ${entityName}ViewState {
    	
    }
""".trimIndent()

fun defaultStringProvider(
    newFilePackage: String,
    entityName: String,
    projectData: ProjectTemplateData
) = """
    package $newFilePackage.viewmodel
    
    import android.content.Context
    import javax.inject.Inject
    import ${projectData.applicationPackage}.R
    import ${projectData.applicationPackage}.presentation.base.res.IStringResourceGetter

    class ${entityName}StringProvider @Inject constructor(
    	private val context: Context
    ): IStringResourceGetter {

    	enum class Code {
    		$entityName
    	}

    	fun getString(code: Code): String {
    		return when (code) {
    			Code.${entityName} -> getStringRes(R.string.app_name)
    		}
    	}

    	override fun getStringRes(id: Int): String {
    		return context.getString(id)
    	}
    }
""".trimIndent()

fun defaultActionEntity(
    newFilePackage: String,
    entityName: String,
    projectData: ProjectTemplateData
) = """
    package $newFilePackage.model
    
    import ${projectData.applicationPackage}.domain.entity.ActionEntity

    sealed class ${entityName}ActionEntity : ActionEntity() {
    	
    }
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
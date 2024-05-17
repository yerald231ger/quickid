package org.qid

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.qid.core.constants.IdentityFileType
import org.qid.core.models.IdentityFile
import org.qid.di.AppContainer
import org.qid.di.QuickIdDatabaseFactory
import org.qid.ui.navigation.AppNavigation

class MainActivity : ComponentActivity() {

    lateinit var container: AppContainer
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        container = AppContainer(QuickIdDatabaseFactory(this.application))
        val identityFile = IdentityFile.create(2).also {
            it.name = "First File"
            it.description = "Description of the first file."
            it.importance = 1
            it.identityFileType = IdentityFileType.ID
            it.size = emptyArray()
            it.tags = emptyList()
            it.path = "Test"
        }

//        lifecycleScope.launch {
//            container.fileRepository.saveFile(identityFile)
//        }

        lifecycleScope.launch {
            container.fileRepository.getTopFiles().collect {
                it.forEach { file ->
                    Log.i("MainActivity", "Top Files: $file.")
                }
            }
        }



        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.4f,
                    0.0f
                )
                zoomX.duration = 200L
                zoomX.doOnEnd { screen.remove() }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.4f,
                    0.0f
                )
                zoomY.duration = 200L
                zoomY.doOnEnd { screen.remove() }

                zoomX.start()
                zoomY.start()

            }
        }
        setContent {
            AppNavigation()
        }
    }
}
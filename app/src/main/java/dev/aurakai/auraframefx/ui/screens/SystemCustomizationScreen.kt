package dev.aurakai.auraframefx.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.aurakai.auraframefx.system.lockscreen.model.LockScreenAnimation
import dev.aurakai.auraframefx.system.lockscreen.model.LockScreenConfig
import dev.aurakai.auraframefx.system.lockscreen.model.LockScreenElementConfig
import dev.aurakai.auraframefx.system.lockscreen.model.LockScreenElementType
import dev.aurakai.auraframefx.system.overlay.model.OverlayShape
import dev.aurakai.auraframefx.system.quicksettings.model.QuickSettingsAnimation
import dev.aurakai.auraframefx.system.quicksettings.model.QuickSettingsConfig
import dev.aurakai.auraframefx.system.quicksettings.model.QuickSettingsTileConfig
import dev.aurakai.auraframefx.ui.model.ImageResource
import dev.aurakai.auraframefx.ui.viewmodel.SystemCustomizationViewModel

/**
 * Displays the main system customization screen for adjusting Quick Settings and Lock Screen appearance.
 *
 * Presents sections for customizing tile and element shapes, animations, and background images, with options to reset all settings to defaults.
 */
/**
 * Displays the main system customization screen for adjusting Quick Settings and Lock Screen appearance.
 *
 * Presents sections for customizing tile and element shapes, animations, and backgrounds, with options to reset settings to defaults.
 */
/**
 * Displays the main UI for customizing system Quick Settings and Lock Screen appearance.
 *
 * Presents sections for adjusting tile and element shapes, animations, and background images. Provides options to reset all settings to their default values.
 */

/**
 * Displays the main screen for customizing system Quick Settings and Lock Screen appearance.
 *
 * Presents sections for adjusting tile and element shapes, animations, and background images. Includes a top app bar and a floating action button to reset all settings to their default values.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SystemCustomizationScreen(
    viewModel: SystemCustomizationViewModel = hiltViewModel(),
) {
    val quickSettingsConfig by viewModel.quickSettingsConfig.collectAsState()
    val lockScreenConfig by viewModel.lockScreenConfig.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("System Customization") },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Navigate back */ }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.resetToDefaults() }
            ) {
                Icon(Icons.Default.Restore, "Reset")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Quick Settings Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF00FFCC).copy(alpha = 0.1f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Quick Settings",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    QuickSettingsCustomization(
                        config = quickSettingsConfig,
                        onTileShapeChange = { tileId, shape ->
                            viewModel.updateQuickSettingsTileShape(tileId, shape)
                        },
                        onTileAnimationChange = { tileId, animation ->
                            viewModel.updateQuickSettingsTileAnimation(tileId, animation)
                        },
                        onBackgroundChange = { image ->
                            viewModel.updateQuickSettingsBackground(image)
                        }
                    )
                }
            }

            // Lock Screen Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF00FFCC).copy(alpha = 0.1f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Lock Screen",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LockScreenCustomization(
                        config = lockScreenConfig,
                        onElementShapeChange = { elementType, shape ->
                            viewModel.updateLockScreenElementShape(elementType, shape)
                        },
                        onElementAnimationChange = { elementType, animation ->
                            viewModel.updateLockScreenElementAnimation(elementType, animation)
                        },
                        onBackgroundChange = { image ->
                            viewModel.updateLockScreenBackground(image)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun QuickSettingsCustomization(
    config: QuickSettingsConfig?,
    onTileShapeChange: (String, OverlayShape) -> Unit,
    onTileAnimationChange: (String, QuickSettingsAnimation) -> Unit,
    onBackgroundChange: (ImageResource?) -> Unit,
) {
    config?.let { current ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Tiles Section
            Text(
                text = "Tiles",
                style = MaterialTheme.typography.titleSmall
            )
            current.tiles.forEach { tile ->
                TileCustomization(
                    tile = tile,
                    onShapeChange = { shape -> onTileShapeChange(tile.id, shape) },
                    onAnimationChange = { animation -> onTileAnimationChange(tile.id, animation) }
                )
            }

            // Background Section
            Text(
                text = "Background",
                style = MaterialTheme.typography.titleSmall
            )
            BackgroundCustomization(
                background = current.background,
                onChange = onBackgroundChange
            )
        }
    }
}

@Composable
fun LockScreenCustomization(
    config: LockScreenConfig?,
    onElementShapeChange: (LockScreenElementType, OverlayShape) -> Unit,
    onElementAnimationChange: (LockScreenElementType, LockScreenAnimation) -> Unit,
    onBackgroundChange: (ImageResource?) -> Unit,
) {
    config?.let { current ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Elements Section
            Text(
                text = "Elements",
                style = MaterialTheme.typography.titleSmall
            )
            current.elements.forEach { element ->
                ElementCustomization(
                    element = element,
                    onShapeChange = { shape -> onElementShapeChange(element.type, shape) },
                    onAnimationChange = { animation ->
                        onElementAnimationChange(
                            element.type,
                            animation
                        )
                    }
                )
            }

            // Background Section
            Text(
                text = "Background",
                style = MaterialTheme.typography.titleSmall
            )
            BackgroundCustomization(
                background = current.background?.image,
                onChange = onBackgroundChange
            )
        }
    }
}

@Composable
fun TileCustomization(
    tile: QuickSettingsTileConfig,
    onShapeChange: (OverlayShape) -> Unit,
    onAnimationChange: (QuickSettingsAnimation) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF00FFCC).copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = tile.label,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Shape Picker
            Text(
                text = "Shape",
                style = MaterialTheme.typography.bodyMedium
            )
            ShapePicker(
                currentShape = tile.shape,
                onShapeSelected = onShapeChange
            )

            // Animation Picker
            Text(
                text = "Animation",
                style = MaterialTheme.typography.bodyMedium
            )
            AnimationPicker(
                currentAnimation = tile.animation,
                onAnimationSelected = onAnimationChange
            )
        }
    }
}

/**
 * Displays a card for customizing a single lock screen element's shape and animation.
 *
 * Presents the element's type, allows selection of its overlay shape via a shape picker,
 * and enables choosing an animation for the element.
 *
 * @param element The configuration for the lock screen element being customized.
 * @param onShapeChange Callback invoked when a new shape is selected for the element.
 * @param onAnimationChange Callback invoked when a new animation is selected for the element.
 */
@Composable
fun ElementCustomization(
    element: LockScreenElementConfig,
    onShapeChange: (OverlayShape) -> Unit,
    onAnimationChange: (LockScreenAnimation) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF00FFCC).copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = element.type.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Shape Picker
            Text(
                text = "Shape",
                style = MaterialTheme.typography.bodyMedium
            )
            ShapePicker(
                currentShape = element.shape,
                onShapeSelected = onShapeChange
            )

            // Animation Picker
            Text(
                text = "Animation",
                style = MaterialTheme.typography.bodyMedium
            )
            AnimationPicker(
                currentAnimation = element.animation,
                onAnimationSelected = onAnimationChange
            )
        }
    }
}

/**
 * Displays a card for customizing the background image, allowing selection or removal.
 *
 * Shows the current background image and provides an interface for the user to select a new image or clear the existing one.
 *
 * @param background The currently selected background image, or null if no image is set.
 * @param onChange Invoked when the background image is changed or cleared.
 */
@Composable
fun BackgroundCustomization(
    background: ImageResource?,
    onChange: (ImageResource?) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF00FFCC).copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Background Image",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Image Picker
            ImagePicker(
                currentImage = background,
                onImageSelected = onChange
            )
        }
    }
}

/**
 * Displays a UI component for selecting an overlay shape.
 *
 * Shows available overlay shape options and calls the provided callback when the user selects a new shape.
 *
 * @param currentShape The currently selected overlay shape.
 * @param onShapeSelected Callback invoked with the newly selected shape.
 */
@Composable
fun ShapePicker(
    currentShape: OverlayShape,
    onShapeSelected: (OverlayShape) -> Unit,
) {
    // TODO: Implement shape picker UI
}

/**
 * Displays a UI component for selecting a Quick Settings animation.
 *
 * @param currentAnimation The animation currently applied to Quick Settings tiles.
 * @param onAnimationSelected Invoked when the user selects a different animation.
 */
@Composable
fun AnimationPicker(
    currentAnimation: QuickSettingsAnimation,
    onAnimationSelected: (QuickSettingsAnimation) -> Unit,
) {
    // TODO: Implement animation picker UI
}

/**
 * Displays a UI component for selecting a lock screen animation.
 *
 * @param currentAnimation The animation currently applied to the lock screen element.
 * @param onAnimationSelected Callback invoked when a new animation is selected.
 */
@Composable
fun AnimationPicker(
    currentAnimation: LockScreenAnimation,
    onAnimationSelected: (LockScreenAnimation) -> Unit,
) {
    // TODO: Implement animation picker UI
}

/**
 * Displays a UI for selecting or clearing a background image.
 *
 * Shows the current image if set, and provides options to choose a new image or remove the existing one.
 *
 * @param currentImage The currently selected image resource, or null if none is set.
 * @param onImageSelected Callback invoked when the user selects a new image or clears the selection.
 */
@Composable
fun ImagePicker(
    currentImage: ImageResource?,
    onImageSelected: (ImageResource?) -> Unit,
) {
    // TODO: Implement image picker UI
}

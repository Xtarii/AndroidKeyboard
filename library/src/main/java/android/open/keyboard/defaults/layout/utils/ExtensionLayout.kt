package android.open.keyboard.defaults.layout.utils

import android.open.keyboard.extensions.annotations.Extension
import android.open.keyboard.extensions.interfaces.IComposableExtension
import android.open.keyboard.extensions.interfaces.layoutContext
import android.open.keyboard.extensions.objects.IObject
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExtensionLayout(extensions: HashMap<String, IObject<Extension, IComposableExtension>>) {
    val layout = layoutContext

    Box(modifier = Modifier.fillMaxSize()) {
        LazyRow(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(extensions.keys.toTypedArray()) {
                OutlinedButton(
                    modifier = Modifier.size(25.dp),
                    onClick = { layout.loadExtensionIntoView(extensions[it]!!.instance) },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    extensions[it]?.instance?.Badge()
                }
            }
        }
    }
}

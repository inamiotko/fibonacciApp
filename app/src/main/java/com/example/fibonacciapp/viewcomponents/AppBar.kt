import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AppBar(title: String) {
    TopAppBar(
        title = { Text(text = title) },
        Modifier.fillMaxWidth(),
        backgroundColor = Color.Gray
    )
}
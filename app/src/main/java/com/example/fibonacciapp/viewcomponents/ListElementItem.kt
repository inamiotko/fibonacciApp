import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.math.BigInteger

@Composable
fun ListElementItem(date: String, number: Int, result: BigInteger) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = "Request details:", style = MaterialTheme.typography.headlineSmall)
                Text(text = "Date: $date", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Value: $number", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Result: $result", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
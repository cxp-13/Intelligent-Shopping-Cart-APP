package com.example.intelligent_shopping_cart.ui.screens.map

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tencent.lbssearch.`object`.result.RoutePlanningObject
import com.tencent.lbssearch.`object`.result.WalkingResultObject.Route

@Composable
fun WalkPlanCard(result: Route?) {
    if (result != null) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 8.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row {
                    WalkPlanCardItem(
                        icon = Icons.Filled.Timeline,
                        title = "Distance",
                        value = "${result.distance} meters"
                    )
                    WalkPlanCardItem(
                        icon = Icons.Filled.Timelapse,
                        title = "Duration",
                        value = "${result.duration} minutes"
                    )
                    WalkPlanCardItem(
                        icon = Icons.Filled.DirectionsWalk, title = "Mode", value = result.mode
                    )
                    WalkPlanCardItem(
                        icon = Icons.Filled.Directions,
                        title = "Direction",
                        value = result.direction
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(result.steps) { step ->
                        WalkPlanStepItem(step)
                    }
                }
            }
        }
    } else {
        CircularProgressIndicator()
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowScope.WalkPlanCardItem(icon: ImageVector, title: String, value: String) {
    Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Badge(
            containerColor = Color.Gray
        ) { Text(text = value, style = MaterialTheme.typography.bodyMedium) }
    }
}

@Composable
fun WalkPlanStepItem(step: RoutePlanningObject.Step) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Step: ${step.road_name}",
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "${step.distance} meters, ${step.instruction}, ${step.act_desc}, ${step.dir_desc}",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )
        Divider(modifier = Modifier.fillMaxWidth())
    }
}

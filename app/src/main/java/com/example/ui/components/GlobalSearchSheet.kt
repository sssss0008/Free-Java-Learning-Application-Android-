package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.JavaCurriculumData
import com.example.data.JavaProjectsData
import com.example.data.JavaReferenceData
import com.example.ui.theme.JavaCyanSecondary
import com.example.ui.theme.JavaOrangePrimary

data class SearchResultItem(
    val title: String,
    val category: String,
    val subtitle: String,
    val type: String, // "lesson", "keyword", "project", "error"
    val id: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlobalSearchModal(
    onDismissRequest: () -> Unit,
    onItemSelected: (SearchResultItem) -> Unit
) {
    var query by remember { mutableStateOf("") }

    val allSearchItems = remember {
        val list = mutableListOf<SearchResultItem>()

        // Lessons
        JavaCurriculumData.getAllLessons().forEach { l ->
            list.add(
                SearchResultItem(
                    title = l.title,
                    category = "Lesson",
                    subtitle = l.conceptSummary.take(70) + "...",
                    type = "lesson",
                    id = l.id
                )
            )
        }

        // Keywords
        JavaReferenceData.keywords.forEach { kw ->
            list.add(
                SearchResultItem(
                    title = "keyword: " + kw.keyword,
                    category = "Java Keyword",
                    subtitle = kw.description,
                    type = "keyword",
                    id = kw.keyword
                )
            )
        }

        // Projects
        JavaProjectsData.projects.forEach { p ->
            list.add(
                SearchResultItem(
                    title = p.title,
                    category = "${p.tier} Project",
                    subtitle = p.description.take(70) + "...",
                    type = "project",
                    id = p.id
                )
            )
        }

        // Errors
        JavaReferenceData.errorLabItems.forEach { err ->
            list.add(
                SearchResultItem(
                    title = err.exceptionName,
                    category = "Error & Exception",
                    subtitle = err.cause.take(70) + "...",
                    type = "error",
                    id = err.exceptionName
                )
            )
        }

        list
    }

    val filtered = remember(query) {
        if (query.isBlank()) allSearchItems.take(15)
        else allSearchItems.filter {
            it.title.contains(query, ignoreCase = true) ||
            it.category.contains(query, ignoreCase = true) ||
            it.subtitle.contains(query, ignoreCase = true)
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.testTag("global_search_bottom_sheet")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Search Input Field
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text("Search lessons, keywords, errors, projects...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = JavaOrangePrimary
                    )
                },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { query = "" }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Clear")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = JavaOrangePrimary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("search_text_input")
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = if (query.isBlank()) "Recommended & Popular Topics" else "${filtered.size} results found",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filtered) { item ->
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemSelected(item)
                                onDismissRequest()
                            }
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val iconVec = when (item.type) {
                                "lesson" -> Icons.Default.MenuBook
                                "keyword" -> Icons.Default.Code
                                "project" -> Icons.Default.FolderSpecial
                                else -> Icons.Default.BugReport
                            }
                            val iconColor = when (item.type) {
                                "lesson" -> JavaOrangePrimary
                                "keyword" -> JavaCyanSecondary
                                "project" -> Color(0xFF10B981)
                                else -> Color(0xFFEF4444)
                            }

                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(iconColor.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = iconVec,
                                    contentDescription = null,
                                    tint = iconColor,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = item.title,
                                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = iconColor.copy(alpha = 0.15f)
                                    ) {
                                        Text(
                                            text = item.category,
                                            color = iconColor,
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = item.subtitle,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontSize = 11.sp
                                    ),
                                    maxLines = 1
                                )
                            }

                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

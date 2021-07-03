package com.example.androidcomposesample.todo

import androidx.annotation.StringRes
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun TodoItemInputBackground(
  elevate: Boolean,
  modifier: Modifier = Modifier,
  content: @Composable RowScope.() -> Unit
) {
  val animatedElevation by animateDpAsState(if (elevate) 1.dp else 0.dp, TweenSpec(500))
  Surface(
    color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f),
    elevation = animatedElevation,
    shape = RectangleShape,
  ) {
    Row(
      modifier = modifier.animateContentSize(animationSpec = TweenSpec(300)),
      content = content
    )
  }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TodoInputText(
  text: String,
  onTextChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  onImeAction: () -> Unit = {}
) {
  val keyboardController = LocalSoftwareKeyboardController.current
  TextField(
    value = text,
    onValueChange = onTextChange,
    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
    maxLines = 1,
    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
    keyboardActions = KeyboardActions(onDone = {
      onImeAction()
      keyboardController?.hide()
    }),
    modifier = modifier
  )
}

@Composable
fun TodoEditButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean
) {
  TextButton(
    onClick = onClick,
    shape = CircleShape,
    enabled = enabled,
    modifier = modifier
  ) {
    Text("Add")
  }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedIconRow(
  icon: TodoIcon,
  onIconChange: (TodoIcon) -> Unit,
  modifier: Modifier = Modifier,
  visible: Boolean = true,
  initialVisibility: Boolean = false
) {
  val enter = remember { fadeIn(animationSpec = TweenSpec(300, easing = FastOutLinearInEasing)) }
  val exit = remember { fadeOut(animationSpec = TweenSpec(100, easing = FastOutSlowInEasing)) }
  Box(modifier.defaultMinSize(minHeight = 16.dp)) {
    AnimatedVisibility(
      visible = visible,
      initiallyVisible = initialVisibility,
      enter = enter,
      exit = exit,
    ) {
      IconRow(icon, onIconChange)
    }
  }
}

@Composable
fun IconRow(
  icon: TodoIcon,
  onIconChange: (TodoIcon) -> Unit,
  modifier: Modifier = Modifier
) {
  Row(modifier) {
    for (todoIcon in TodoIcon.values()) {
      SelectableIconButton(
        icon = todoIcon.imageVector,
        iconContentDescription = todoIcon.contentDescription,
        onIconSelected = { onIconChange(todoIcon) },
        isSelected = todoIcon == icon
      )
    }
  }
}

@Composable
private fun SelectableIconButton(
  icon: ImageVector,
  @StringRes iconContentDescription: Int,
  onIconSelected: () -> Unit,
  isSelected: Boolean,
  modifier: Modifier = Modifier
) {
  val tint = if (isSelected) {
    MaterialTheme.colors.primary
  } else {
    MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
  }
  TextButton(
    onClick = { onIconSelected() },
    shape = CircleShape,
    modifier = modifier
  ) {
    Column {
      Icon(
        imageVector = icon,
        tint = tint,
        contentDescription = stringResource(id = iconContentDescription)
      )
      if (isSelected) {
        Box(
          Modifier
            .padding(top = 3.dp)
            .width(icon.defaultWidth)
            .height(1.dp)
            .background(tint)
        )
      } else {
        Spacer(modifier = Modifier.height(4.dp))
      }
    }
  }
}

@Composable
fun TodoRow(
  todo: TodoItem,
  onItemClicked: (TodoItem) -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    modifier = modifier
      .clickable { onItemClicked(todo) }
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    Text(todo.task)
    val iconAlpha: Float = remember(todo.id) { randomTint() }
    Icon(
      imageVector = todo.icon.imageVector,
      tint = LocalContentColor.current.copy(alpha = iconAlpha),
      contentDescription = stringResource(id = todo.icon.contentDescription)
    )
  }
}

private fun randomTint(): Float {
  return Random.nextFloat().coerceIn(0.3f, 0.9f)
}

@Preview
@Composable
fun PreviewIconRow() {
  IconRow(icon = TodoIcon.Square, onIconChange = {})
}